package com.kaushik.shoppingapp.repository;

import com.kaushik.shoppingapp.entity.Product;
import com.kaushik.shoppingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByUser(User user);
}
