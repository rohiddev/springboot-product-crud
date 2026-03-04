package com.demo.product.service;

import com.demo.product.exception.ResourceNotFoundException;
import com.demo.product.model.Product;
import com.demo.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ── GET all ─────────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        log.debug("Fetching all products");
        List<Product> products = productRepository.findAll();
        log.info("Found {} products", products.size());
        return products;
    }

    // ── GET by ID ────────────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        log.debug("Fetching product with id={}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        log.info("Retrieved {}", product);
        return product;
    }

    // ── CREATE ───────────────────────────────────────────────────────────────
    public Product createProduct(Product product) {
        log.debug("Creating product: {}", product.getName());
        Product saved = productRepository.save(product);
        log.info("Created product with id={}, name='{}'", saved.getId(), saved.getName());
        return saved;
    }

    // ── UPDATE ───────────────────────────────────────────────────────────────
    public Product updateProduct(Long id, Product updated) {
        log.debug("Updating product with id={}", id);
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setCategory(updated.getCategory());
        existing.setStock(updated.getStock());

        Product saved = productRepository.save(existing);
        log.info("Updated {}", saved);
        return saved;
    }

    // ── DELETE ───────────────────────────────────────────────────────────────
    public void deleteProduct(Long id) {
        log.debug("Deleting product with id={}", id);
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        log.info("Deleted product with id={}", id);
    }

    // ── GET by category ──────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<Product> getByCategory(String category) {
        log.debug("Fetching products in category='{}'", category);
        List<Product> products = productRepository.findByCategory(category);
        log.info("Found {} products in category '{}'", products.size(), category);
        return products;
    }

    // ── SEARCH by name ───────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<Product> searchByName(String keyword) {
        log.debug("Searching products with keyword='{}'", keyword);
        List<Product> products = productRepository.searchByName(keyword);
        log.info("Found {} products matching '{}'", products.size(), keyword);
        return products;
    }

    // ── GET by price range ───────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<Product> getByPriceRange(double min, double max) {
        log.debug("Fetching products with price between {} and {}", min, max);
        List<Product> products = productRepository.findByPriceRange(min, max);
        log.info("Found {} products in price range [{}, {}]", products.size(), min, max);
        return products;
    }
}
