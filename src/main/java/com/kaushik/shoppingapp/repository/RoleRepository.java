package com.kaushik.shoppingapp.repository;

import com.kaushik.shoppingapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
