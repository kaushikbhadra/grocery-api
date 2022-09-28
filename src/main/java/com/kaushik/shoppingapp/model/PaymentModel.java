package com.kaushik.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModel {
    private Long id;
    private double ItemsPrice;
    private double taxPrice;
    private double shippingPrice;
    private double totalPrice;
    private String orderStatus;
}
