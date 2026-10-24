import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// View: displays the menu and collects user input.
public class Hard {
    private static final String URL = "jdbc:postgresql://localhost:5432/storedb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            StudentController controller = new StudentController(connection);
            controller.createTable();

            boolean running = true;
            while (running) {
                printMenu();
                String choice = scanner.nextLine().trim();

                try {
                    switch (choice) {
                        case "1":
                            controller.addStudent(readStudent(scanner));
                            System.out.println("Student added successfully.");
                            break;
                        case "2":
                            displayStudents(controller.getAllStudents());
                            break;
                        case "3":
                            controller.updateStudent(readStudent(scanner));
                            System.out.println("Student updated successfully.");
                            break;
                        case "4":
                            System.out.print("Student ID to delete: ");
                            int studentId = Integer.parseInt(scanner.nextLine().trim());
                            controller.deleteStudent(studentId);
                            System.out.println("Student deleted successfully.");
                            break;
                        case "5":
                            running = false;
                            System.out.println("Exiting program.");
                            break;
                        default:
                            System.out.println("Invalid option. Choose 1-5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Enter valid numeric values.");
                } catch (SQLException e) {
                    System.err.println("Database operation failed: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.println("Could not connect to the database: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add student");
        System.out.println("2. View all students");
        System.out.println("3. Update student");
        System.out.println("4. Delete student");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static Student readStudent(Scanner scanner) {
        System.out.print("Student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Department: ");
        String department = scanner.nextLine().trim();
        System.out.print("Marks: ");
        double marks = Double.parseDouble(scanner.nextLine().trim());
        return new Student(studentId, name, department, marks);
    }

    private static void displayStudents(Iterable<Student> students) {
        System.out.printf("%-12s %-25s %-20s %-10s%n",
                "StudentID", "Name", "Department", "Marks");
        for (Student student : students) {
            System.out.printf("%-12d %-25s %-20s %-10.2f%n",
                    student.getStudentId(),
                    student.getName(),
                    student.getDepartment(),
                    student.getMarks());
        }
    }
}

// Model: represents one student record.
class Student {
    private final int studentId;
    private final String name;
    private final String department;
    private final double marks;

    Student(int studentId, String name, String department, double marks) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    int getStudentId() {
        return studentId;
    }

    String getName() {
        return name;
    }

    String getDepartment() {
        return department;
    }

    double getMarks() {
        return marks;
    }
}

// Controller: contains all database operations for Student records.
class StudentController {
    private final Connection connection;

    StudentController(Connection connection) {
        this.connection = connection;
    }

    void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Student ("
                + "StudentID INT PRIMARY KEY, "
                + "Name VARCHAR(100) NOT NULL, "
                + "Department VARCHAR(100) NOT NULL, "
                + "Marks DECIMAL(5, 2) NOT NULL)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        }
    }

    void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO Student (StudentID, Name, Department, Marks) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, student.getStudentId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getDepartment());
            statement.setDouble(4, student.getMarks());
            statement.executeUpdate();
        }
    }

    Iterable<Student> getAllStudents() throws SQLException {
        java.util.List<Student> students = new java.util.ArrayList<>();
        String sql = "SELECT StudentID, Name, Department, Marks FROM Student";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt("StudentID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Department"),
                        resultSet.getDouble("Marks")));
            }
        }
        return students;
    }

    void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE Student SET Name = ?, Department = ?, Marks = ? "
                + "WHERE StudentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getDepartment());
            statement.setDouble(3, student.getMarks());
            statement.setInt(4, student.getStudentId());
            if (statement.executeUpdate() == 0) {
                throw new SQLException("No student found with ID "
                        + student.getStudentId());
            }
        }
    }

    void deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM Student WHERE StudentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("No student found with ID " + studentId);
            }
        }
    }
}
