package com.mjf.measurement.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjf.measurement.DTO.OrderDTO;
import com.mjf.measurement.DTO.OrderResponse;
import com.mjf.measurement.model.Customer;
import com.mjf.measurement.model.Order;
import com.mjf.measurement.model.Pant;
import com.mjf.measurement.model.Shirt;
import com.mjf.measurement.repository.CustomerRepository;
import com.mjf.measurement.repository.OrderRepository;
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
    OrderRepository orderRepository;

    public OrderController(CustomerRepository customerRepository, ShirtRepository shirtRepository,
            PantRepository pantRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.shirtRepository = shirtRepository;
        this.pantRepository = pantRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderDTO orderDto) {
        try {
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
            Shirt shirt = shirtRepository.save(orderDto.getShirt());
            Pant pant = pantRepository.save(orderDto.getPant());

            // Create the order
            Order order = new Order(shirt, pant, customer);
            orderRepository.save(order);

        } catch (Exception ex) {

        }
        return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orderResponses = orderRepository.findAllOrderDetails();
        return ResponseEntity.ok(orderResponses);
    }
}
