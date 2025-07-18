package com.example.dproject.service;

import com.example.dproject.dto.CategoryDto;
import com.example.dproject.entity.Category;

public interface CategoryService {
    Category createCategory(CategoryDto category);
    Category updateCategory(Long id, CategoryDto updated);
    void deleteCategory(Long id);

}
