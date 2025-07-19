package com.example.dproject.service;

import com.example.dproject.dto.ProductDto;
import com.example.dproject.entity.Category;
import com.example.dproject.entity.Product;
import com.example.dproject.repository.CategoryRepository;
import com.example.dproject.repository.ProductRepository;
import com.example.dproject.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct() {
        ProductDto dto = new ProductDto();
        dto.setName("Test Product");
        dto.setDescription("Description");
        dto.setPrice(BigDecimal.valueOf(99.99));
        dto.setStockQuantity(10);
        dto.setCategoryId(1L);

        Category category = new Category();
        category.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Product saved = new Product();
        saved.setId(1L);
        saved.setName(dto.getName());

        when(productRepository.save(any(Product.class))).thenReturn(saved);

        Product result = productService.saveProduct(dto);

        assertThat(result.getName()).isEqualTo("Test Product");
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        Product existing = new Product();
        existing.setId(productId);

        ProductDto dto = new ProductDto();
        dto.setName("Updated Product");
        dto.setDescription("Updated");
        dto.setPrice(BigDecimal.TEN);
        dto.setStockQuantity(5);
        dto.setCategoryId(1L);
        dto.setImageUrl("url");

        Category category = new Category();
        category.setId(1L);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existing));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(existing);

        Product updated = productService.updateProduct(productId, dto);

        assertThat(updated.getName()).isEqualTo("Updated Product");
        verify(productRepository).save(existing);
    }

    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(new Product(), new Product()));
        List<Product> result = productService.getAllProducts();
        assertThat(result).hasSize(2);
    }

    @Test
    void testGetAvailableProducts() {
        when(productRepository.findByStockQuantityGreaterThan(0)).thenReturn(List.of(new Product()));
        List<Product> result = productService.getAvailableProducts();
        assertThat(result).hasSize(1);
    }

    @Test
    void testSearchByName() {
        when(productRepository.findByNameContainingIgnoreCase("Test")).thenReturn(List.of(new Product()));
        List<Product> result = productService.searchByName("Test");
        assertThat(result).isNotEmpty();
    }

    @Test
    void testDeleteProduct() {
        Long id = 1L;
        productService.deleteProduct(id);
        verify(productRepository).deleteById(id);
    }
}
