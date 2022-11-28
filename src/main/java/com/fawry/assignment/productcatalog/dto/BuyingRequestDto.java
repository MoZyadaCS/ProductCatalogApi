package com.fawry.assignment.productcatalog.dto;

import com.fawry.assignment.productcatalog.model.Order;
import lombok.Data;

@Data
public class BuyingRequestDto {

    private Order order;
}
