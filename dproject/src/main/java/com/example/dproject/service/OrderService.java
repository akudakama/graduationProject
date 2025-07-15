package com.example.dproject.service;

import com.example.dproject.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId);
    List<Order> getUserOrders(Long userId);


}
