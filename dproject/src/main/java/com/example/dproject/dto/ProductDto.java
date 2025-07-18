package com.example.dproject.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private int stockQuantity;
    private Long categoryId;
}
