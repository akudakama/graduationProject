package com.example.dproject.service.impl;

import com.example.dproject.dto.UserDto;
import com.example.dproject.entity.Role;
import com.example.dproject.entity.User;
import com.example.dproject.repository.UserRepository;
import com.example.dproject.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return new UserDto(user.getId(),user.getUsername(),user.getEmail(), user.getFullName(), user.getRole().name());
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, User updated) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        user.setEmail(updated.getEmail());
        user.setFullName(updated.getFullName());
        userRepository.save(user);
        return new UserDto(user.getId(),user.getUsername(),user.getEmail(), user.getFullName(), user.getRole().name());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUserDtos() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getFullName(),
                        user.getRole().name()
                ))
                .toList();
    }

    @Override
    @Transactional
    public void updateUserRole(Long id, String newRole) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        try {
            Role role = Role.valueOf(newRole.toUpperCase());
            user.setRole(role);
            userRepository.save(user);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + newRole);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
