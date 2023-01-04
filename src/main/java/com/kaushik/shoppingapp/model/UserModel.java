package com.kaushik.shoppingapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kaushik.shoppingapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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
    private List<Role> roles = new ArrayList<>();
}
