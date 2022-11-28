package com.fawry.assignment.productcatalog.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {

    private String customerName;
    private double totalPrice;
    private List<String> variantNames;
    private int limit;
    private boolean isBought;

}
