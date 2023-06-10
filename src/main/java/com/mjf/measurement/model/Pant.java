package com.mjf.measurement.model;

import jakarta.persistence.Entity;
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
@Table(name = "pant")
public class Pant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String waist;
    private String hip;
    private String rise;
    private String inseam;
    private String opening;
    private String Outseam;
    private String braise;
    private String fraise;
    private String knee;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Pant(String waist, String hip, String rise, String inseam, String opening, String outseam, String braise,
            String fraise, String knee) {
        this.waist = waist;
        this.hip = hip;
        this.rise = rise;
        this.inseam = inseam;
        this.opening = opening;
        Outseam = outseam;
        this.braise = braise;
        this.fraise = fraise;
        this.knee = knee;
    }

    

}


