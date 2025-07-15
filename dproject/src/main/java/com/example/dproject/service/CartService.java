package com.example.dproject.service;

import com.example.dproject.entity.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getCartItems(Long userId);
    CartItem addToCart(Long userId, Long productId, int quantity);
    void clearCart(Long userId);

}
