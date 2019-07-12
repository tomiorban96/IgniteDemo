package com.ignite.demo.service;

import com.ignite.demo.model.Customer;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteTransactions;
import org.apache.ignite.Ignition;
import org.apache.ignite.transactions.Transaction;
import org.springframework.stereotype.Service;

import javax.cache.CacheException;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private Ignite ignite = Ignition.ignite();
    private IgniteTransactions transactions = ignite.transactions();

    private IgniteCache<UUID, Customer> customerCache = ignite.getOrCreateCache("customerCache");
    private IgniteCache<String, UUID> customerIndexCache = ignite.getOrCreateCache("customerIndexCache");

    public List<Customer> getCustomerByLastName(String lastName) {

        List<Customer> customers;
        for(UUID id : )

    }

    public Customer getCustomerById(UUID id) {
        return customerCache.get(id);
    }

    public Customer addCustomer(Customer customer) {

        try (Transaction tx = transactions.txStart()) {
            customerCache.put(customer.getId(), customer);
            customerIndexCache.put(customer.getLastName(), customer.getId());
            tx.commit();
        } catch (CacheException ex) {
            //TODO: Handle exception
            throw ex;
        }

        return customer;

    }

    public Customer updateCustomer(UUID id, Customer customer) {

        try (Transaction tx = transactions.txStart()) {
            customerCache.replace(customer.getId(), customer);
            customerIndexCache.replace(customer.getLastName(), customer.getId());
            tx.commit();
        } catch (CacheException ex) {
            //TODO: Handle exception
            throw ex;
        }

        return customer;

    }

    public void deleteById(UUID id) {
        try (Transaction tx = transactions.txStart()) {
            String lastName = customerCache.get(id).getLastName();
            customerCache.clear(id);

            tx.commit();
        } catch (CacheException ex) {
            //TODO: Handle exception
            throw ex;
        }
    }
}
