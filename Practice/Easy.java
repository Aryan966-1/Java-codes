import java.util.ArrayList;
public class Easy {
    private static Integer parseInteger(String value){
        return Integer.parseInt(value);
    }
    public static void main(String[] args){
        String[] input = {"10", "20", "30", "40"};
        ArrayList<Integer> num = new ArrayList<>();

        for (String value : input){
            int parsedValue = parseInteger(value);
            num.add(parsedValue); // Autoboxing: int to Integer.
        }

        int sum = 0;
        for (Integer num1 : num){
            sum += num1; // Unboxing: Integer to int.
        }
        System.out.println("Numbers: " + num);
        System.out.println("Sum: "+ sum);
    }  
}
