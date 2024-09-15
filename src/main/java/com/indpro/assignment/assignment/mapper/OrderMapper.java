package com.indpro.assignment.assignment.mapper;


import com.indpro.assignment.assignment.dtos.OrderDTO;
import com.indpro.assignment.assignment.dtos.ProductDTO;
import com.indpro.assignment.assignment.entity.Order;
import com.indpro.assignment.assignment.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",uses = OrderItemMapper.class)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    // Mapping from ProductDTO to Product entity
    @Mapping(target = "createdAt", ignore = true) // Example: ignore fields if necessary
    @Mapping(target = "id",source = "id")
    Order toEntity(OrderDTO dto);

    // Mapping from Product entity to ProductDTO
    @Mapping(target = "id",source = "id")
    OrderDTO toDto(Order product);

    List<Order> toEntityList(List<OrderDTO> dtoList);

    // Mapping a list of Products to a list of ProductDTOs
    List<OrderDTO> toDtoList(List<Order> orderList);
}