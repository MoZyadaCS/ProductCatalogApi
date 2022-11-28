package com.fawry.assignment.productcatalog.mapper;

import com.fawry.assignment.productcatalog.dto.VariantRequestDto;
import com.fawry.assignment.productcatalog.dto.VariantResponseDto;
import com.fawry.assignment.productcatalog.model.Product;
import com.fawry.assignment.productcatalog.model.Variant;
import org.aspectj.weaver.ast.Var;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VariantMapper {

    VariantMapper INSTANCE = Mappers.getMapper(VariantMapper.class);

    @Mapping(source = "limit", target = "limit")
    Variant toVariant(VariantRequestDto requestDto);


    default VariantResponseDto toResponseDto(Variant variant){
        if(variant == null) throw new RuntimeException("cannot map null to responseDto");
        else{
            VariantResponseDto responseDto = new VariantResponseDto();
            responseDto.setNameEn(variant.getProduct().getNameEn());
            responseDto.setNameAr(variant.getProduct().getNameAr());
            responseDto.setImage(variant.getProduct().getImage());
            responseDto.setQuantity(variant.getQuantity());
            responseDto.setType(variant.getType());
            responseDto.setPrice(variant.getPrice());
            responseDto.setLimit(variant.getLimit());
            return responseDto;
        }
    }
}
