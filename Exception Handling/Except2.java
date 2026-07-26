// User-defined exception example in Java
class InvalidAgeException extends Exception {
	public InvalidAgeException(String message) {
		super(message);
	}
}

public class Except2 {
	static void validateAge(int age) throws InvalidAgeException {
		if (age < 18) {
			throw new InvalidAgeException("Age must be 18 or above.");
		}
		System.out.println("Eligible to vote.");
	}

	public static void main(String[] args) {
		int age = 16;

		try {
			validateAge(age);
		} catch (InvalidAgeException e) {
			System.out.println("User-defined exception caught: " + e.getMessage());
		}
	}
}
