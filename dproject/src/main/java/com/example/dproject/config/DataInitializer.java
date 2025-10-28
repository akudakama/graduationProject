package com.example.dproject.config;

import com.example.dproject.entity.Cart;
import com.example.dproject.entity.Role;
import com.example.dproject.entity.User;
import com.example.dproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initUsers() {
        return args -> {
            createUserIfNotExists("admin", "1234", Role.ADMIN, "Admin User", "admin@example.com");
            createUserIfNotExists("client", "1234", Role.CLIENT, "Test Client", "client@example.com");
        };
    }

    private void createUserIfNotExists(String username, String rawPassword, Role role, String fullName, String email) {
        if (userRepository.findByUsername(username).isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setRole(role);
            user.setFullName(fullName);
            user.setEmail(email);
            user.setCreatedAt(LocalDateTime.now());

            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);

            userRepository.save(user);
        }
    }

}
