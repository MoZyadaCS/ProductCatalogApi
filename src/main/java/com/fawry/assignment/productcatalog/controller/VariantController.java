package com.fawry.assignment.productcatalog.controller;

import com.fawry.assignment.productcatalog.model.Variant;
import com.fawry.assignment.productcatalog.service.VariantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/variants")
public class VariantController {

    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }


    @PostMapping
    public Variant addVariant(@RequestBody Variant variant){
        return this.variantService.add(variant);
    }
}
