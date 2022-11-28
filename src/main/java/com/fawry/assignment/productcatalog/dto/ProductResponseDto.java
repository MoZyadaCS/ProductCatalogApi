package com.fawry.assignment.productcatalog.dto;


import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDto {

    private String nameAr;
    private String nameEn;
    private Byte[] image;
    private int buyingCount;
    private List<VariantResponseDto> variants;
    private CategoryResponseDto category;
}
