package com.fawry.assignment.productcatalog.mapper;

import com.fawry.assignment.productcatalog.dto.CustomerRequestDto;
import com.fawry.assignment.productcatalog.dto.CustomerResponseDto;
import com.fawry.assignment.productcatalog.model.Customer;
import com.fawry.assignment.productcatalog.model.Login;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponseDto toResponseDto(Login login, Customer customer);

    Customer toCustomer(CustomerRequestDto requestDto);

    Login toLogin(CustomerRequestDto requestDto);
}
