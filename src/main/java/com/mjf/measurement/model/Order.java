package com.mjf.measurement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "shirt_id")
    private Shirt shirt;

    @OneToOne
    @JoinColumn(name = "pant_id")
    private Pant pant;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order(Shirt shirt, Pant pant, Customer customer) {
        this.shirt = shirt;
        this.pant = pant;
        this.customer = customer;
    }
}
