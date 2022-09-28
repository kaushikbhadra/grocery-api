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
public class UserModel {

    private Long id;
    @NotEmpty
    @Size(min = 3, message = "name must be grater than 3 character!")
    private String name;
    @Email(message = "email is not valid!")
    private String email;
    @NotEmpty
    @Size(min = 8, max = 12, message = "password size require 8-12")
    @Pattern(regexp = "^(?=.*\\d)(?=\\S+$)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,12}$", message = "password must be one [a-z],[A-Z],[0-9] or [@#$%^&-+=()] symbol and not space between character")
    private String password;
    private String role;

    public UserModel(Long id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
