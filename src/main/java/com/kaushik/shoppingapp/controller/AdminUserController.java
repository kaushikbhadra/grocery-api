package com.kaushik.shoppingapp.controller;

import com.kaushik.shoppingapp.entity.User;
import com.kaushik.shoppingapp.model.UserModel;
import com.kaushik.shoppingapp.model.UserResponseModel;
import com.kaushik.shoppingapp.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/users")
    public ResponseEntity<UserResponseModel> getUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return ResponseEntity.ok(adminUserService.getAllUsers(pageNumber, pageSize, sortBy, sortDir));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserModel> getUserByAdmin(@PathVariable Long id){
        UserModel userModel = adminUserService.getUserById(id);
        return ResponseEntity.ok(userModel);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
        boolean deleted = false;
        deleted = adminUserService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

//    @PutMapping("/user/{id}")
//    public ResponseEntity<User> updateUserRole(@Valid @RequestBody UserModel userModel, @PathVariable Long id){
//        User user = adminUserService.updateUserRole(id, userModel);
//        return ResponseEntity.ok(user);
//    }
}
