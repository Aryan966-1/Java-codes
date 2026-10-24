package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Enables Spring Boot auto-configuration and component scanning
public class ProductCatalogApplication {
    public static void main(String[] args) {
        // Starts the embedded server and the Spring application
        SpringApplication.run(ProductCatalogApplication.class, args);
    }
}
