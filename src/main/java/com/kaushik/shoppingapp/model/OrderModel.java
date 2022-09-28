package com.kaushik.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
