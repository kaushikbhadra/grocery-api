package com.kaushik.shoppingapp.controller;

import com.kaushik.shoppingapp.model.ResponseModel;
import com.kaushik.shoppingapp.model.ReviewModel;
import com.kaushik.shoppingapp.service.UserReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserReviewController {
    @Autowired
    private UserReviewService userReviewService;

    @PostMapping("/{userId}/product/{productId}/review")
    public ResponseEntity<ReviewModel> createReview(
            @Valid
            @RequestBody ReviewModel reviewModel,
            @PathVariable Long userId,
            @PathVariable Long productId
    ) {
        return new ResponseEntity<>(userReviewService.createReview(reviewModel, productId, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<ResponseModel> createReview(
            @PathVariable Long id
    ) {
        userReviewService.deleteReview(id);
        return new ResponseEntity<>(new ResponseModel("review deleted successfully!", true), HttpStatus.ACCEPTED);
    }

}
