package com.kaushik.shoppingapp.controller;

import com.kaushik.shoppingapp.entity.User;
import com.kaushik.shoppingapp.model.UserModel;
import com.kaushik.shoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserModel userModel){
        User user = userService.registerUser(userModel);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserModel userModel ,@PathVariable Long id){
        User user = userService.updateUser(id, userModel);
        return ResponseEntity.ok(user);
    }
}
