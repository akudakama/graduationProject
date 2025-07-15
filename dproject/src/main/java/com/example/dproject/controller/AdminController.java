package com.example.dproject.controller;

import com.example.dproject.entity.*;
import com.example.dproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    // ========== PRODUCT CRUD ==========

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updated) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(updated.getName());
        product.setDescription(updated.getDescription());
        product.setPrice(updated.getPrice());
        product.setStockQuantity(updated.getStockQuantity());
        product.setCategory(updated.getCategory());
        return productRepository.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    // ========== CATEGORY CRUD ==========

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category updated) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(updated.getName());
        return categoryRepository.save(category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
    }

    // ========== USER ROLE MANAGEMENT ==========

    @PutMapping("/users/{id}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            Role newRole = Role.valueOf(body.get("role").toUpperCase());
            User user = userRepository.findById(id).orElseThrow();
            user.setRole(newRole);
            return ResponseEntity.ok(userRepository.save(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid role: " + body.get("role"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("General error: " + e.getMessage());
        }
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    // ========== ORDER MANAGEMENT ==========

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            Order order = orderRepository.findById(id).orElseThrow();
            OrderState newStatus = OrderState.valueOf(body.get("status").toUpperCase());
            order.setStatus(newStatus);
            return ResponseEntity.ok(orderRepository.save(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid order status: " + body.get("status"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("General error: " + e.getMessage());
        }
    }


    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }

}
