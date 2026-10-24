package com.example.demo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController  // Makes this class handle REST requests and return data as JSON
@RequestMapping("/products")  // Base URL for every endpoint in this controller
public class ProductController {
    // In-memory list: no database is used
    private List<Product> products = new ArrayList<>();
    // Constructor runs when Spring creates this controller
    public ProductController() {
        // Add two sample products so GET requests return data immediately
        products.add(new Product(1, "Posters", 200.0));
        products.add(new Product(2, "Painting", 500.0));
        products.add(new Product(3, "Potted Plants", 1500.0));
       products.add(new Product(4, "Sculpture", 200.0));
    }
    // 1. GET /products
    // Returns the complete product list
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(products);  // HTTP 200 OK
    }
    // 2. GET /products/{id}
    // Returns one product matching the ID in the URL
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        // Search the list for a product with the requested ID
        for (Product product : products) {
            if (product.getId() == id) {
                return ResponseEntity.ok(product);  // Found: HTTP 200
            }
        }
        // No matching product was found: HTTP 404 Not Found
        return ResponseEntity.notFound().build();
    }
    // 3. POST /products
    // Creates a new product using the JSON request body
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        // Reject a product if its ID is already in use
        for (Product existing : products) {
            if (existing.getId() == product.getId()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build(); // HTTP 409
            }
        }
        // Add the new product to the in-memory list
        products.add(product);
        // Return the created product with HTTP 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    // 4. PUT /products/{id}
    // Replaces the product details for the given ID
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product updatedProduct) {
        // Find the product that needs to be updated
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                // Keep the URL ID as the product ID, even if JSON has another ID
                updatedProduct.setId(id);
                // Replace the old product with the updated product
                products.set(i, updatedProduct);
                // Return updated details with HTTP 200 OK
                return ResponseEntity.ok(updatedProduct);
            }
        }
        // Cannot update a product that does not exist
        return ResponseEntity.notFound().build(); // HTTP 404
    }
    // 5. DELETE /products/{id}
    // Deletes a product using its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        // Search for the product to delete
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                // Remove the matching product from the list
                products.remove(i);
                // Deletion succeeded: HTTP 204 No Content
                return ResponseEntity.noContent().build();
            }
        }
        // No product with this ID exists
        return ResponseEntity.notFound().build(); // HTTP 404
    }
}