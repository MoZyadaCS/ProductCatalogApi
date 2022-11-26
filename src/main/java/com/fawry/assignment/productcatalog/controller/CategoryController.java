package com.fawry.assignment.productcatalog.controller;

import com.fawry.assignment.productcatalog.dto.CategoryRequestDto;
import com.fawry.assignment.productcatalog.dto.CategoryResponseDto;
import com.fawry.assignment.productcatalog.mapper.CategoryMapper;
import com.fawry.assignment.productcatalog.model.Category;
import com.fawry.assignment.productcatalog.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponseDto addCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        return CategoryMapper.INSTANCE.toResponseDto(this.categoryService.addCategory(CategoryMapper.INSTANCE.toCategory(categoryRequestDto)));
    }

    @GetMapping
    public List<CategoryResponseDto> getAllCategories(){
        List<CategoryResponseDto> responseDtos = new ArrayList<>();
        this.categoryService.getAll().forEach(category -> responseDtos.add(CategoryMapper.INSTANCE.toResponseDto(category)));
        return responseDtos;
    }
}
