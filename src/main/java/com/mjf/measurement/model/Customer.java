package com.mjf.measurement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "customers", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "name"),
      @UniqueConstraint(columnNames = "phone") 
    })
    @Data
    @Setter
    @Getter
    @NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @NotBlank
  @Size(max = 20)
  private String name;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 12)
  private String phone;
  @NotBlank
  @Size(max = 120)
  private String address;
public Customer( @NotBlank @Size(max = 20) String name, @NotBlank @Size(max = 50) @Email String email,
        @NotBlank @Size(max = 12) String phone, @NotBlank @Size(max = 120) String address) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
}

  
    
}
