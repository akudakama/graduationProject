package com.example.dproject.controller;

import com.example.dproject.dto.OrderDto;
<<<<<<< HEAD
import com.example.dproject.dto.PlaceOrderRequest;
import com.example.dproject.entity.Order;
import com.example.dproject.entity.User;
import com.example.dproject.service.OrderService;
import jakarta.validation.Valid;
=======
import com.example.dproject.entity.Order;
import com.example.dproject.entity.User;
import com.example.dproject.service.OrderService;
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping
    public List<OrderDto> getUserOrders(@AuthenticationPrincipal User user) {
        return orderService.getUserOrders(user.getId());
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/place")
<<<<<<< HEAD
    public ResponseEntity<?> placeOrder(@AuthenticationPrincipal User user,  @Valid @RequestBody PlaceOrderRequest req) {

        return ResponseEntity.ok(orderService.placeOrder(user.getId(), req.getAddressId()));
=======
    public ResponseEntity<?> placeOrder(@AuthenticationPrincipal User user) {

        orderService.placeOrder(user.getId());
        return ResponseEntity.ok().build();
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
    }


}
