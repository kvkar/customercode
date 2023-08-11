package com.customer.customermanagement.controller;

import com.customer.customermanagement.model.customer;
import com.customer.customermanagement.repository.CustomerRepository;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

   @GetMapping("/{id}")
public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    Optional<Customer> customerOptional = customerRepository.findById(id);
    if (customerOptional.isPresent()) {
        return ResponseEntity.ok(customerOptional.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @GetMapping
    public List<customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        // Update fields of the customer object with values from updatedCustomer
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        customer.setAddress(updatedCustomer.getAddress());
        customer.setGstNumber(updatedCustomer.getGstNumber());

        return customerRepository.save(customer);
    }
}