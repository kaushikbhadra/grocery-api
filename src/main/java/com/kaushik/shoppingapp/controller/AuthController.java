package com.kaushik.shoppingapp.controller;

import com.kaushik.shoppingapp.model.JWTAuthRequestModel;
import com.kaushik.shoppingapp.model.JWTAuthResponseModel;
import com.kaushik.shoppingapp.security.JWTTokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JWTTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseModel> createToken(
            @Valid
            @RequestBody JWTAuthRequestModel requestModel
            ) throws Exception {
            this.authenticate(requestModel.getUsername(), requestModel.getPassword());
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(requestModel.getUsername());
            String token = this.jwtTokenHelper.generateToken(userDetails);
        return new ResponseEntity<>(new JWTAuthResponseModel(token), HttpStatus.CREATED);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (BadCredentialsException exception){
            throw new Exception("Invalid Username and Password!");
        }
    }
}
