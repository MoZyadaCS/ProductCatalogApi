package com.fawry.assignment.productcatalog.dto;

import lombok.Data;


@Data
public class VariantResponseDto {


    private String nameAr;
    private String nameEn;
    private Byte[] image;
    private String type;
    private int quantity;
    private double price;
    private int limit;
}
