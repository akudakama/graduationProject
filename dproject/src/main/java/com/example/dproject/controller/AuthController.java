package com.example.dproject.controller;

import com.example.dproject.dto.AuthRequest;
import com.example.dproject.service.AuthService;
import com.example.dproject.service.UserService;
import jakarta.validation.Valid;
<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
<<<<<<< HEAD
@RequiredArgsConstructor
=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
@RequestMapping("/api/auth")
public class AuthController {


<<<<<<< HEAD
    private final UserService userService;
    private final AuthService authService;
=======
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AuthRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Registration successful");
    }


}