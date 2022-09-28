package com.kaushik.shoppingapp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double ItemsPrice;
    private double taxPrice;
    private double shippingPrice;
    private double totalPrice;
    private String orderStatus;

    public Payment() {
        this.orderStatus = "Processing";
    }
}
