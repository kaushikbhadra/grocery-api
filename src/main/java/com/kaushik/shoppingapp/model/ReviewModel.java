package com.kaushik.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModel {
    private Long id;
    private String name;
    private int rating;
    private String comment;
}
