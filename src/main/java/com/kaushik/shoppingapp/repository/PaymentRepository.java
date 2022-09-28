package com.kaushik.shoppingapp.repository;

import com.kaushik.shoppingapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
