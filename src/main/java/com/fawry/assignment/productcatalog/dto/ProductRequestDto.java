package com.fawry.assignment.productcatalog.dto;

import com.fawry.assignment.productcatalog.model.Category;
import com.fawry.assignment.productcatalog.model.Variant;
import lombok.Data;


import java.util.List;

@Data
public class ProductRequestDto {
    private Long id;
    private String nameAr;
    private String nameEn;
    private Byte[] image;
    private int buyingCount;
    private List<Variant> variants;
    private Category category;

}
