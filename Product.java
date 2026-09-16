/*Create a Java program to create a Product class with attributes id, name, and price. 
The program should: Demonstrate the use of 
constructors and methods to 
display product details.*/
import java.util.Scanner;
public class Product{
    private int id;
    private String name;
    private double price;

    // Constructor
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Method to display product details
    public void displayDetails() {
        System.out.println("Product ID: " + id);
        System.out.println("Product Name: " + name);
        System.out.println("Product Price: rs." + price);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print(   " Enter Product id: ");
        int id = sc.nextInt();
        System.out.print(" Enter Product name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print(" Enter Product price: ");
        double price = sc.nextDouble();
        Product p = new Product(id, name, price);
        p.displayDetails();
        sc.close();
    }
}
