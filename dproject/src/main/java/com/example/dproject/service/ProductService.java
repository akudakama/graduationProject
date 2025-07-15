package com.example.dproject.service;

import com.example.dproject.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    // Отримати всі товари (можливо, з пагінацією в майбутньому)
    List<Product> getAllProducts();

    // Отримати товар по ID
    Optional<Product> getProductById(Long id);

    // Створити або оновити товар
    Product saveProduct(Product product);

    // Видалити товар
    void deleteProduct(Long id);

    // Пошук за назвою (наприклад, для фільтрації на фронті)
    List<Product> searchByName(String name);

    // Фільтрація за категорією
    List<Product> getByCategoryId(Long categoryId);

    // Товари з наявністю на складі
    List<Product> getAvailableProducts();

    // (Опційно) Топ популярних або новинок
    List<Product> getTopProducts(int limit);
}
