package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.User;
import com.kaushik.shoppingapp.model.UserModel;

public interface UserService {

    User registerUser(UserModel userModel);

    User updateUser(Long id, UserModel userModel);
}
