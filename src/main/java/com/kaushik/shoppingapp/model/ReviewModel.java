package com.kaushik.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModel {
    private Long id;
    @NotBlank(message = "name not be empty!")
    private String name;
    @Min(0)
    @Max(value = 5, message = "not greater than 5 rating")
    private int rating;
    @NotEmpty(message = "comment not be empty!")
    private String comment;
    private UserModel user;
}
