package com.fawry.assignment.productcatalog.controller;

import com.fawry.assignment.productcatalog.dto.OrderRequestDto;
import com.fawry.assignment.productcatalog.dto.OrderResponseDto;
import com.fawry.assignment.productcatalog.mapper.OrderMapper;
import com.fawry.assignment.productcatalog.model.Order;
import com.fawry.assignment.productcatalog.model.Product;
import com.fawry.assignment.productcatalog.model.Variant;
import com.fawry.assignment.productcatalog.service.OrderService;
import com.fawry.assignment.productcatalog.service.ProductService;
import com.fawry.assignment.productcatalog.service.VariantService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    private final ProductService productService;

    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @PostMapping
    public OrderResponseDto addOrder(@RequestBody OrderRequestDto requestDto){
        Order order = OrderMapper.INSTANCE.toOrder(requestDto);
        List<Product> products = order.getProducts().stream().map(product -> productService.getById(product.getId())).toList();
        order.setProducts(products);
        products.forEach(product -> product.setOrders(List.of(order)));
        return OrderMapper.INSTANCE.toResponseDto(this.orderService.add(order));
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrder(@PathVariable Long id){
        return OrderMapper.INSTANCE.toResponseDto(this.orderService.getOrderById(id));
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders(){
        List<OrderResponseDto> responseDtos = new ArrayList<>();
        this.orderService.getAll().forEach(order -> responseDtos.add(OrderMapper.INSTANCE.toResponseDto(order)));
        return responseDtos;
    }

    @GetMapping("/customer/{id}")
    public List<OrderResponseDto> getAllOrdersForCustomer(@PathVariable Long id){
        List<OrderResponseDto> responseDtos = new ArrayList<>();
        this.orderService.getAllByCustomerId(id).forEach(order -> responseDtos.add(OrderMapper.INSTANCE.toResponseDto(order)));
        return responseDtos;
    }
}
