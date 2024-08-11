package com.myweb.controller;

import com.cloudinary.Api;
import com.myweb.dto.ApiResponse;
import com.myweb.dto.CategoryDTO;
import com.myweb.entity.Cart;
import com.myweb.entity.Category;
import com.myweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    ApiResponse<List<Category>> getAllCategory(){
        return ApiResponse.<List<Category>>builder()
                .success(true)
                .result(categoryService.getAllCategory())
                .build();
    }

    @GetMapping("/{categoryId}")
    ApiResponse<Category> getCategoryById(@PathVariable Long categoryId){
        return ApiResponse.<Category>builder()
                .success(true)
                .result(categoryService.getCategoryById(categoryId))
                .build();
    }

    @PostMapping("/new-category")
    ApiResponse<Category> createCategory(@RequestBody CategoryDTO categoryDTO){
        return ApiResponse.<Category>builder()
                .success(true)
                .result(categoryService.createCategory(categoryDTO))
                .build();
    }

    @PutMapping("/update/{categoryId}")
    ApiResponse<Category> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO  categoryDTO){
        return ApiResponse.<Category>builder()
                .success(true)
                .result(categoryService.updateCategory(categoryId, categoryDTO))
                .build();
    }

    @DeleteMapping("/{categoryId}")
    ApiResponse<Category> deleteCategory(@PathVariable Long categoryId ){
        categoryService.deleteCategory(categoryId);
        return ApiResponse.<Category>builder()
                .success(true)
                .message("Delete category successfully")
                .build();
    }

}
