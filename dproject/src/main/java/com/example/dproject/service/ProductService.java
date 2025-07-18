package com.example.dproject.service;

import com.example.dproject.dto.ProductDto;
import com.example.dproject.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product saveProduct(ProductDto dto);
    void deleteProduct(Long id);
    List<Product> searchByName(String name);
    List<Product> getByCategoryId(Long categoryId);
    List<Product> getAvailableProducts();
    List<Product> getTopProducts(int limit);
    Product updateProduct(Long id, ProductDto updated);
}
