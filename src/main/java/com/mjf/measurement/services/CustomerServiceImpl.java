package com.mjf.measurement.services;

import org.springframework.stereotype.Service;

import com.mjf.measurement.model.Customer;
import com.mjf.measurement.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }
    
}
