package com.kaushik.shoppingapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
