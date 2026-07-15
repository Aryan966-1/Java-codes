import java.util.Scanner;
public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int vowel = 0;
        int consonant = 0;
        int digits = 0;
        int sp_char = 0;
        System.out.print("Enter a string: ");
        String st = sc.nextLine();
        for (int i = 0; i < st.length(); i++){
            if (st.charAt(i) == 'a' || st.charAt(i) == 'e' || st.charAt(i) == 'i' || st.charAt(i) == 'o' || st.charAt(i) == 'u'){
                vowel++;
            }
            else if (st.charAt(i) >= 'a' && st.charAt(i) <= 'z'){
                consonant++;
            }
            else if(st.charAt(i) == 'A' || st.charAt(i) == 'E' || st.charAt(i) == 'I' || st.charAt(i) == 'O' || st.charAt(i) == 'U'){
                vowel++;
            }
            else if (st.charAt(i) >= 'A' && st.charAt(i) <= 'Z'){
                consonant++;
            }
            else if (st.charAt(i) >= '0' && st.charAt(i) <= '9'){
                digits++;
            }
            else{
                sp_char++;
            }
        }
        System.out.println("Total number of Vowel: "+ vowel);
        System.out.println("Total number of Consonant: "+ consonant);
        System.out.println("Total number of digits: "+ digits);
        System.out.println("Total number of sp_char: "+ sp_char);
        sc.close();
    }
}