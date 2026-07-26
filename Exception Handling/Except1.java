//package Exception Handling;
// Checked exceptions vs Unchecked exceptions in Java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class Except1 {
	public static void main(String[] args) {
		System.out.println("Checked exception example:");
		try {
			FileInputStream file = new FileInputStream("missing-file.txt");
			file.read();
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("Checked exception caught: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}

		System.out.println();
		System.out.println("Unchecked exception example:");
		try {
			int result = 10 / 0;
			System.out.println(result);
		} catch (ArithmeticException e) {
			System.out.println("Unchecked exception caught: Cannot divide by zero.");
		}
	}
}
