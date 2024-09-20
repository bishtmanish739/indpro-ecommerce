package com.indpro.assignment.assignment.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long productId;
    private String productName;
    private String productUrl;
    private Integer quantity;
    private BigDecimal price;


}

