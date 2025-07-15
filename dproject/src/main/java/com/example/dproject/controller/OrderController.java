package com.example.dproject.controller;

import com.example.dproject.entity.Order;
import com.example.dproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    @PostMapping("/{userId}/place")
    public Order placeOrder(@PathVariable Long userId) {
        return orderService.placeOrder(userId);
    }
}
