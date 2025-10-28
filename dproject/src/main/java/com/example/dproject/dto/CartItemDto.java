package com.example.dproject.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private Long id;
    private Long productId;
    private String productName;
    private String imageUrl;
    private int quantity;
    private BigDecimal priceAtPurchase;
}
