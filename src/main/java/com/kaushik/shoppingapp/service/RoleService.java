package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.Role;

public interface RoleService {
    Role getByName(String name);
    Role saveRole(Role role);
}
