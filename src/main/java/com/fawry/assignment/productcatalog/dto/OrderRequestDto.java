package com.fawry.assignment.productcatalog.dto;

import com.fawry.assignment.productcatalog.model.Customer;
import com.fawry.assignment.productcatalog.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {

    private Customer customer;
    private double totalPrice;
    private List<Product> products;

}
