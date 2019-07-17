package com.ignite.demo.service;

import com.ignite.demo.exception.CustomerAlreadyExistsException;
import com.ignite.demo.exception.CustomerNotFoundException;
import com.ignite.demo.exception.InconsistentCacheException;
import com.ignite.demo.model.Customer;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteTransactions;
import org.apache.ignite.Ignition;
import org.apache.ignite.transactions.Transaction;
import org.springframework.stereotype.Service;

import javax.cache.CacheException;
import java.util.*;

@Service
public class CustomerService {

    private Ignite ignite = Ignition.start();
    private IgniteTransactions transactions = ignite.transactions();

    private IgniteCache<UUID, Customer> customerCache = ignite.getOrCreateCache("customerCache");
    private IgniteCache<String, Set<UUID>> customerIndexCache = ignite.getOrCreateCache("customerIndexCache");

    public List<Customer> getCustomerByLastName(String lastName) {

        Set<UUID> uuids = customerIndexCache.get(lastName);

        if (uuids == null) {
            return new ArrayList<>();
        }

        Map<UUID, Customer> customersMap = customerCache.getAll(uuids);

        if (customersMap == null || customersMap.isEmpty()) {
            return new ArrayList<>();
        } else {
            return new ArrayList<Customer>(customersMap.values());
        }

    }

    public Customer getCustomerById(UUID id) {
        return customerCache.get(id);
    }

    public Customer addCustomer(Customer customer) {

        try (Transaction tx = transactions.txStart()) {

            if (customerCache.get(customer.getId()) != null) {
                throw new CustomerAlreadyExistsException();
            }

            customerCache.put(customer.getId(), customer);

            Set<UUID> uuids = customerIndexCache.get(customer.getLastName());

            if (uuids == null) {
                uuids = new HashSet<>();
            }

            uuids.add(customer.getId());

            customerIndexCache.put(customer.getLastName(), uuids);

            tx.commit();

        } catch (CacheException ex) {

            //TODO: Handle exception
            throw ex;

        }

        return customer;

    }

    public Customer updateCustomer(UUID id, Customer customer) {

        try (Transaction tx = transactions.txStart()) {

            customer.setId(id);

            Customer currentCustomer = customerCache.get(id);

            if (currentCustomer == null) {
                throw new CustomerNotFoundException();
            }

            customerCache.replace(id, customer);

            tx.commit();

        } catch (CacheException ex) {

            //TODO: Handle exception
            throw ex;

        }

        return customer;

    }

    public void deleteById(UUID id) {

        try (Transaction tx = transactions.txStart()) {

            Customer toDelete = customerCache.get(id);

            if (toDelete == null) {
                return;
            }

            String lastName = toDelete.getLastName();


            Set<UUID> uuids = customerIndexCache.get(lastName);

            uuids.remove(id);

            customerIndexCache.replace(lastName, uuids);

            customerCache.clear(id);

            tx.commit();

        } catch (CacheException ex) {

            //TODO: Handle exception
            throw ex;

        }
    }

}
