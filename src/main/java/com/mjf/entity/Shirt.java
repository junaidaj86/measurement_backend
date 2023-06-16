package com.mjf.entity;


import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "shirt")
public class Shirt {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String length;
  private String neck;
  private String waist;
  private String sleves;
  private String sleves_length;
  private String cuff;
  private String cuff_size;
  private String chest_size;
  private String shoulder;
  private String notes;
  private String fit;
  private String collar;
  private String placket;
  private String seat;
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  public Shirt(String length, String neck, String waist, String sleves, String sleves_length, String cuff,
      String cuff_size, String chest_size, String shoulder, String notes, String fit, String collar,
      String placket, String seat) {
    this.length = length;
    this.neck = neck;
    this.waist = waist;
    this.sleves = sleves;
    this.sleves_length = sleves_length;
    this.cuff = cuff;
    this.cuff_size = cuff_size;
    this.chest_size = chest_size;
    this.shoulder = shoulder;
    this.notes = notes;
    this.fit = fit;
    this.collar = collar;
    this.placket = placket;
    this.seat = seat;
  }
}
