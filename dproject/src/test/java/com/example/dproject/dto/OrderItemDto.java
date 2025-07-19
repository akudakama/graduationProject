package com.example.dproject.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Long productId;
    private String productName;
    private int quantity;
    private String imageUrl;
    private BigDecimal priceAtPurchase;
}
