package com.example.dproject.service;

import com.example.dproject.dto.AuthRequest;
import com.example.dproject.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);

    void register(AuthRequest request);
}
