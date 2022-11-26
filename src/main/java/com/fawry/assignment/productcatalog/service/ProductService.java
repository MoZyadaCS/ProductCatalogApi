package com.fawry.assignment.productcatalog.service;

import com.fawry.assignment.productcatalog.model.Category;
import com.fawry.assignment.productcatalog.model.Product;
import com.fawry.assignment.productcatalog.model.Variant;
import com.fawry.assignment.productcatalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    private final VariantService variantService;

    private final CategoryService categoryService;


    public ProductService(ProductRepository productRepository, VariantService variantService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.variantService = variantService;
        this.categoryService = categoryService;
    }



    public Product addProduct(Product product){
        return this.productRepository.save(product);
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product updateProduct(Product product) {
        Product managedProduct = this.productRepository.findById(product.getId()).orElseThrow();
        managedProduct.setNameAr(product.getNameAr());
        managedProduct.setNameEn(product.getNameEn());
        managedProduct.setImage(product.getImage());
        managedProduct.setBuyingCount(product.getBuyingCount());
        return this.productRepository.save(managedProduct);
    }

    public void deleteProduct(Long id){
        // find all relevant variants to delete
        List<Variant> variants = this.variantService.getAllVariantsOfProduct(id);
        this.variantService.deleteListOfVariants(variants);
        this.productRepository.deleteById(id);
    }

    public List<Product> getAllProductsByCategory(String name){
        Category category = this.categoryService.findByName(name);
        return this.productRepository.findAllByCategory(category);
    }

    public Product getById(Long id){
        return this.productRepository.findById(id).orElseThrow();
    }

    public List<Product> getAllSortedByPopularity(){
        return this.productRepository.findByOrderByBuyingCountDesc();
    }
}
