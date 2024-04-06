package com.demo.microservices.controllers;

import com.demo.microservices.models.Product;
import com.demo.microservices.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity
                .ok(productRepository.findAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getById(@PathVariable Long productId) {
        return ResponseEntity.of(productRepository.findById(productId));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Product product) {
        try {
            Product inserted = productRepository.save(product);
            return ResponseEntity
                    .ok("Inserted Product : " + inserted.getProductId());
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Product product) {
        Optional<Product> found = productRepository.findById(product.getProductId());
        if (found.isPresent()) {
            Product foundProduct = found.get();
            BeanUtils.copyProperties(product, foundProduct);
            Product updated;
            try {
                updated = productRepository.save(foundProduct);
                return ResponseEntity.ok("Updated Product : " + updated.getProductId());
            } catch (Exception e) {
                return ResponseEntity
                        .badRequest()
                        .body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> delete(@PathVariable Long productId) {
        try {
            productRepository.deleteById(productId);
            return ResponseEntity
                    .ok("Deleted Product : " + productId);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
