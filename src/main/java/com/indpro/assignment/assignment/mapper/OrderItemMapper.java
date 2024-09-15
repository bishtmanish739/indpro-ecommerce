package com.indpro.assignment.assignment.mapper;


import com.indpro.assignment.assignment.dtos.OrderDTO;
import com.indpro.assignment.assignment.dtos.OrderItemDTO;
import com.indpro.assignment.assignment.entity.Order;
import com.indpro.assignment.assignment.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    // Mapping from ProductDTO to Product entity
    @Mapping(target = "product.id",source = "productId")
    OrderItem toEntity(OrderItemDTO dto);

    // Mapping from Product entity to ProductDTO
    @Mapping(target = "productId",source = "product.id")
    OrderItemDTO toDto(OrderItem orderItem);

    List<OrderItem> toEntityList(List<OrderItemDTO> dtoList);

    // Mapping a list of Products to a list of ProductDTOs
    List<OrderItemDTO> toDtoList(List<OrderItem> orderList);
}