package com.mjf.repository;

import com.mjf.dto.OrderResponse;
import com.mjf.entity.Order;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query("SELECT new com.mjf.dto.OrderResponse(o, s, p, c) " +
      "FROM Order o " +
      "JOIN o.shirt s " +
      "JOIN o.pant p " +
      "JOIN o.customer c")
  List<OrderResponse> findAllOrderDetails();


  @Query("SELECT new com.mjf.dto.OrderResponse(o, s, p, c) " +
      "FROM Order o " +
      "JOIN o.shirt s " +
      "JOIN o.pant p " +
      "JOIN o.customer c " +
      "WHERE o.shop.id = :shopId")
  List<OrderResponse> findAllOrderDetailsForShop(Long shopId);

  @Modifying
  @Query("update Order o set o.orderStatus = 'Assigned' where o.id < :orderId")
  void updateStatus(Long orderId);


}