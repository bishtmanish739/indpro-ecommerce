package com.indpro.assignment.assignment.services;

import com.indpro.assignment.assignment.dtos.ProductDTO;
import com.indpro.assignment.assignment.entity.Product;
import com.indpro.assignment.assignment.mapper.ProductMapper;
import com.indpro.assignment.assignment.repository.ProductRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired

   private ProductMapper productMapper;



    public List<Product> getAllProducts() {
        return productRepository.findAllByIsDeletedFalse();
    }


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public List<Product> saveAllProducts(List<ProductDTO> productDTOS){
        List<Product> products=productMapper.toEntityList(productDTOS);
        return productRepository.saveAll(products);


    }


    public Product updateProduct(Long id, ProductDTO productDetails) throws BadRequestException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        return productRepository.save(product);
    }


    public String deleteProduct(Long id) throws BadRequestException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Product not found"));
        product.setDeleted(true);
        productRepository.save(product);
       return "Product Deleted";
    }
}
