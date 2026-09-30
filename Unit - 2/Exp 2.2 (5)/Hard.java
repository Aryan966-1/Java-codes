/*Create a menu-based Java application with the following 
options. 1.Add an Employee 2. Display All 3. Exit If option 1 is 
selected, the application should gather details of the employee 
like employee name, employee id, designation and salary and 
store it in a file. If option 2 is selected, the application should 
display all the employee details. If option 3 is selected the 
application should exit.*/
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hard {
	private static final String FILE_NAME = "employees.dat";

	private static class Employee implements Serializable {
		/* This is a private static nested class. 
		It is serializable because the program wants to store Employee objects in a file.*/
		private static final long serialVersionUID = 1L;

		private final String name;
		private final int id;
		private final String designation;
		private final double salary;

		Employee(String name, int id, String designation, double salary) { // Constructor to initialize the fields
			this.name = name;
			this.id = id;
			this.designation = designation;
			this.salary = salary;
		}

		@Override
		public String toString() {
			return "ID: " + id + ", Name: " + name
					+ ", Designation: " + designation
					+ ", Salary: " + salary;
		}
	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			boolean running = true;

			while (running) {
				System.out.println("\n1. Add an Employee");
				System.out.println("2. Display All");
				System.out.println("3. Exit");
				System.out.print("Enter your choice: ");

				String choice = scanner.nextLine();
				switch (choice) {
					case "1":
						addEmployee(scanner);
						break;
					case "2":
						displayEmployees();
						break;
					case "3":
						running = false;
						System.out.println("Application exited.");
						break;
					default:
						System.out.println("Invalid choice. Please select 1, 2, or 3.");
				}
			}
		}
	}

	private static void addEmployee(Scanner scanner) {
		try {
			System.out.print("Enter employee name: ");
			String name = scanner.nextLine();
			System.out.print("Enter employee ID: ");
			int id = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter designation: ");
			String designation = scanner.nextLine();
			System.out.print("Enter salary: ");
			double salary = Double.parseDouble(scanner.nextLine());

			List<Employee> employees = readEmployees();
			employees.add(new Employee(name, id, designation, salary));
			writeEmployees(employees);
			System.out.println("Employee added successfully.");
		} catch (NumberFormatException exception) {
			System.out.println("Employee ID and salary must be valid numbers.");
		} catch (IOException | ClassNotFoundException exception) {
			System.out.println("Unable to save employee details: " + exception.getMessage());
		}
	}

	private static void displayEmployees() {
		try {
			List<Employee> employees = readEmployees();
			if (employees.isEmpty()) {
				System.out.println("No employee records found.");
				return;
			}

			System.out.println("\nEmployee Details:");
			for (Employee employee : employees) {
				System.out.println(employee);
			}
		} catch (IOException | ClassNotFoundException exception) {
			System.out.println("Unable to read employee details: " + exception.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	/*@SuppressWarnings("unchecked") Why? Because:
    input.readObject()
 returns:
     Object
    and the program casts it to:
     List<Employee>
     Java cannot fully verify the generic type at runtime due to type erasure.
     Therefore an unchecked cast warning can occur. */
	private static List<Employee> readEmployees() throws IOException, ClassNotFoundException {
		/*readEmployees(): This method may throw two checked exceptions.
		IOException: If the file is not found or cannot be read.
		ClassNotFoundException: If the class of the serialized object cannot be found.
		or, during deserialization, Java cannot find the class needed to reconstruct the serialized object. */
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			return (List<Employee>) input.readObject();
			/*There are actually two operations:
           1. Read object
            input.readObject()
            returns Object.
            2. Cast
            (List<Employee>)
            tells compiler:
            Treat this object as a List of Employee. */
		} catch (java.io.FileNotFoundException exception) {
			/*if:
			 employees.dat is not found then, it assumes:
			  there are currently zero employees and returns an empty ArrayList.*/
			return new ArrayList<>();
		}
	}

	private static void writeEmployees(List<Employee> employees) throws IOException {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			output.writeObject(employees);
		}
	}
}
