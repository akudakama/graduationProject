package com.example.dproject.service.impl;

import com.example.dproject.dto.CartDto;
import com.example.dproject.dto.CartItemDto;
import com.example.dproject.entity.Cart;
import com.example.dproject.entity.CartItem;
import com.example.dproject.entity.Product;
import com.example.dproject.entity.User;
import com.example.dproject.repository.CartItemRepository;
import com.example.dproject.repository.CartRepository;
import com.example.dproject.repository.ProductRepository;
import com.example.dproject.repository.UserRepository;
import com.example.dproject.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public List<CartItem> getCartItems(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getItems();
    }

    @Override
    @Transactional(readOnly = true)
    public CartDto getCartDto(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Cart cart = user.getCart();
        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart is empty");
        }

        CartDto dto = new CartDto();
        dto.setId(cart.getId());
        dto.setUserId(userId);

        List<CartItemDto> items = cart.getItems().stream().map(item -> {
            CartItemDto i = new CartItemDto();
            i.setId(item.getId());
            i.setProductId(item.getProduct().getId());
            i.setProductName(item.getProduct().getName());
            i.setImageUrl(item.getProduct().getImageUrl());
            i.setQuantity(item.getQuantity());
            i.setPriceAtPurchase(item.getPriceAtPurchase());
            return i;
        }).toList();

        dto.setItems(items);

        BigDecimal total = items.stream()
                .map(i -> i.getPriceAtPurchase().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        dto.setTotalAmount(total);

        return dto;
    }


    @Override
    @Transactional
    public CartItem updateCartItem(Long userId, Long itemId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("This item does not belong to the user");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    @Override
    @Transactional
    public void removeItem(Long userId, Long itemId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("This item does not belong to the user");
        }

        cartItemRepository.delete(item);
    }



    @Override
    @Transactional
    public CartItem addToCart(Long userId, Long productId, int quantity) {
        if (quantity <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be positive");
        }

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Optional<CartItem> existingItemOpt = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            return cartItemRepository.save(existingItem);
        }

        CartItem newItem = new CartItem();
        newItem.setCart(cart);
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        newItem.setPriceAtPurchase(product.getPrice());

        return cartItemRepository.save(newItem);
    }


    @Override
    @Transactional
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        cartItemRepository.deleteAll(cart.getItems());
    }

}
