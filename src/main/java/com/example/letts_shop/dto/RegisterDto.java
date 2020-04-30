package com.example.letts_shop.dto;

import com.example.letts_shop.validators.UniqueUsername;
import com.example.letts_shop.validators.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegisterDto {

    @NotNull
    @NotEmpty(message = "Please choose a username")
    @UniqueUsername
    private String userName;

    private String password;

    @NotNull
    @Email
    @NotEmpty(message = "Please enter your email")
    @ValidEmail
    private String email;

}
