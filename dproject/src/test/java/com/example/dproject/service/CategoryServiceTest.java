package com.example.dproject.service;

import com.example.dproject.dto.CategoryDto;
import com.example.dproject.entity.Category;
import com.example.dproject.repository.CategoryRepository;
import com.example.dproject.service.impl.CategoryServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    private CategoryRepository categoryRepository;
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void createCategory_ShouldSaveAndReturnCategory() {
        CategoryDto dto = new CategoryDto();
        dto.setName("Electronics");

        Category saved = new Category();
        saved.setId(1L);
        saved.setName("Electronics");

        when(categoryRepository.save(any(Category.class))).thenReturn(saved);

        Category result = categoryService.createCategory(dto);

        assertThat(result.getName()).isEqualTo("Electronics");
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void updateCategory_ShouldUpdateAndReturnCategory() {
        Long id = 1L;
        Category existing = new Category();
        existing.setId(id);
        existing.setName("Old Name");

        CategoryDto dto = new CategoryDto();
        dto.setName("New Name");

        when(categoryRepository.findById(id)).thenReturn(Optional.of(existing));
        when(categoryRepository.save(any(Category.class))).thenAnswer(i -> i.getArgument(0));

        Category updated = categoryService.updateCategory(id, dto);

        assertThat(updated.getName()).isEqualTo("New Name");
        verify(categoryRepository).save(existing);
    }

    @Test
    void updateCategory_ShouldThrowException_WhenNotFound() {
        Long id = 999L;
        CategoryDto dto = new CategoryDto();
        dto.setName("Doesn't matter");

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> categoryService.updateCategory(id, dto))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void deleteCategory_ShouldDelete_WhenExists() {
        Long id = 1L;
        when(categoryRepository.existsById(id)).thenReturn(true);

        categoryService.deleteCategory(id);

        verify(categoryRepository).deleteById(id);
    }

    @Test
    void deleteCategory_ShouldThrowException_WhenNotExists() {
        Long id = 999L;
        when(categoryRepository.existsById(id)).thenReturn(false);

        assertThatThrownBy(() -> categoryService.deleteCategory(id))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
