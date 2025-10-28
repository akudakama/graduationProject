package com.example.dproject.service;

import com.example.dproject.dto.CartDto;
import com.example.dproject.entity.CartItem;

import java.util.List;

public interface CartService {
    public CartItem addToCart(Long userId, Long productId, int quantity);
    public List<CartItem> getCartItems(Long userId);

    CartDto getCartDto(Long userId);

    public CartItem updateCartItem(Long userId, Long itemId, int quantity);
    public void removeItem(Long userId, Long itemId);
    public void clearCart(Long userId);

}
