package com.example.dproject.mapper;

import com.example.dproject.dto.OrderDto;
import com.example.dproject.dto.OrderItemDto;
import com.example.dproject.entity.Order;
import com.example.dproject.entity.OrderItem;

import java.util.List;

public class OrderMapper {

    public static OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus().name());
        dto.setTotalAmount(order.getTotalAmount());

        List<OrderItemDto> itemDtos = order.getItems().stream().map(OrderMapper::toDto).toList();
        dto.setItems(itemDtos);

        return dto;
    }

    private static OrderItemDto toDto(OrderItem item) {
        OrderItemDto dto = new OrderItemDto();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setPriceAtPurchase(item.getPriceAtPurchase());
        return dto;
    }
}
