package com.kaushik.shoppingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int ratings;
    private String category;
    private int stock;
    private int numberOfReviews;
    private String imageName;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCT_USER"))
    private User user;
    private Date createAt;
    public Product() {
        this.price = 0.0;
        this.ratings = 0;
        this.stock = 0;
        this.numberOfReviews = 0;
    }
}
