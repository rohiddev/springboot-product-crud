package com.demo.product.controller;

import com.demo.product.model.Product;
import com.demo.product.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ── GET /api/products ────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info("GET /api/products");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // ── GET /api/products/{id} ───────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        log.info("GET /api/products/{}", id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // ── POST /api/products ───────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        log.info("POST /api/products — name='{}'", product.getName());
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── PUT /api/products/{id} ───────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {
        log.info("PUT /api/products/{}", id);
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    // ── DELETE /api/products/{id} ────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("DELETE /api/products/{}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // ── GET /api/products/category/{category} ────────────────────────────────
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        log.info("GET /api/products/category/{}", category);
        return ResponseEntity.ok(productService.getByCategory(category));
    }

    // ── GET /api/products/search?keyword=mac ─────────────────────────────────
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        log.info("GET /api/products/search?keyword={}", keyword);
        return ResponseEntity.ok(productService.searchByName(keyword));
    }

    // ── GET /api/products/price-range?min=100&max=500 ────────────────────────
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getByPriceRange(
            @RequestParam double min,
            @RequestParam double max) {
        log.info("GET /api/products/price-range?min={}&max={}", min, max);
        return ResponseEntity.ok(productService.getByPriceRange(min, max));
    }
}
