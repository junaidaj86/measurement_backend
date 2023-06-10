package com.mjf.measurement.DTO;

import com.mjf.measurement.model.Customer;
import com.mjf.measurement.model.Pant;
import com.mjf.measurement.model.Shirt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Customer customer;
    private Shirt shirt;
    private Pant pant;

}
