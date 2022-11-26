package com.fawry.assignment.productcatalog.mapper;

import com.fawry.assignment.productcatalog.dto.CategoryRequestDto;
import com.fawry.assignment.productcatalog.dto.CategoryResponseDto;
import com.fawry.assignment.productcatalog.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryRequestDto requestDto);

    CategoryResponseDto toResponseDto(Category category);

}
