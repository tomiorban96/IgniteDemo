package com.ignite.demo.controller;

import com.ignite.demo.exception.CustomerNotFoundException;
import com.ignite.demo.model.Customer;
import com.ignite.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // GET /customer/1
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") UUID id) {
        return customerService.getCustomerById(id);
    }

    // GET /customer/lastName/Doe
    @GetMapping("/lastName/{lastName}")
    public List<Customer> getCustomerByLastName(@PathVariable("lastName") String lastName) {
        return customerService.getCustomerByLastName(lastName);
    }

    // POST /customer
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    // PATCH /customer/1
    @PatchMapping("/{id}")
    public Customer updateCustomer(@PathVariable("id") UUID id, @RequestBody Customer customer) {
        Customer c = customerService.updateCustomer(id, customer);
        if (c == null) throw new CustomerNotFoundException();
        return c;
    }

    // DELETE /customer/1
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        customerService.deleteById(id);
    }

}
