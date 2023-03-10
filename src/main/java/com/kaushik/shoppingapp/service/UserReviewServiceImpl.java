package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.Product;
import com.kaushik.shoppingapp.entity.Review;
import com.kaushik.shoppingapp.entity.User;
import com.kaushik.shoppingapp.exception.ResourceNotFoundException;
import com.kaushik.shoppingapp.model.ReviewModel;
import com.kaushik.shoppingapp.repository.ProductRepository;
import com.kaushik.shoppingapp.repository.ReviewRepository;
import com.kaushik.shoppingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReviewServiceImpl implements UserReviewService{
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    @Override
    public ReviewModel createReview(ReviewModel reviewModel, Long productId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        Review review = modelMapper.map(reviewModel, Review.class);
        review.setUser(user);
        review.setProduct(product);
        Review newReview = reviewRepository.save(review);
        return modelMapper.map(newReview, ReviewModel.class);
    }

    @Override
    public void deleteReview(Long id) {
        Review review =reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review", "Id", id));
          reviewRepository.delete(review);
    }
}
