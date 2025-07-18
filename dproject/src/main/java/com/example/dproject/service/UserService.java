package com.example.dproject.service;

import com.example.dproject.dto.UserDto;
import com.example.dproject.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUserById(Long id);
    User updateUser(Long id, User updated);
    List<UserDto> getAllUserDtos();
    void updateUserRole(Long id, String newRole);
    void deleteUser(Long id);

}
