package com.example.dproject.service;

import com.example.dproject.entity.User;

import java.util.Optional;

public interface UserService {
    User getUserById(Long id);
    User updateUser(Long id, User updated);
}
