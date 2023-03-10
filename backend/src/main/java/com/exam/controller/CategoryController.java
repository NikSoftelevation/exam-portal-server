package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController{
    @Autowired
    private CategoryService categoryService;
    //creating category
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
       return new ResponseEntity<>(categoryService.addCategory(category),HttpStatus.CREATED);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") int categoryId){
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories(){
    return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }
    @PutMapping("/")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.updateCategory(category),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategoryByCategoryId(@PathVariable("categoryId") int categoryId){
     categoryService.deleteCategory(categoryId);
     return new ResponseEntity<>(HttpStatus.GONE);
    }
}