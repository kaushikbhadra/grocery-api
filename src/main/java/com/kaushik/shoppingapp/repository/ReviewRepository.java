package com.kaushik.shoppingapp.repository;

import com.kaushik.shoppingapp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
