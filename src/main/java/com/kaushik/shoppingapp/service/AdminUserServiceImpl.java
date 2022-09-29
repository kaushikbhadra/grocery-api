package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.User;
import com.kaushik.shoppingapp.exception.ResourceNotFoundException;
import com.kaushik.shoppingapp.model.UserModel;
import com.kaushik.shoppingapp.model.UserResponseModel;
import com.kaushik.shoppingapp.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserServiceImpl implements AdminUserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserResponseModel getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> pageUsers = userRepository.findAll(pageable);
        List<User> users = pageUsers.getContent();
        List<UserModel> userModels = users.stream()
                .map(user -> new UserModel(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                )).collect(Collectors.toList());
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setUsers(userModels);
        userResponseModel.setPageNumber(pageUsers.getNumber());
        userResponseModel.setPageSize(pageUsers.getSize());
        userResponseModel.setTotalElements(pageUsers.getTotalElements());
        userResponseModel.setTotalPages(pageUsers.getTotalPages());
        userResponseModel.setLastPage(pageUsers.isLast());
        return userResponseModel;
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
