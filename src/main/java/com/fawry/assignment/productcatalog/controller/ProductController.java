package com.fawry.assignment.productcatalog.controller;

import com.fawry.assignment.productcatalog.dto.ProductRequestDto;
import com.fawry.assignment.productcatalog.dto.ProductResponseDto;
import com.fawry.assignment.productcatalog.mapper.ProductMapper;
import com.fawry.assignment.productcatalog.model.Product;
import com.fawry.assignment.productcatalog.service.CategoryService;
import com.fawry.assignment.productcatalog.service.ProductService;
import com.fawry.assignment.productcatalog.service.VariantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    private final ProductService productService;

    private final VariantService variantService;

    private final CategoryService categoryService;




    public ProductController(ProductService productService, VariantService variantService, CategoryService categoryService) {
        this.productService = productService;
        this.variantService = variantService;
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto requestDto){
        Product product = ProductMapper.INSTANCE.toProduct(requestDto);
        if(product.getVariants() != null) {
            product.setVariants(product.getVariants().stream().map(variant -> variantService.getById(variant.getId())).toList());
            product.getVariants().forEach(variant -> variant.setProduct(product));
        }
        if(requestDto.getCategory() != null) product.setCategory(categoryService.findById(requestDto.getCategory().getId()));
        return ProductMapper.INSTANCE.toResponseDto(productService.addProduct(product));

    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponseDto> getAllProducts(){
        return this.productService.getAll().stream().map(ProductMapper.INSTANCE::toResponseDto).toList();
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto requestDto){

        return ProductMapper.INSTANCE.toResponseDto(this.productService.updateProduct(ProductMapper.INSTANCE.toProduct(requestDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id){
        this.productService.deleteProduct(id);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable String name){
        return this.productService.getAllProductsByCategory(name).stream().map(ProductMapper.INSTANCE::toResponseDto).toList();
    }

    @GetMapping("/sorted")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductResponseDto> getAllSorted(){
        return this.productService.getAllSortedByPopularity().stream().map(ProductMapper.INSTANCE::toResponseDto).toList();
    }

}
