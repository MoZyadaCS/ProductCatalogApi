package com.fawry.assignment.productcatalog.mapper;

import com.fawry.assignment.productcatalog.dto.OrderRequestDto;
import com.fawry.assignment.productcatalog.dto.OrderResponseDto;
import com.fawry.assignment.productcatalog.model.Order;
import com.fawry.assignment.productcatalog.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    Order toOrder(OrderRequestDto requestDto);



    default OrderResponseDto toResponseDto(Order order){
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setCustomerName(order.getCustomer().getName());
        responseDto.setTotalPrice(order.getTotalPrice());
        responseDto.setProducts(order.getProducts().stream().map(Product::getNameEn).toList());
        return responseDto;
    }
}
