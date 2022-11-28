package com.fawry.assignment.productcatalog.dto;

import com.fawry.assignment.productcatalog.model.Product;
import lombok.Data;

@Data
public class VariantRequestDto {

    private String type;
    private int quantity;
    private double price;
    private Product product;
    private int limit;

}
