package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.User;
import com.kaushik.shoppingapp.model.UserModel;

import java.util.List;

public interface AdminUserService {
    List<UserModel> getAllUsers();

    UserModel getUserById(Long id);

    boolean deleteUser(Long id);

    User updateUserRole(Long id, UserModel userModel);
}
