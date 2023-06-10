package com.mjf.measurement.model;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers", uniqueConstraints = {
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
    @Column(name = "phone", unique = true)
    private String phone;
    @NotBlank
    @Size(max = 120)
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shirt> shirts = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pant> pants = new ArrayList<>();

    public Customer(@NotBlank @Size(max = 20) String name, @NotBlank @Size(max = 50) @Email String email,
            @NotBlank @Size(max = 12) String phone, @NotBlank @Size(max = 120) String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

}
