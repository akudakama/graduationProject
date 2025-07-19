package com.example.dproject.service;

import com.example.dproject.dto.AuthRequest;
import com.example.dproject.dto.AuthResponse;
import com.example.dproject.entity.Cart;
import com.example.dproject.entity.Role;
import com.example.dproject.entity.User;
import com.example.dproject.repository.CartRepository;
import com.example.dproject.repository.UserRepository;
import com.example.dproject.config.JwtUtils;
import com.example.dproject.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Successful() {
        AuthRequest request = new AuthRequest("user1", "pass123");

        User user = new User();
        user.setId(42L);
        user.setUsername("user1");
        user.setPassword("encoded");
        user.setRole(Role.CLIENT);

        Authentication mockAuth = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mockAuth);
        when(mockAuth.getPrincipal()).thenReturn(user);
        when(jwtUtils.generateToken(user)).thenReturn("mocked-jwt");
        when(userRepository.findByUsername("user1")).thenReturn(Optional.of(user));

        AuthResponse response = authService.login(request);

        assertEquals("mocked-jwt", response.getToken());
        assertEquals("CLIENT", response.getRole());
        assertEquals(42L, response.getUserId());
    }

    @Test
    void testRegister_Success() {
        AuthRequest request = new AuthRequest("newuser", "1234");

        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("1234")).thenReturn("encoded123");

        authService.register(request);

        verify(userRepository, times(1)).save(any(User.class));
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testRegister_AlreadyExists() {
        AuthRequest request = new AuthRequest("existing", "1234");

        when(userRepository.findByUsername("existing"))
                .thenReturn(Optional.of(new User()));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            authService.register(request);
        });

        assertEquals("User already exist", exception.getMessage());
    }
}
