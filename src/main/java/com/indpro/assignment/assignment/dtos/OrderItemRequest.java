package com.indpro.assignment.assignment.dtos;

import lombok.Data;

@Data

public class OrderItemRequest {
    private Long productId;
    private Integer quantity;

    // getters and setters
}
