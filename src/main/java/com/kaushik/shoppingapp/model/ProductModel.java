package com.kaushik.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private Long id;
    @NotBlank(message = "name not be empty!")
    private String name;
    @NotEmpty(message = "description not be empty!")
    private String description;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=6, fraction=2)
    private BigDecimal price;
    private int ratings;
    @NotEmpty(message = "category not be empty! ")
    private String category;
    private int stock;
    private int numberOfReviews;
    private String imageName;
    private UserModel user;
    private List<ReviewModel> reviews = new ArrayList<>();
}
