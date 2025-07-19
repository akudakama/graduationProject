package com.example.dproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

    @NotBlank(message = "Username must not be blank")
    private String username;

    @NotBlank(message = "Password must not be blank")
    private String password;

    public AuthRequest(String user1, String pass123) {
        username = user1;
        password = pass123;
    }
}
