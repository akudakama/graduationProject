package com.example.dproject.service.impl;

import com.example.dproject.config.JwtUtils;
import com.example.dproject.dto.AuthRequest;
import com.example.dproject.dto.AuthResponse;
import com.example.dproject.entity.Cart;
import com.example.dproject.entity.Role;
import com.example.dproject.entity.User;
import com.example.dproject.repository.CartRepository;
import com.example.dproject.repository.UserRepository;
import com.example.dproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String token = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        return new AuthResponse(token, user.getRole().name(),user.getId());
    }

    @Transactional
    public void register(AuthRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("User already exist");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CLIENT);
        Cart cart = new Cart();
        user.setCart(cart);
        cart.setUser(user);
        userRepository.save(user);
        cartRepository.save(cart);
    }
}

