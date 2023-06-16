package com.mjf.service;

import com.mjf.entity.Customer;
import com.mjf.repository.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Service;



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

  @Override
  public List<Customer> getAllCustomer() {
    return customerRepository.findAll();
  }

}