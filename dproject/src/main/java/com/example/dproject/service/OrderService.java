package com.example.dproject.service;

import com.example.dproject.dto.OrderDto;
import com.example.dproject.entity.Order;

import java.util.List;

public interface OrderService {
<<<<<<< HEAD
    OrderDto placeOrder(Long userId, Long addressId);
=======
    OrderDto placeOrder(Long userId);
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
    List<OrderDto> getUserOrders(Long userId);
    void updateOrderStatus(Long id, String status);
    void deleteOrder(Long id);
    List<OrderDto> getAllOrders();
}
