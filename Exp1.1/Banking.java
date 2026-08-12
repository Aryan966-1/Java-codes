// Create a Java program to implement a basic banking system with the following features: 
// Account creation (Name, Account Number, Balance). 
// Deposit and withdrawal operations. 
import java.util.Scanner;
public class Banking {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your Account Number: ");
        String acc_num = sc.nextLine();
        System.out.print("Enter your initial Balance: ");
        double balance = sc.nextDouble();
        System.out.println("Account created successfully!");
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + acc_num);   
        System.out.println("Balance: " + balance);
        System.out.println("Enter 1 to deposit, 2 to withdraw: ");
        int choice = sc.nextInt();
        if(choice == 1){
            System.out.print("Enter Account number: ");
            String acc_num1 = sc.next();
            if(acc_num1.equals(acc_num)){
                System.out.print("Enter amount to deposit: ");
                double deposit = sc.nextDouble();
                balance += deposit;
                System.out.println("Amount deposited successfully!");
                System.out.println("New Balance: " + balance);
            } else {
                System.out.println("Invalid Account Number!");
            }
        }
        else if(choice == 2){
            String acc_num1 = sc.next();
            System.out.print("Enter Account number: ");
            if(acc_num1.equals(acc_num)){
                System.out.print("Enter amount to withdraw: ");
                double withdraw = sc.nextDouble();
                if(withdraw <= balance){
                    balance -= withdraw;
                    System.out.println("Amount withdrawn successfully!");
                    System.out.println("New Balance: " + balance);
                } else {
                    System.out.println("Insufficient funds!");
                }
            } else {
                System.out.println("Invalid Account Number!");
            }
        }
        sc.close();
    }
    
}
