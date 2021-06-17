package Task3;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Input a number for the sum of the digits of its factorial: ");
        int num = in.nextInt();
        in.nextLine();
        Digits.getSumOfDigits(num);
        in.close();
    }
}
