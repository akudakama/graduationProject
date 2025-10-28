package com.example.dproject.integration;

import com.example.dproject.dto.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
<<<<<<< HEAD
@RequiredArgsConstructor
public class AuthIntegrationTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;
=======
public class AuthIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d

    @Test
    void testRegisterAndLogin() throws Exception {
        AuthRequest request = new AuthRequest("testuser","password123");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.role").value("CLIENT"))
                .andExpect(jsonPath("$.userId").isNumber());
    }
}
