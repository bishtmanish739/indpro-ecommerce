package com.indpro.assignment.assignment.dtos;

import com.indpro.assignment.assignment.entity.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Data
public class OrderDTO {

    private Long id;


    private BigDecimal totalPrice;


    private String status;


    private Instant createdAt=Instant.now();

    private List<OrderItemDTO> orderItems = new ArrayList<>();


}
