package com.mjf.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
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

  private String orderId;

  @OneToOne
  @JoinColumn(name = "shirt_id")
  private Shirt shirt;

  @OneToOne
  @JoinColumn(name = "pant_id")
  private Pant pant;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "shop_id")
  private Shop shop;

  @Column(name = "create_date")
  private LocalDate createDate;

  @Column(name = "deliver_date")
  private LocalDate deliverDate;

  private String orderStatus;

  public Order(Shirt shirt, Pant pant, Customer customer, Shop shop) {
    this.shirt = shirt;
    this.pant = pant;
    this.customer = customer;
    this.shop = shop;
  }

  public Order(
      final String orderId,
      final Shirt shirt,
      final Pant pant,
      final Customer customer,
      final Shop shop,
      final LocalDate createDate,
      final LocalDate deliverDate,
      final String orderStatus
  ) {
    this.orderId = orderId;
    this.shirt = shirt;
    this.pant = pant;
    this.customer = customer;
    this.shop = shop;
    this.createDate = createDate;
    this.deliverDate = deliverDate;
    this.orderStatus = orderStatus;
  }
}
