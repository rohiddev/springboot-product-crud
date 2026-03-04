package com.demo.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication {

    private static final Logger log = LoggerFactory.getLogger(ProductApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
        log.info("Product CRUD API started — http://localhost:8080/api/products");
        log.info("H2 Console available at  — http://localhost:8080/h2-console");
    }
}
