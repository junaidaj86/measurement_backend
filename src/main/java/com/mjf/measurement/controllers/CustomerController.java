package com.mjf.measurement.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjf.measurement.model.Customer;
import com.mjf.measurement.req_res.MessageResponse;
import com.mjf.measurement.services.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
  @PostMapping
  public ResponseEntity<?> registerUser(@Valid @RequestBody Customer customer) {
        customerService.createCustomer(customer);
    return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
  }
    
}
