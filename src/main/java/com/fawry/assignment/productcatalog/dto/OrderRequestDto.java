package com.fawry.assignment.productcatalog.dto;

import com.fawry.assignment.productcatalog.model.Customer;
import com.fawry.assignment.productcatalog.model.Variant;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private Customer customer;
    private List<Variant> variants;
}
