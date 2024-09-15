package com.indpro.assignment.assignment.controller;

import com.indpro.assignment.assignment.dtos.ProductDTO;
import com.indpro.assignment.assignment.entity.Product;
import com.indpro.assignment.assignment.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }


    @PostMapping
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<ProductDTO> product) {
        List<Product> createdProduct = productService.saveAllProducts(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDetails) throws Exception {

        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws  Exception{
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
