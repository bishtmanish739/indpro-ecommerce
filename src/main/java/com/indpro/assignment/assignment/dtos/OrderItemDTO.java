package com.indpro.assignment.assignment.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}

