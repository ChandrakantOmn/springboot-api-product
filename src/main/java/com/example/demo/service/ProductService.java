package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(
            ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(@RequestBody Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
        // return ResponseEntity.ok(newProduct);
    }

    // Get all products
    public ResponseEntity<List<Product>> fetchAllProducts() {
        try {
            List<Product> products = productRepository.findAll();

            return ResponseEntity.ok(products);
        } catch (Exception e) {
            System.out.println("fetchAllProducts : " + e.getMessage());
            return ResponseEntity.ok(new ArrayList<Product>());
        }
    }

    // Get a product by ID
    public Optional<Product> fetchProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product;
    }

    public ResponseEntity<Product> updateProduct(Long id, Product updatedProduct) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Product Existingproduct = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.valueOf(id)));
        Existingproduct.setName(updatedProduct.getName());
        Existingproduct.setPrice(updatedProduct.getPrice());
        Existingproduct.setQuantity(
                updatedProduct.getQuantity());
        Product savedEntity = productRepository.save(Existingproduct);
        return ResponseEntity.ok(savedEntity);
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        productRepository.deleteById(id);
        String responseMessage = "Product Deleted Successfully against id " + id;
        return ResponseEntity.ok(responseMessage); // Return 200 OK
    }

}
