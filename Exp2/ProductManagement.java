// ProductManagement.java
/*Write a Java program to create a Product class with attributes id, name, and price.
 The program should: Demonstrate the use of constructors and methods to display product details.*/
class Product {
    int id;
    String name;
    double price;
    // Default Constructor
    Product() {
        id = 0;
        name = "Unknown";
        price = 0.0;
    }
    // Parameterized Constructor
    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    void display() {
        System.out.println("Product ID   : " + id);
        System.out.println("Product Name : " + name);
        System.out.println("Price        : " + price);
    }
}
public class ProductManagement {
    public static void main(String[] args) {
        Product p1 = new Product();
        Product p2 = new Product(101, "Laptop", 55000);

        System.out.println("Default Product:");
        p1.display();

        System.out.println("\nParameterized Product:");
        p2.display();
    }
}