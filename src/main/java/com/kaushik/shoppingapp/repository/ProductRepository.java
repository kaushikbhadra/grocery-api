package com.kaushik.shoppingapp.repository;

import com.kaushik.shoppingapp.entity.Product;
import com.kaushik.shoppingapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByUser(User user, Pageable pageable);
    Page<Product> findByNameContaining(String name, Pageable pageable);
}
