package com.example.dproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String role;
    private Long userId ;
}