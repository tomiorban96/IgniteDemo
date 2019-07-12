package com.ignite.demo.service;

import com.ignite.demo.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public Customer getCustomer(String lastName) {
        return new Customer();
    }

    public Customer addCustomer(Customer customer) {
        return customer;
    }

    public void deleteById(String id) {

    }
}
