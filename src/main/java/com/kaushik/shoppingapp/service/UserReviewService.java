package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.model.ReviewModel;

public interface UserReviewService {
    ReviewModel createReview(ReviewModel reviewModel, Long productId, Long userId);
    void deleteReview(Long id);
}
