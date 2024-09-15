package com.indpro.assignment.assignment.mapper;


import com.indpro.assignment.assignment.dtos.ProductDTO;
import com.indpro.assignment.assignment.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Mapping from ProductDTO to Product entity
    @Mapping(target = "createdAt", ignore = true) // Example: ignore fields if necessary
    @Mapping(target = "id",source = "id")
    Product toEntity(ProductDTO dto);

    // Mapping from Product entity to ProductDTO
    @Mapping(target = "id",source = "id")
    ProductDTO toDto(Product product);

    List<Product> toEntityList(List<ProductDTO> dtoList);

    // Mapping a list of Products to a list of ProductDTOs
    List<ProductDTO> toDtoList(List<Product> productList);
}