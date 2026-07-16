// Program to perform matrix operations (addition, subtraction, and multiplication) on two matrices provided by the user 
import java.util.Scanner;
public class Matrix_Operation {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int row, col;
        System.out.print("Enter the number of rows you want in the matrix: ");
        row = sc.nextInt();
        System.out.print("Enter the number of columns you want in the matrix: ");
        col = sc.nextInt();
        //First Matrix
        int[][] m = new int[row][col];
        System.out.println("Enter the elements of the First Matrix: ");
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print("Enter the element at pos[" + i + "][" + j + "]: ");
                m[i][j] = sc.nextInt();
            }
        }
        //Second Matrix
        int [][] m1 = new int[row][col];
        System.out.println("Enter the elements of the Second Matrix: ");
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print("Enter the element at pos[" + i + "][" + j + "]: ");
                m1[i][j] = sc.nextInt();
            }
        }
        //Matrix Addition
        int [][] sum = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                sum[i][j] = m[i][j] + m1[i][j];            }
        }
        System.out.println("The sum of the two matrices is: ");
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }
        //Matrix Subtraction
        int[][] diff = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                diff[i][j] = m[i][j] - m1[i][j];
            }
        }
        System.out.println("The difference of the two matrices are: ");
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(diff[i][j] + " ");
            }
            System.out.println();
        }
        //Matrix Multiplication
        int[][] prod = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                prod[i][j] = 0;
                for(int k = 0; k < col; k++){
                    prod[i][j] += m[i][k] * m1[k][j];
                }
            }
        }
        System.out.println("The product of the two matrices is: ");
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(prod[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
