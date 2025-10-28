package com.example.dproject.integration;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
<<<<<<< HEAD
@RequiredArgsConstructor
public class PublicEndpointsTest {

    private final MockMvc mockMvc;
=======
public class PublicEndpointsTest {

    @Autowired
    private MockMvc mockMvc;
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d

    @Test
    public void testGetAllProducts_PublicAccess() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCart_UnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/api/cart/1"))
                .andExpect(status().isForbidden());
    }
}
