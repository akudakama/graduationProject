package com.example.dproject.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.DecimalMin;
=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private Long id;

    @NotBlank(message = "Product name must not be blank")
    private String name;

    private String description;

    @NotNull(message = "Price must not be null")
<<<<<<< HEAD
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be non-negative")
=======
    @Min(value = 0, message = "Price must be non-negative")
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
    private BigDecimal price;

    private String imageUrl;

    @NotNull(message = "Stock quantity must not be null")
    @Min(value = 0, message = "Stock quantity must be non-negative")
    private Integer stockQuantity;

    @NotNull(message = "Category ID must not be null")
    private Long categoryId;
}
