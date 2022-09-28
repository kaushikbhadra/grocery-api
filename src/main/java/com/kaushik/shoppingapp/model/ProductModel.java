package com.kaushik.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private Long id;
    @NotBlank(message = "name not be empty!")
    private String name;
    @NotEmpty(message = "description not be empty!")
    private String description;
    private double price;
    private int ratings;
    @NotEmpty(message = "category not be empty! ")
    private String category;
    private int stock;
    private int numberOfReviews;
    private String imageName;
    private UserModel user;

    public ProductModel(Long id, String name, String description, double price, int ratings, String category, int stock, int numberOfReviews, String imageName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.ratings = ratings;
        this.category = category;
        this.stock = stock;
        this.numberOfReviews = numberOfReviews;
        this.imageName = imageName;
    }
}
