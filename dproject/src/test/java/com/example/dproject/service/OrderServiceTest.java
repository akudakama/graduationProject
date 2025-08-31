package com.example.dproject.service;

import com.example.dproject.dto.OrderDto;
import com.example.dproject.entity.*;
import com.example.dproject.repository.*;
import com.example.dproject.service.impl.OrderServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock private OrderRepository orderRepository;
    @Mock private UserRepository userRepository;
    @Mock private CartRepository cartRepository;
    @Mock private CartItemRepository cartItemRepository;

    @InjectMocks private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // === PLACE ORDER ===

 /*   @Test
    void testPlaceOrder_Success() {
        Long userId = 1L;

        User user = new User(); user.setId(userId);
        Product product = new Product(); product.setId(101L); product.setPrice(BigDecimal.TEN);

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(2);

        Cart cart = new Cart();
        cart.setItems(new ArrayList<>(List.of(cartItem)));

        Order savedOrder = new Order();
        savedOrder.setId(999L);
        savedOrder.setTotalAmount(BigDecimal.valueOf(20));
        savedOrder.setStatus(OrderState.NEW);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        Order result = orderService.placeOrder(userId);

        assertEquals(999L, result.getId());
        assertEquals(OrderState.NEW, result.getStatus());
        assertEquals(BigDecimal.valueOf(20), result.getTotalAmount());
    }*/

    @Test
    void testPlaceOrder_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> orderService.placeOrder(1L));
    }

    @Test
    void testPlaceOrder_CartNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> orderService.placeOrder(1L));
    }

    @Test
    void testPlaceOrder_EmptyCart() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        Cart cart = new Cart(); cart.setItems(List.of());
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));

        assertThrows(ResponseStatusException.class,
                () -> orderService.placeOrder(1L));
    }


    @Test
    void testUpdateOrderStatus_InvalidStatus() {
        Order order = new Order(); order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        assertThrows(IllegalArgumentException.class,
                () -> orderService.updateOrderStatus(1L, "WRONG_STATUS"));
    }

    @Test
    void testUpdateOrderStatus_OrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> orderService.updateOrderStatus(1L, "NEW"));
    }

    // === DELETE ===

    @Test
    void testDeleteOrder_Success() {
        when(orderRepository.existsById(1L)).thenReturn(true);

        orderService.deleteOrder(1L);

        verify(orderRepository).deleteById(1L);
    }

    @Test
    void testDeleteOrder_NotFound() {
        when(orderRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class,
                () -> orderService.deleteOrder(1L));
    }

    // === GET ===

    @Test
    void testGetUserOrders() {
        Order order = new Order(); order.setId(1L);
        order.setStatus(OrderState.NEW);
        when(orderRepository.findByUserId(1L)).thenReturn(List.of(order));

        List<OrderDto> result = orderService.getUserOrders(1L);
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllOrders() {
        Order order = new Order(); order.setId(1L);
        order.setStatus(OrderState.NEW);
        when(orderRepository.findAll()).thenReturn(List.of(order));

        List<OrderDto> result = orderService.getAllOrders();
        assertEquals(1, result.size());
    }
}
