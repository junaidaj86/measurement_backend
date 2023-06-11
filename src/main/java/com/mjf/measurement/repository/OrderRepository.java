package com.mjf.measurement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mjf.measurement.DTO.OrderResponse;
import com.mjf.measurement.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT new com.mjf.measurement.DTO.OrderResponse(o, s, p, c) " +
            "FROM Order o " +
            "JOIN o.shirt s " +
            "JOIN o.pant p " +
            "JOIN o.customer c")
    List<OrderResponse> findAllOrderDetails();

 
}
