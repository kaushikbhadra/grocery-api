package com.kaushik.shoppingapp.repository;

import com.kaushik.shoppingapp.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<ShippingAddress, Long> {
}
