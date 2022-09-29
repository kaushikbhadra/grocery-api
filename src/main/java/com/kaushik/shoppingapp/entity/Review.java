package com.kaushik.shoppingapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int rating;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "FK_REVIEW_PRODUCT"))
    private Product product;
}
