package com.indpro.assignment.assignment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = true)
    private String productUrl;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt=Instant.now();

    @OneToMany()
    private Set<OrderItem> orderItems ;

    @Column(name="is_deleted", nullable = true)
    private boolean isDeleted = false;


}

