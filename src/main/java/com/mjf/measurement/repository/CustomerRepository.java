package com.mjf.measurement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mjf.measurement.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Customer findByName(String name);
    Customer findByPhone(String phone);

  Boolean existsByPhone(String phone);

  Boolean existsByEmail(String email);
    
}
