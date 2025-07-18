package com.example.dproject.controller;

import com.example.dproject.dto.OrderDto;
import com.example.dproject.entity.Order;
import com.example.dproject.entity.User;
import com.example.dproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getUserOrders(@AuthenticationPrincipal User user) {
        return orderService.getUserOrders(user.getId());
    }

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@AuthenticationPrincipal User user) {
        System.out.println("Authenticated user: " + user.getId());

        orderService.placeOrder(user.getId());
        return ResponseEntity.ok().build();
    }


}
