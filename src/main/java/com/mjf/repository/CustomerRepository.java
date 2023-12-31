package com.mjf.repository;

import com.mjf.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Customer findByName(String name);
  Customer findByPhone(String phone);

  Boolean existsByPhone(String phone);

  Boolean existsByEmail(String email);

}
