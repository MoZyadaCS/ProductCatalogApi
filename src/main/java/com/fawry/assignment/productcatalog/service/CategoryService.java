package com.fawry.assignment.productcatalog.service;


import com.fawry.assignment.productcatalog.dto.CategoryRequestDto;
import com.fawry.assignment.productcatalog.dto.CategoryResponseDto;
import com.fawry.assignment.productcatalog.mapper.CategoryMapper;
import com.fawry.assignment.productcatalog.model.Category;
import com.fawry.assignment.productcatalog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category addCategory(Category category){
         return this.categoryRepository.save(category);
    }

    public Category findByName(String name){
        return this.categoryRepository.findCategoryByName(name);
    }

    public List<Category> getAll(){
        return this.categoryRepository.findAll();
    }

}
