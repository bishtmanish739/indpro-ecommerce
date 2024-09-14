package com.indpro.assignment.assignment.services;

import com.indpro.assignment.assignment.entity.Product;
import com.indpro.assignment.assignment.repository.ProductRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    public Product updateProduct(Long id, Product productDetails) throws BadRequestException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        return productRepository.save(product);
    }


    public void deleteProduct(Long id) throws BadRequestException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product not found"));
        productRepository.delete(product);
    }
}
