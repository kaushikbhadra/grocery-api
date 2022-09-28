package com.kaushik.shoppingapp.repository;

import com.kaushik.shoppingapp.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderItem, Long> {
}
