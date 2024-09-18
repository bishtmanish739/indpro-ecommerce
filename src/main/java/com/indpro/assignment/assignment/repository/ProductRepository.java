package com.indpro.assignment.assignment.repository;

import com.indpro.assignment.assignment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIsDeletedFalse();

//    @Query("SELECT p FROM Product p WHERE p.is_deleted = false")
//    List<Product> findAllActiveProducts();
}
