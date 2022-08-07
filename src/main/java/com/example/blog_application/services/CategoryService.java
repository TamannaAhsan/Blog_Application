package com.example.blog_application.services;

import com.example.blog_application.payloads.CategoryDto;

import java.util.Set;

public interface CategoryService {

    CategoryDto createCategory (CategoryDto categoryDto);
    CategoryDto updateCategory (CategoryDto categoryDto, Integer categoryId);
    void deleteCategory (Integer categoryId);
    CategoryDto getSingleCategory (Integer categoryId);
    Set<CategoryDto> getAllCategories ();

}
