package Task1;
/**
 * getInfo() method commented out for a detailed answer
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a number in the interval [1, 16] : ");
        int N = in.nextInt();
        in.nextLine();
        BracketExpression bracketExpression = new BracketExpression(N);
        bracketExpression.getCorrectSequenceOfBrackets();
        //System.out.println("\nDetailed answer : \n");
        //bracketExpression.getInfo();
        in.close();
    }
}

