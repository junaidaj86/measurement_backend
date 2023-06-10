package com.mjf.measurement.model;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
    private String left_sleeve;
    private String right_sleeve;
    private String left_cuff;
    private String right_cuff;
    private String chest;
    private String shoulder;
    private String notes;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Shirt(String length, String neck, String waist, String left_sleeve, String right_sleeve, String left_cuff,
            String right_cuff, String chest, String shoulder, String notes) {
        this.length = length;
        this.neck = neck;
        this.waist = waist;
        this.left_sleeve = left_sleeve;
        this.right_sleeve = right_sleeve;
        this.left_cuff = left_cuff;
        this.right_cuff = right_cuff;
        this.chest = chest;
        this.shoulder = shoulder;
        this.notes = notes;
    }

    

}
