package com.mjf.controller;


import com.mjf.dto.MessageResponse;
import com.mjf.entity.Customer;
import com.mjf.service.CustomerService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

  CustomerService customerService;
  public CustomerController(CustomerService customerService){
    this.customerService = customerService;
  }
  @PostMapping
  public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customer customer) {
    customerService.createCustomer(customer);
    return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
  }

  @GetMapping
  public ResponseEntity<?> getAllCustomer() {
    return ResponseEntity.ok(customerService.getAllCustomer());
  }

}
