package com.fawry.assignment.productcatalog.service;

import com.fawry.assignment.productcatalog.model.Variant;
import com.fawry.assignment.productcatalog.repository.ProductRepository;
import com.fawry.assignment.productcatalog.repository.VariantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class VariantService {

    private final VariantRepository variantRepository;

    private final ProductRepository productRepository;

    public VariantService(VariantRepository variantRepository, ProductRepository productRepository) {
        this.variantRepository = variantRepository;
        this.productRepository = productRepository;
    }


    public Variant add(Variant variant) {
        variant.setProduct(productRepository.findById(variant.getProduct().getId()).orElseThrow());
        return this.variantRepository.save(variant);
    }

    public List<Variant> getAllVariantsOfProduct(Long id){
        return this.variantRepository.getAllByProductId(id);
    }

    @Transactional
    public void deleteListOfVariants(List<Variant> variants){
        List<Long> ids = variants.stream().map(Variant::getId).toList();
        this.variantRepository.deleteAllByIdIn(ids);
    }

    public Variant getById(Long id) {
        return this.variantRepository.findById(id).orElseThrow();
    }

    public List<Variant> getAll() {
        return this.variantRepository.findAll();
    }

    public Variant updateVariant(Variant variant) {
        Variant managedVariant = variantRepository.findById(variant.getId()).orElseThrow();
        managedVariant.setQuantity(variant.getQuantity());
        managedVariant.setPrice(variant.getPrice());
        managedVariant.setLimit(variant.getLimit());
        return managedVariant;
    }
}
