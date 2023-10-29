package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.ApiResponse;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create a new product
    @PostMapping("/product")
    public ResponseEntity<ApiResponse<Product>> saveProduct(@RequestBody Product product) {
        Product product2 = productService.saveProduct(product);
        String responseMessage = "Product added Successfully product id " + product2.getId();
        HttpStatus responseStatus = HttpStatus.OK;
        int statusCode = HttpStatus.OK.value();
        ApiResponse<Product> response = new ApiResponse<Product>(responseMessage, responseStatus, statusCode, product2,
                null);
        return ResponseEntity.ok(response);
    }

    // Get all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.fetchAllProducts();
    }

    // Get a product by ID
    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.fetchProductById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a product
    @PutMapping(path = "/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "productId") Long productId,
            @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    // Delte a product

    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Long productId) {
        // return productService.deleteProduct(productId);

        String responseMessage = "Product Deleted Successfully against id " + productId;
        HttpStatus responseStatus = HttpStatus.OK;
        ApiResponse<String> response = new ApiResponse<>(responseMessage,  responseStatus, HttpStatus.OK.value(), null, null);
        return new ResponseEntity<>(response, responseStatus); // Return 200 OK

    }

}
