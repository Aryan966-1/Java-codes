package com.example.demo;
public class Product {
    // Unique ID used to identify each product
    private int id;
    // Product name, for example "Keyboard"
    private String name;
    // Price of the product
    private double price;
    // Empty constructor used by frameworks when needed
    public Product() {
    }
    // Constructor used to create a product with values
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    // Returns the product ID
    public int getId() {
        return id;
    }
    // Sets the product ID
    public void setId(int id) {
        this.id = id;
    }
    // Returns the product name
    public String getName() {
        return name;
    }
    // Sets the product name
    public void setName(String name) {
        this.name = name;
    }
    // Returns the product price
    public double getPrice() {
        return price;
    }
    // Sets the product price
    public void setPrice(double price) {
        this.price = price;
    }
}