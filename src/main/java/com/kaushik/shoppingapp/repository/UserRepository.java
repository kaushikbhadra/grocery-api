package com.kaushik.shoppingapp.repository;

import com.kaushik.shoppingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
