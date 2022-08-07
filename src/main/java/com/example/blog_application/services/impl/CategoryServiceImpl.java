package com.example.blog_application.services.impl;

import com.example.blog_application.entities.Category;

import com.example.blog_application.exceptions.ResourceNotFoundException;
import com.example.blog_application.payloads.CategoryDto;

import com.example.blog_application.repositories.CategoryRepo;
import com.example.blog_application.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto,Category.class);
        Category addedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updateCat = this.categoryRepo.save(cat);

        return this.modelMapper.map(updateCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getSingleCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id",categoryId));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public Set<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        Set<CategoryDto> categoryDtos = categories.stream().map((cat) -> this.categoryTodto(cat)).collect(Collectors.toSet());
        return categoryDtos;
    }

    private CategoryDto categoryTodto (Category category){

        CategoryDto categoryDto = this.modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }

}
