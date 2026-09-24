/*Write a Java program to calculate the sum of a list of integers 
using autoboxing and unboxing. Include methods to parse 
strings into their respective wrapper classes (e.g., 
Integer.parseInt()).*/
import java.util.ArrayList;

public class Easy {
    private static Integer parseInteger(String value) {
        return Integer.parseInt(value);
    }

    public static void main(String[] args) {
        String[] inputValues = {"10", "20", "30", "40"};
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String inputValue : inputValues) {
            int parsedValue = parseInteger(inputValue);
            numbers.add(parsedValue); // Autoboxing: int to Integer.
        }

        int sum = 0;
        for (Integer number : numbers) {
            sum += number; // Unboxing: Integer to int.
        }

        System.out.println("Numbers: " + numbers);
        System.out.println("Sum: " + sum);
    }
}
