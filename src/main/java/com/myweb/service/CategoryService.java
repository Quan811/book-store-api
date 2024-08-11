package com.myweb.service;

import com.myweb.dto.CartDTO;
import com.myweb.dto.CategoryDTO;
import com.myweb.entity.Cart;
import com.myweb.entity.Category;
import com.myweb.mapper.CategoryMapper;
import com.myweb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found"));
    }

    public Category createCategory(CategoryDTO categoryDTO){
        Category category = categoryMapper.toCategory(categoryDTO);
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO){
        Category category = getCategoryById(categoryId);
        categoryMapper.updateCategory(categoryDTO, category);
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId){
        if(categoryRepository.findById(categoryId).isPresent()){
            categoryRepository.deleteById(categoryId);
        }
        else{
            throw new RuntimeException("Category not found");
        }
    }
}
