package com.ignite.demo.controller;

import com.ignite.demo.model.Customer;
import com.ignite.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{lastName}")
    public Customer getCustomer(@RequestParam("lastName") String lastName) {
        return customerService.getCustomer(lastName);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@RequestParam("id") String id) {
        customerService.deleteById(id);
    }

}
