package com.fawry.assignment.productcatalog.service;

import com.fawry.assignment.productcatalog.model.Variant;
import com.fawry.assignment.productcatalog.repository.ProductRepository;
import com.fawry.assignment.productcatalog.repository.VariantRepository;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VariantService {

    private final VariantRepository variantRepository;

    private final ProductRepository productRepository;

    public VariantService(VariantRepository variantRepository, ProductRepository productRepository) {
        this.variantRepository = variantRepository;
        this.productRepository = productRepository;
    }


    public Variant add(Variant variant) {
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
}
