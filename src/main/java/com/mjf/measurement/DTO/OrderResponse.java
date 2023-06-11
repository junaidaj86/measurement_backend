package com.mjf.measurement.DTO;

import com.mjf.measurement.model.Customer;
import com.mjf.measurement.model.Order;
import com.mjf.measurement.model.Pant;
import com.mjf.measurement.model.Shirt;
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
