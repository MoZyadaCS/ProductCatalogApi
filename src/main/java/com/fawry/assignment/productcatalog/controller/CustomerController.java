package com.fawry.assignment.productcatalog.controller;

import com.fawry.assignment.productcatalog.dto.CustomerRequestDto;
import com.fawry.assignment.productcatalog.dto.CustomerResponseDto;
import com.fawry.assignment.productcatalog.mapper.CustomerMapper;
import com.fawry.assignment.productcatalog.model.Customer;
import com.fawry.assignment.productcatalog.model.Login;
import com.fawry.assignment.productcatalog.service.CustomerService;
import com.fawry.assignment.productcatalog.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final LoginService loginService;

    public CustomerController(CustomerService customerService, LoginService loginService) {
        this.customerService = customerService;
        this.loginService = loginService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto requestDto){

        Customer customer = CustomerMapper.INSTANCE.toCustomer(requestDto);
        Login login = CustomerMapper.INSTANCE.toLogin(requestDto);
        this.loginService.addLogin(login);
        customer.setLogin(login);
        this.customerService.addCustomer(customer);
        login.setCustomer(customer);
        this.loginService.addLogin(login);
        return CustomerMapper.INSTANCE.toResponseDto(login,customer);

    }
}
