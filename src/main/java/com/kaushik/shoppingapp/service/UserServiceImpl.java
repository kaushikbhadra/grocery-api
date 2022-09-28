package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.User;
import com.kaushik.shoppingapp.exception.ResourceNotFoundException;
import com.kaushik.shoppingapp.model.UserModel;
import com.kaushik.shoppingapp.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        BeanUtils.copyProperties(userModel,user);
        user.setRole("user");
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(Long id, UserModel userModel) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        userRepository.save(user);
        return user;
    }
}
