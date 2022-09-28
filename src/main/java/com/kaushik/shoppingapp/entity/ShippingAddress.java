package com.kaushik.shoppingapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Shipping")
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String city;
    private String state;
    private String country;
    private int pinCode;
    private String phoneNo;
}
