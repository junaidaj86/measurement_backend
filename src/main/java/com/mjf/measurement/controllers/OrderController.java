package com.mjf.measurement.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjf.measurement.DTO.OrderDTO;
import com.mjf.measurement.model.Customer;
import com.mjf.measurement.model.Shirt;
import com.mjf.measurement.repository.CustomerRepository;
import com.mjf.measurement.repository.PantRepository;
import com.mjf.measurement.repository.ShirtRepository;
import com.mjf.measurement.req_res.MessageResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {

    CustomerRepository customerRepository;
    ShirtRepository shirtRepository;
    PantRepository pantRepository;

    

    public OrderController(CustomerRepository customerRepository, ShirtRepository shirtRepository,
            PantRepository pantRepository) {
        this.customerRepository = customerRepository;
        this.shirtRepository = shirtRepository;
        this.pantRepository = pantRepository;
    }



    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody OrderDTO orderDto) {
        try{
            Customer customer = null;
            if (!customerRepository.existsByPhone(orderDto.getCustomer().getPhone())) {
                // Create a new customer if the phone number doesn't exist
                customer = orderDto.getCustomer();
                customerRepository.save(customer);
            } else {
                // Retrieve the existing customer by phone number
                customer = customerRepository.findByPhone(orderDto.getCustomer().getPhone());
            }
    
            // Associate the shirt with the customer
            Shirt shirt = orderDto.getShirt();
            shirt.setCustomer(customer);
            customer.getShirts().add(shirt);
            shirtRepository.save(shirt);
            
        
        }catch(Exception ex){

        }
        return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
    }

}
