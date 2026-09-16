import java.util.ArrayList;
import java.util.Scanner;

/*
 * Program to implement an ArrayList that stores employee details (ID, Name, and Salary).
 * Supports adding, updating, removing, searching, and displaying employees.
 */
public class Employee {
    private int id;
    private String name;
    private double salary;

    // Parameterized Constructor
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Method to display individual employee details
    public void display() {
        System.out.println("ID     : " + id);
        System.out.println("Name   : " + name);
        System.out.println("Salary : " + salary);
    }

    // Helper method to find an employee by ID in the list
    private static Employee findEmployeeById(ArrayList<Employee> employees, int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    // Method to add a new employee
    private static void addEmployee(ArrayList<Employee> employees, Scanner sc) {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        if (findEmployeeById(employees, id) != null) {
            System.out.println("Error: Employee with ID " + id + " already exists.");
            return;
        }

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine(); // consume newline

        employees.add(new Employee(id, name, salary));
        System.out.println("Employee added successfully!");
    }

    // Method to update employee details
    private static void updateEmployee(ArrayList<Employee> employees, Scanner sc) {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        Employee emp = findEmployeeById(employees, id);
        if (emp == null) {
            System.out.println("Error: Employee with ID " + id + " not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        String newName = sc.nextLine();

        System.out.print("Enter New Salary: ");
        double newSalary = sc.nextDouble();
        sc.nextLine(); // consume newline

        emp.setName(newName);
        emp.setSalary(newSalary);
        System.out.println("Employee details updated successfully!");
    }

    // Method to remove an employee
    private static void removeEmployee(ArrayList<Employee> employees, Scanner sc) {
        System.out.print("Enter Employee ID to remove: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        Employee emp = findEmployeeById(employees, id);
        if (emp == null) {
            System.out.println("Error: Employee with ID " + id + " not found.");
            return;
        }

        employees.remove(emp);
        System.out.println("Employee removed successfully!");
    }

    // Method to search for an employee by ID
    private static void searchEmployee(ArrayList<Employee> employees, Scanner sc) {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        Employee emp = findEmployeeById(employees, id);
        if (emp == null) {
            System.out.println("Error: Employee with ID " + id + " not found.");
        } else {
            System.out.println("\n--- Employee Found ---");
            emp.display();
        }
    }

    // Method to display all employees
    private static void displayAllEmployees(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found in the list.");
            return;
        }

        System.out.println("\n========== Employee List ==========");
        for (Employee emp : employees) {
            emp.display();
            System.out.println("-----------------------------------");
        }
    }

    public static void main(String[] args) {
        ArrayList<Employee> employeeList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // discard non-integer input
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addEmployee(employeeList, sc);
                    break;
                case 2:
                    updateEmployee(employeeList, sc);
                    break;
                case 3:
                    removeEmployee(employeeList, sc);
                    break;
                case 4:
                    searchEmployee(employeeList, sc);
                    break;
                case 5:
                    displayAllEmployees(employeeList);
                    break;
                case 6:
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose between 1 and 6.");
            }
        } while (choice != 6);

        sc.close();
    }
}
