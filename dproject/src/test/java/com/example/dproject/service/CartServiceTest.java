package com.example.dproject.service;

import com.example.dproject.dto.CartDto;
import com.example.dproject.entity.*;
import com.example.dproject.repository.*;
import com.example.dproject.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock private CartRepository cartRepository;
    @Mock private CartItemRepository cartItemRepository;
    @Mock private ProductRepository productRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // === ADD TO CART ===

    @Test
    void testAddToCart_NewItem_Success() {
        Long userId = 1L, productId = 100L;
        int quantity = 2;

        Cart cart = new Cart(); cart.setId(10L);
        Product product = new Product(); product.setId(productId); product.setPrice(BigDecimal.valueOf(99.99));

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)).thenReturn(Optional.empty());

        CartItem savedItem = new CartItem();
        savedItem.setId(42L);
        savedItem.setProduct(product);
        savedItem.setCart(cart);
        savedItem.setQuantity(quantity);
        savedItem.setPriceAtPurchase(product.getPrice());

        when(cartItemRepository.save(any(CartItem.class))).thenReturn(savedItem);

        CartItem result = cartService.addToCart(userId, productId, quantity);

        assertEquals(42L, result.getId());
        assertEquals(productId, result.getProduct().getId());
        assertEquals(quantity, result.getQuantity());
    }

    @Test
    void testAddToCart_ExistingItem_UpdatesQuantity() {
        Long userId = 1L, productId = 100L;
        int quantity = 2;

        Cart cart = new Cart(); cart.setId(10L);
        Product product = new Product(); product.setId(productId);
        CartItem existingItem = new CartItem();
        existingItem.setId(42L);
        existingItem.setCart(cart);
        existingItem.setProduct(product);
        existingItem.setQuantity(3);
        existingItem.setPriceAtPurchase(BigDecimal.TEN);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)).thenReturn(Optional.of(existingItem));
        when(cartItemRepository.save(existingItem)).thenReturn(existingItem);

        CartItem result = cartService.addToCart(userId, productId, quantity);

        assertEquals(5, result.getQuantity());
    }

    @Test
    void testAddToCart_InvalidQuantity() {
        assertThrows(ResponseStatusException.class,
                () -> cartService.addToCart(1L, 100L, 0));
    }

    @Test
    void testAddToCart_ProductNotFound() {
        Cart cart = new Cart(); cart.setId(10L);
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(productRepository.findById(123L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> cartService.addToCart(1L, 123L, 1));
    }

    // === UPDATE ITEM ===

    @Test
    void testUpdateCartItem_Success() {
        Cart cart = new Cart(); cart.setId(10L);
        CartItem item = new CartItem(); item.setId(5L); item.setCart(cart); item.setQuantity(1);

        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(cartItemRepository.findById(5L)).thenReturn(Optional.of(item));
        when(cartItemRepository.save(item)).thenReturn(item);

        CartItem updated = cartService.updateCartItem(1L, 5L, 3);
        assertEquals(3, updated.getQuantity());
    }

    @Test
    void testUpdateCartItem_InvalidQuantity() {
        Cart cart = new Cart(); cart.setId(1L);
        CartItem item = new CartItem(); item.setId(5L); item.setCart(cart);

        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(cartItemRepository.findById(5L)).thenReturn(Optional.of(item));

        assertThrows(IllegalArgumentException.class,
                () -> cartService.updateCartItem(1L, 5L, 0));
    }


    @Test
    void testUpdateCartItem_NotOwned() {
        Cart userCart = new Cart(); userCart.setId(1L);
        Cart otherCart = new Cart(); otherCart.setId(2L);
        CartItem item = new CartItem(); item.setId(5L); item.setCart(otherCart);

        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(userCart));
        when(cartItemRepository.findById(5L)).thenReturn(Optional.of(item));

        assertThrows(RuntimeException.class,
                () -> cartService.updateCartItem(1L, 5L, 2));
    }

    // === REMOVE ITEM ===

    @Test
    void testRemoveItem_Success() {
        Cart cart = new Cart(); cart.setId(1L);
        CartItem item = new CartItem(); item.setId(5L); item.setCart(cart);

        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(cartItemRepository.findById(5L)).thenReturn(Optional.of(item));

        cartService.removeItem(1L, 5L);
        verify(cartItemRepository).delete(item);
    }

    @Test
    void testRemoveItem_NotOwned() {
        Cart cart = new Cart(); cart.setId(1L);
        CartItem item = new CartItem(); item.setId(5L);
        Cart otherCart = new Cart(); otherCart.setId(2L);
        item.setCart(otherCart);

        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(cartItemRepository.findById(5L)).thenReturn(Optional.of(item));

        assertThrows(RuntimeException.class,
                () -> cartService.removeItem(1L, 5L));
    }

    // === GET CART DTO ===

    @Test
    void testGetCartDto_Success() {
        Product product = new Product(); product.setId(11L); product.setName("Test"); product.setImageUrl("x"); product.setPrice(BigDecimal.TEN);
        CartItem item = new CartItem(); item.setId(5L); item.setProduct(product); item.setQuantity(2); item.setPriceAtPurchase(BigDecimal.TEN);
        Cart cart = new Cart(); cart.setId(1L); cart.setItems(List.of(item));

        User user = new User(); user.setId(1L); user.setCart(cart);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        CartDto dto = cartService.getCartDto(1L);
        assertEquals(1L, dto.getUserId());
        assertEquals(1, dto.getItems().size());
        assertEquals(BigDecimal.valueOf(20), dto.getTotalAmount());
    }

    @Test
    void testGetCartDto_EmptyCart() {
        User user = new User(); user.setId(1L); user.setCart(new Cart());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertThrows(ResponseStatusException.class,
                () -> cartService.getCartDto(1L));
    }

    // === CLEAR CART ===

    @Test
    void testClearCart() {
        Cart cart = new Cart(); cart.setItems(List.of(new CartItem(), new CartItem()));
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));

        cartService.clearCart(1L);
        verify(cartItemRepository).deleteAll(cart.getItems());
    }
}
