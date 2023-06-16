package com.mjf.dto;

import com.mjf.entity.Customer;
import com.mjf.entity.Pant;
import com.mjf.entity.Shirt;
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
