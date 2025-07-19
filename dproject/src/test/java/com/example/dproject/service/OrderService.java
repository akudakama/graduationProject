package com.example.dproject.service;

import com.example.dproject.dto.OrderDto;
import com.example.dproject.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId);
    List<OrderDto> getUserOrders(Long userId);
    void updateOrderStatus(Long id, String status);
    void deleteOrder(Long id);
    List<OrderDto> getAllOrders();
}
