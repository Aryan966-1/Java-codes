/*Write a Java program to implement a library management system. 
The program should: Use a base class Book and derived classes Fiction and NonFiction. */
class Book {
    String title;
    String author;
    double price;

    // Parameterized Constructor
    Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    void displayDetails() {
        System.out.println("Title  : " + title);
        System.out.println("Author : " + author);
        System.out.println("Price  : " + price);
    }
}

// Derived class
class Fiction extends Book {
    String genre;

    Fiction(String title, String author, double price, String genre) {
        super(title, author, price);
        this.genre = genre;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Type   : Fiction");
        System.out.println("Genre  : " + genre);
    }
}

// Derived class
class NonFiction extends Book {
    String subject;

    NonFiction(String title, String author, double price, String subject) {
        super(title, author, price);
        this.subject = subject;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Type    : Non-Fiction");
        System.out.println("Subject : " + subject);
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
                                                                                
        Fiction fictionBook = new Fiction(
                "Harry Potter",
                "J.K. Rowling",
                500,
                "Fantasy"
        );

        NonFiction nonFictionBook = new NonFiction(
                "A Brief History of Time",
                "Stephen Hawking",
                600,
                "Science"
        );

        System.out.println("Fiction Book");
        fictionBook.displayDetails();

        System.out.println(" Non-Fiction Boo");
        nonFictionBook.displayDetails();
    }
}