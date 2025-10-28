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
<<<<<<< HEAD
    @Mock private AddressRepository addressRepository;
=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d

    @InjectMocks private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

<<<<<<< HEAD

    @Test
    void testPlaceOrder_Success() {
        Long userId = 1L;
        Long addressId = 77L;

        User user = new User();
        user.setId(userId);

        Address address = new Address();
        address.setId(addressId);
        address.setUser(user);
        address.setFirstName("Ivan");
        address.setLastName("Ivanov");
        address.setPhone("+380991112233");
        address.setLine1("Khreshchatyk 1");
        address.setLine2(null);
        address.setCity("Kyiv");
        address.setRegion("Kyiv");
        address.setPostalCode("01001");
        address.setCountryCode("UA");

        Product product = new Product();
        product.setId(101L);
        product.setPrice(BigDecimal.TEN);
=======
    // === PLACE ORDER ===

 /*   @Test
    void testPlaceOrder_Success() {
        Long userId = 1L;

        User user = new User(); user.setId(userId);
        Product product = new Product(); product.setId(101L); product.setPrice(BigDecimal.TEN);
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(2);

        Cart cart = new Cart();
        cart.setItems(new ArrayList<>(List.of(cartItem)));

<<<<<<< HEAD
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));


        when(orderRepository.save(any(Order.class))).thenAnswer(inv -> {
            Order o = inv.getArgument(0);
            o.setId(999L);
            return o;
        });


        OrderDto result = orderService.placeOrder(userId, addressId);


        assertNotNull(result);
        assertEquals(999L, result.getId());
        assertEquals("NEW", result.getStatus());
        assertEquals(BigDecimal.valueOf(20), result.getTotalAmount());

        assertTrue(cart.getItems().isEmpty());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testPlaceOrder_UserNotFound() {
        Long addressId = 77L;
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> orderService.placeOrder(1L, addressId));
=======
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
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
    }

    @Test
    void testPlaceOrder_CartNotFound() {
<<<<<<< HEAD
        Long addressId = 77L;
=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
<<<<<<< HEAD
                () -> orderService.placeOrder(1L, addressId));
=======
                () -> orderService.placeOrder(1L));
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
    }

    @Test
    void testPlaceOrder_EmptyCart() {
<<<<<<< HEAD
        Long addressId = 77L;
=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        Cart cart = new Cart(); cart.setItems(List.of());
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));

        assertThrows(ResponseStatusException.class,
<<<<<<< HEAD
                () -> orderService.placeOrder(1L, addressId));
    }

    @Test
    void testPlaceOrder_InvalidAddress() {
        Long userId = 1L;
        Long addressId = 77L;

        User user = new User(); user.setId(userId);
        Cart cart = new Cart();
        CartItem it = new CartItem();
        Product p = new Product(); p.setPrice(BigDecimal.ONE);
        it.setProduct(p); it.setQuantity(1);
        cart.setItems(new ArrayList<>(List.of(it)));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));


        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> orderService.placeOrder(userId, addressId));
    }

    @Test
    void testPlaceOrder_AddressBelongsToAnotherUser() {
        Long userId = 1L;
        Long addressId = 77L;

        User user = new User(); user.setId(userId);
        User other = new User(); other.setId(2L);

        Cart cart = new Cart();
        CartItem it = new CartItem();
        Product p = new Product(); p.setPrice(BigDecimal.ONE);
        it.setProduct(p); it.setQuantity(1);
        cart.setItems(new ArrayList<>(List.of(it)));

        Address address = new Address();
        address.setId(addressId);
        address.setUser(other);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        assertThrows(ResponseStatusException.class,
                () -> orderService.placeOrder(userId, addressId));
=======
                () -> orderService.placeOrder(1L));
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
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

<<<<<<< HEAD
=======
    // === DELETE ===
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d

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

<<<<<<< HEAD
=======
    // === GET ===
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d

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
