package com.exam.service.impl;

import com.exam.model.exam.Category;
import com.exam.repository.CategoryRepository;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public Category updateCategory(Category category){
       return categoryRepository.save(category);
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public Category getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException());
    }
    @Override
    public void deleteCategory(int categoryId){
        Category deleteCategoryById =categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException());
        categoryRepository.delete(deleteCategoryById);
    }
}