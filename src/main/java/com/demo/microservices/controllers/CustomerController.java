package com.demo.microservices.controllers;

import com.demo.microservices.models.Customer;
import com.demo.microservices.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity
                .ok(customerRepository.findAll());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getById(@PathVariable Long customerId) {
        return ResponseEntity.of(customerRepository.findById(customerId));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Customer customer) {
        try {
            Customer inserted = customerRepository.save(customer);
            return ResponseEntity
                    .ok("Inserted Customer : " + inserted.getCustomerId());
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Customer customer) {
        Optional<Customer> found = customerRepository.findById(customer.getCustomerId());
        if (found.isPresent()) {
            Customer foundCustomer = found.get();
            BeanUtils.copyProperties(customer, foundCustomer);
            Customer updated;
            try {
                updated = customerRepository.save(foundCustomer);
                return ResponseEntity.ok("Updated Customer : " + updated.getCustomerId());
            } catch (Exception e) {
                return ResponseEntity
                        .badRequest()
                        .body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> delete(@PathVariable Long customerId) {
        try {
            customerRepository.deleteById(customerId);
            return ResponseEntity
                    .ok("Deleted Customer : " + customerId);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
