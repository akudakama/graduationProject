package com.example.dproject.service.impl;

import com.example.dproject.entity.Cart;
import com.example.dproject.entity.CartItem;
import com.example.dproject.entity.Product;
import com.example.dproject.repository.CartItemRepository;
import com.example.dproject.repository.CartRepository;
import com.example.dproject.repository.ProductRepository;
import com.example.dproject.repository.UserRepository;
import com.example.dproject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public List<CartItem> getCartItems(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        return cart.getItems();
    }

    @Override
    public CartItem addToCart(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        cartItemRepository.deleteAll(cart.getItems());
    }
}
