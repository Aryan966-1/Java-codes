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
    // private: The nested class is accessible only inside Medium.
    /* static: The nested class can be instantiated without an instance of Medium.
    It is a static nested class.
    It doesn't require an instance of Medium.*/
	/*implements Serializable
           This tells Java:
                Objects of this class are allowed to be serialized. */
		//Serializable: It is a marker interface. It doesn't require you to implement methods.		
		private static final long serialVersionUID = 1L;
		// serialVersionUID: It is a unique identifier for the class. It is used by the serialization mechanism to determine if the class has changed since the last time it

		private final int rollNumber;
		private final String name;       // final: The variable cannot be reassigned.
		private final String course;
		/*private: The variable is accessible only inside the class. It is Encapsulation.
		* final: Once assigned, they cannot be reassigned. They must therefore be initialized through the constructor. */

		Student(int rollNumber, String name, String course) { // Constructor to initialize the fields
			this.rollNumber = rollNumber; // this: Refers to the current instance of the class. It is used to distinguish between the instance variable and the parameter with the same name.
			this.name = name; /*eg: means: object's name = parameter name 
			Without 'this', Java would have no way of knowing which 'name' to refer to. 
			With 'this', Java knows to use the instance variable. */
			this.course = course;
		}

		@Override
		public String toString() { /* toString(): Overrides Object.toString()
			 Without overriding it, printing:
                    System.out.println(originalStudent)
				would produce something similar to:
                       'Medium$Student@5e2de80c'
			        which is not very helpful.
                With the override, you get meaningful information:
                    Student{rollNumber=101, name='Aarav', course='Computer Science'} */
			return "Student{rollNumber=" + rollNumber
					+ ", name='" + name + '\''
					+ ", course='" + course + '\'' + '}';
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Student originalStudent = new Student(101, "Aarav", "Computer Science"); // Creates a Student object in memory (in heap)

		try (ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream(FILE_NAME))) {
					/*This creates a stream chain:
					   Student object
                           ↓
                      ObjectOutputStream
                           ↓
                       FileOutputStream
                             ↓
                         student.ser
				FileOutputStream: This is a byte stream that writes bytes to a file. It is used to write the serialized object to a file.
				ObjectOutputStream: This is a byte stream that writes objects to an OutputStream. It is used to serialize the object and write it to the file.	 */
			output.writeObject(originalStudent); // This serializes the Student object and writes it to the file student.ser
		}
		System.out.println("Serialized: " + originalStudent);
          //DESERIALIZATION: The process of converting a serialized object back into a live object in memory.
		Student restoredStudent;
		try (ObjectInputStream input = new ObjectInputStream(
				new FileInputStream(FILE_NAME))) {
					/*This creates a stream chain:
					   student.ser
                           ↓
                      ObjectInputStream
                           ↓
                       FileInputStream
                             ↓
                         Student object
					*/
			restoredStudent = (Student) input.readObject();
			/*readObject(): Object. This method reads an object from the input stream and returns it.
			   It is used to deserialize the object and restore it to its original state.
			   This is downcasting from Object to Student. */
		}
		System.out.println("Deserialized: " + restoredStudent);
	}
}
