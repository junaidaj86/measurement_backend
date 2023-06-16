package com.mjf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

  private String name;
  private String email;
  private String password;
  private String roles;
  private Long shopId;
}
