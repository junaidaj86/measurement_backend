package com.mjf.dto;

import com.mjf.entity.Customer;
import com.mjf.entity.Order;
import com.mjf.entity.Pant;
import com.mjf.entity.Shirt;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
  private Order order;
  private Shirt shirt;
  private Pant pant;
  private Customer customer;
}