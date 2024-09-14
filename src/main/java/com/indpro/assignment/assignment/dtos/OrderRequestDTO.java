package com.indpro.assignment.assignment.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private Long userId;
    private List<ProductDTO> products;


}

