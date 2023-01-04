package com.kaushik.shoppingapp.service;

import com.kaushik.shoppingapp.entity.Role;
import com.kaushik.shoppingapp.entity.User;
import com.kaushik.shoppingapp.exception.ResourceNotFoundException;
import com.kaushik.shoppingapp.model.UserModel;
import com.kaushik.shoppingapp.repository.RoleRepository;
import com.kaushik.shoppingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        modelMapper.map(userModel, user);
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        Role newRole = roleRepository.findById(2L).get();
        user.getRoles().add(newRole);
        User newUser = userRepository.save(user);
        return newUser;
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
