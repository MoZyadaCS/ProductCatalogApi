package com.fawry.assignment.productcatalog.controller;

import com.fawry.assignment.productcatalog.dto.BuyingRequestDto;
import com.fawry.assignment.productcatalog.dto.OrderRequestDto;
import com.fawry.assignment.productcatalog.dto.OrderResponseDto;
import com.fawry.assignment.productcatalog.exception.ItemNotAvailableException;
import com.fawry.assignment.productcatalog.exception.MethodNotAllowedException;
import com.fawry.assignment.productcatalog.mapper.OrderMapper;
import com.fawry.assignment.productcatalog.model.Order;
import com.fawry.assignment.productcatalog.model.Variant;
import com.fawry.assignment.productcatalog.service.OrderService;
import com.fawry.assignment.productcatalog.service.ProductService;
import com.fawry.assignment.productcatalog.service.VariantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    private final VariantService variantService;

    private final ProductService productService;

    public OrderController(OrderService orderService, VariantService variantService, ProductService productService) {
        this.orderService = orderService;
        this.variantService = variantService;
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto addOrder(@RequestBody OrderRequestDto requestDto){
        Order order = OrderMapper.INSTANCE.toOrder(requestDto);
        List<Variant> variants = order.getVariants().stream().map(variant -> variantService.getById(variant.getId())).toList();
        order.setVariants(variants);
        order.setTotalPrice(order.getVariants().stream().mapToDouble(Variant::getPrice).sum());
        variants.forEach(product -> product.setOrders(List.of(order)));
        return OrderMapper.INSTANCE.toResponseDto(this.orderService.add(order));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public OrderResponseDto getOrder(@PathVariable Long id){
        return OrderMapper.INSTANCE.toResponseDto(this.orderService.getOrderById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<OrderResponseDto> getAllOrders(){
        List<OrderResponseDto> responseDtos = new ArrayList<>();
        this.orderService.getAll().forEach(order -> responseDtos.add(OrderMapper.INSTANCE.toResponseDto(order)));
        return responseDtos;
    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<OrderResponseDto> getAllOrdersForCustomer(@PathVariable Long id){
        List<OrderResponseDto> responseDtos = new ArrayList<>();
        this.orderService.getAllByCustomerId(id).forEach(order -> responseDtos.add(OrderMapper.INSTANCE.toResponseDto(order)));
        return responseDtos;
    }

    @PutMapping("/buy")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto buyOrder(@RequestBody BuyingRequestDto requestDto){
        // get Order from the database
        Order order = orderService.getOrderById(requestDto.getOrder().getId());

        // check if this order is not bought before
        if(order.isBought()) throw new MethodNotAllowedException("cannot buy order that has been already bought");

        // check if the product is available to buy
        order.getVariants().forEach(
                variant -> {
                    if (variant.getQuantity() == 0) {
                        throw new ItemNotAvailableException("The Item You are looking for is not available at this moment");
                    }
                }
        );
        // increase the popularity of each product
        order.getVariants().forEach(variant -> variant.getProduct().setBuyingCount(variant.getProduct().getBuyingCount() + 1));

        // decrease the quantity of each product by one

        order.getVariants().forEach(variant -> variant.setQuantity(variant.getQuantity() - 1));

        order.setBought(true);
        // save the changes to the database
        this.orderService.updateOrder(order);
        order.getVariants().forEach(
                variant -> {
                    productService.updateProduct(variant.getProduct());
                    variantService.updateVariant(variant);
                }
        );

        return OrderMapper.INSTANCE.toResponseDto(order);


    }



}
