package com.fawry.assignment.productcatalog.controller;

import com.fawry.assignment.productcatalog.dto.VariantRequestDto;
import com.fawry.assignment.productcatalog.dto.VariantResponseDto;
import com.fawry.assignment.productcatalog.mapper.VariantMapper;
import com.fawry.assignment.productcatalog.service.VariantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/variants")
public class VariantController {

    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VariantResponseDto addVariant(@RequestBody VariantRequestDto requestDto){
        return VariantMapper.INSTANCE.toResponseDto(this.variantService.add(VariantMapper.INSTANCE.toVariant(requestDto)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<VariantResponseDto> getAllVariants(){
        return this.variantService.getAll().stream().map(VariantMapper.INSTANCE::toResponseDto).toList();
    }
}
