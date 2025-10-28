package com.example.dproject.config;

import com.example.dproject.repository.UserRepository;
<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
<<<<<<< HEAD
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
=======
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.dproject.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}
