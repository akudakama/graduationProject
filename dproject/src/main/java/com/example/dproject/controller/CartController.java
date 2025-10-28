package com.example.dproject.controller;

import com.example.dproject.dto.AddToCartRequest;
import com.example.dproject.dto.CartDto;
import com.example.dproject.dto.UpdateCartRequest;
import com.example.dproject.entity.CartItem;
import com.example.dproject.entity.User;
import com.example.dproject.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/{userId}/items")
    public ResponseEntity<CartItem> addItem(@AuthenticationPrincipal User me,
                                            @PathVariable Long userId,
                                            @Valid @RequestBody AddToCartRequest request) {
        CartItem item = cartService.addToCart(me.getId(), request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(item);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@AuthenticationPrincipal User me,
                                           @PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartDto(me.getId()));
    }


    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/{userId}/items")
    public ResponseEntity<List<CartItem>> getItems(@AuthenticationPrincipal User me,
                                                   @PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartItems(me.getId()));
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PutMapping("/{userId}/items/{itemId}")
    public ResponseEntity<CartItem> updateItem(@AuthenticationPrincipal User me,
                                               @PathVariable Long userId,
                                               @PathVariable Long itemId,
                                               @Valid @RequestBody UpdateCartRequest request) {
        CartItem updated = cartService.updateCartItem(me.getId(), itemId, request.getQuantity());
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<Void> removeItem(@AuthenticationPrincipal User me,
                                           @PathVariable Long userId,
                                           @PathVariable Long itemId) {
        cartService.removeItem(me.getId(), itemId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping("/{userId}/items")
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal User me,
                                          @PathVariable Long userId) {
        cartService.clearCart(me.getId());
        return ResponseEntity.noContent().build();
    }
}