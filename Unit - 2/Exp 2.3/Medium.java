import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Medium {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            connection.setAutoCommit(false);

            boolean running = true;
            while (running) {
                printMenu();
                String choice = scanner.nextLine().trim();

                try {
                    switch (choice) {
                        case "1":
                            createProduct(connection, scanner);
                            break;
                        case "2":
                            readProducts(connection);
                            break;
                        case "3":
                            updateProduct(connection, scanner);
                            break;
                        case "4":
                            deleteProduct(connection, scanner);
                            break;
                        case "5":
                            running = false;
                            System.out.println("Exiting program.");
                            break;
                        default:
                            System.out.println("Invalid option. Please choose 1-5.");
                    }
                } catch (SQLException e) {
                    rollback(connection);
                    System.err.println("Operation failed. Transaction rolled back: "
                            + e.getMessage());
                } catch (NumberFormatException e) {
                    rollback(connection);
                    System.out.println("Enter valid numeric values. Transaction rolled back.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Could not connect to the database: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Product CRUD Menu ---");
        System.out.println("1. Create product");
        System.out.println("2. Read all products");
        System.out.println("3. Update product");
        System.out.println("4. Delete product");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static void createProduct(Connection connection, Scanner scanner)
            throws SQLException {
        System.out.print("Product ID: ");
        int productId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Product name: ");
        String productName = scanner.nextLine().trim();
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        String sql = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.setString(2, productName);
            statement.setDouble(3, price);
            statement.setInt(4, quantity);
            statement.executeUpdate();
            connection.commit();
            System.out.println("Product created successfully.");
        }
    }

    private static void readProducts(Connection connection) throws SQLException {
        String sql = "SELECT ProductID, ProductName, Price, Quantity FROM Product";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.printf("%-12s %-25s %-12s %-10s%n",
                    "ProductID", "ProductName", "Price", "Quantity");
            while (resultSet.next()) {
                System.out.printf("%-12d %-25s %-12.2f %-10d%n",
                        resultSet.getInt("ProductID"),
                        resultSet.getString("ProductName"),
                        resultSet.getDouble("Price"),
                        resultSet.getInt("Quantity"));
            }
            connection.commit();
        }
    }

    private static void updateProduct(Connection connection, Scanner scanner)
            throws SQLException {
        System.out.print("Product ID to update: ");
        int productId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("New product name: ");
        String productName = scanner.nextLine().trim();
        System.out.print("New price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("New quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        String sql = "UPDATE Product SET ProductName = ?, Price = ?, Quantity = ? "
                + "WHERE ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productName);
            statement.setDouble(2, price);
            statement.setInt(3, quantity);
            statement.setInt(4, productId);
            int rowsUpdated = statement.executeUpdate();
            connection.commit();
            System.out.println(rowsUpdated == 0
                    ? "No product found with that ID."
                    : "Product updated successfully.");
        }
    }

    private static void deleteProduct(Connection connection, Scanner scanner)
            throws SQLException {
        System.out.print("Product ID to delete: ");
        int productId = Integer.parseInt(scanner.nextLine().trim());

        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            int rowsDeleted = statement.executeUpdate();
            connection.commit();
            System.out.println(rowsDeleted == 0
                    ? "No product found with that ID."
                    : "Product deleted successfully.");
        }
    }

    private static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.err.println("Could not roll back transaction: " + e.getMessage());
        }
    }
}
