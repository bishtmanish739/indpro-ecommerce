package com.indpro.assignment.assignment.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
}
