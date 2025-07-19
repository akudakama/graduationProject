package com.example.dproject.controller;

import com.example.dproject.dto.AddToCartRequest;
import com.example.dproject.dto.CartDto;
import com.example.dproject.dto.UpdateCartRequest;
import com.example.dproject.entity.CartItem;
import com.example.dproject.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{userId}/items")
    public ResponseEntity<CartItem> addItem(@PathVariable Long userId,
                                            @Valid @RequestBody AddToCartRequest request) {
        CartItem item = cartService.addToCart(userId, request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartDto(userId));
    }



    @GetMapping("/{userId}/items")
    public ResponseEntity<List<CartItem>> getItems(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }

    @PutMapping("/{userId}/items/{itemId}")
    public ResponseEntity<CartItem> updateItem(@PathVariable Long userId,
                                               @PathVariable Long itemId,
                                               @Valid @RequestBody UpdateCartRequest request) {
        CartItem updated = cartService.updateCartItem(userId, itemId, request.getQuantity());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long userId,
                                           @PathVariable Long itemId) {
        cartService.removeItem(userId, itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/items")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}