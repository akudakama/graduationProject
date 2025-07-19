package com.example.dproject.service;

import com.example.dproject.dto.UserDto;
import com.example.dproject.entity.Role;
import com.example.dproject.entity.User;
import com.example.dproject.repository.UserRepository;
import com.example.dproject.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById_Success() {
        User user = new User();
        user.setId(1L);
        user.setUsername("john");
        user.setEmail("john@example.com");
        user.setFullName("John Doe");
        user.setRole(Role.CLIENT);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDto result = userService.getUserById(1L);

        assertThat(result.getUsername()).isEqualTo("john");
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("User not found");
    }

    @Test
    void testUpdateUser_Success() {
        User user = new User();
        user.setId(1L);
        user.setEmail("old@mail.com");
        user.setFullName("Old Name");
        user.setUsername("user");
        user.setRole(Role.CLIENT);

        User updated = new User();
        updated.setEmail("new@mail.com");
        updated.setFullName("New Name");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);

        UserDto result = userService.updateUser(1L, updated);

        assertThat(result.getEmail()).isEqualTo("new@mail.com");
        assertThat(result.getFullName()).isEqualTo("New Name");
    }

    @Test
    void testGetAllUserDtos() {
        User u1 = new User();
        u1.setId(1L);
        u1.setUsername("user1");
        u1.setEmail("1@mail.com");
        u1.setFullName("One");
        u1.setRole(Role.ADMIN);

        User u2 = new User();
        u2.setId(2L);
        u2.setUsername("user2");
        u2.setEmail("2@mail.com");
        u2.setFullName("Two");
        u2.setRole(Role.CLIENT);

        when(userRepository.findAll()).thenReturn(List.of(u1, u2));

        List<UserDto> result = userService.getAllUserDtos();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getUsername()).isEqualTo("user1");
    }

    @Test
    void testUpdateUserRole_Success() {
        User user = new User();
        user.setId(1L);
        user.setRole(Role.CLIENT);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.updateUserRole(1L, "admin");

        assertThat(user.getRole()).isEqualTo(Role.ADMIN);
    }

    @Test
    void testUpdateUserRole_InvalidRole() {
        User user = new User();
        user.setId(1L);
        user.setRole(Role.CLIENT);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertThatThrownBy(() -> userService.updateUserRole(1L, "invalid"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid role");
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepository.existsById(1L)).thenReturn(true);
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void testDeleteUser_NotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> userService.deleteUser(1L))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
