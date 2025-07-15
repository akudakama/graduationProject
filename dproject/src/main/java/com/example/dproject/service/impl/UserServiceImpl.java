package com.example.dproject.service.impl;

import com.example.dproject.entity.User;
import com.example.dproject.repository.UserRepository;
import com.example.dproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User updateUser(Long id, User updated) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEmail(updated.getEmail());
        user.setFullName(updated.getFullName());
        return userRepository.save(user);
    }
}
