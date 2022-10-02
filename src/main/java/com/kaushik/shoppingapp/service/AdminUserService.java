package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.User;
import com.kaushik.shoppingapp.model.UserModel;
import com.kaushik.shoppingapp.model.UserResponseModel;

import java.util.List;

public interface AdminUserService {
    UserResponseModel getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    UserModel getUserById(Long id);

    boolean deleteUser(Long id);

//    User updateUserRole(Long id, UserModel userModel);
}
