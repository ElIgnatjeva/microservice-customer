package com.demo.microservice.customer.web;

import com.demo.microservice.customer.entity.Customer;
import com.demo.microservice.customer.entity.Order;
import com.demo.microservice.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CustomerOrderResourse {
    private final Logger log = LoggerFactory.getLogger(CustomerOrderResourse.class);
    private static final String ENTITY_NAME = "order";
    @Value("${spring.application.name}")
    private String applicationName;
    private final CustomerRepository customerRepository;

    public CustomerOrderResourse(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customerOrders/{customerId}")
    public ResponseEntity<Order> createOrder(@PathVariable String customerId
            , @Valid @RequestBody Order order) {
        log.debug("REST request to save Order: {} for Customer: {}", order, customerId);
        if (customerId.isBlank()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + ENTITY_NAME);
        }

        final Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Customer: " + ENTITY_NAME);
        }
        final var customer = customerOpt.get();
        customer.addOrder(order);
        customerRepository.save(customer);
        return ResponseEntity.ok().body(order);
    }
}
