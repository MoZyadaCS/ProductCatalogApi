package com.fawry.assignment.productcatalog.controller;

import com.fawry.assignment.productcatalog.model.Product;
import com.fawry.assignment.productcatalog.service.ProductService;
import com.fawry.assignment.productcatalog.service.VariantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    private final ProductService productService;

    private final VariantService variantService;




    public ProductController(ProductService productService, VariantService variantService) {
        this.productService = productService;
        this.variantService = variantService;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        product.getVariants().forEach(variant -> variant.setProduct(product));
        Product product1 = productService.addProduct(product);
        product.getVariants().forEach(variantService::add);
        return product1;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return this.productService.getAll();
    }


    @PutMapping
    public Product updateProduct(@RequestBody Product product){

        return this.productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        this.productService.deleteProduct(id);
    }

    @GetMapping("/{name}")
    public List<Product> getAllProductsByCategory(@PathVariable String name){
        return this.productService.getAllProductsByCategory(name);
    }

    @GetMapping("/sorted")
    public List<Product> getAllSorted(){
        return this.productService.getAllSortedByPopularity();
    }

}
