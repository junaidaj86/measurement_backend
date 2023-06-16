package com.mjf.service;

import com.mjf.entity.Customer;
import java.util.List;

public interface CustomerService {
  Customer createCustomer(Customer customer);

  List<Customer> getAllCustomer();
}