package com.kaushik.shoppingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthRequestModel {

    @Email(message = "email is not valid!")
    @NotEmpty(message = "username not be empty!")
    private String username;
    @NotEmpty
    @Size(min = 8, max = 12, message = "password size require 8-12")
    @Pattern(regexp = "^(?=.*\\d)(?=\\S+$)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,12}$", message = "password must be one [a-z],[A-Z],[0-9] or [@#$%^&-+=()] symbol and not space between character")
    private String password;
}
