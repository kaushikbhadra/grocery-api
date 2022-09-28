package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.User;
import com.kaushik.shoppingapp.exception.ResourceNotFoundException;
import com.kaushik.shoppingapp.model.UserModel;
import com.kaushik.shoppingapp.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserServiceImpl implements AdminUserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserModel> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserModel> userModels = users.stream()
                .map(user -> new UserModel(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                )).collect(Collectors.toList());
        return userModels;
    }

    @Override
    public UserModel getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        return userModel;
    }

    @Override
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        userRepository.delete(user);
        return true;
    }

    @Override
    public User updateUserRole(Long id, UserModel userModel) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
        user.setRole(userModel.getRole());
        userRepository.save(user);
        return user;
    }
}
