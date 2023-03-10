package com.exam.service;

import com.exam.model.exam.Category;
import java.util.List;

public interface CategoryService{
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public List<Category> getAllCategories();
    public Category getCategoryById(int categoryId);
    public void deleteCategory(int categoryId);
}