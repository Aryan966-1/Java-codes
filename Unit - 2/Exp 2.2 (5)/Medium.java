/* Create a Java program to serialize and deserialize a Student 
object. */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Medium {
	private static final String FILE_NAME = "student.ser";

	private static class Student implements Serializable {
		private static final long serialVersionUID = 1L;

		private final int rollNumber;
		private final String name;
		private final String course;

		Student(int rollNumber, String name, String course) {
			this.rollNumber = rollNumber;
			this.name = name;
			this.course = course;
		}

		@Override
		public String toString() {
			return "Student{rollNumber=" + rollNumber
					+ ", name='" + name + '\''
					+ ", course='" + course + '\'' + '}';
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Student originalStudent = new Student(101, "Aarav", "Computer Science");

		try (ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream(FILE_NAME))) {
			output.writeObject(originalStudent);
		}
		System.out.println("Serialized: " + originalStudent);

		Student restoredStudent;
		try (ObjectInputStream input = new ObjectInputStream(
				new FileInputStream(FILE_NAME))) {
			restoredStudent = (Student) input.readObject();
		}
		System.out.println("Deserialized: " + restoredStudent);
	}
}
