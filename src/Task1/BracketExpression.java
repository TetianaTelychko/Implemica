package Task1;

import java.util.Arrays;
import java.math.BigInteger;
/**
 * Using BigInteger to get big numbers
 */

/**
 * Class for bracket expressions
 * Class stores method to find the correct bracket expressions using Narayana's Algorithm
 */
public class BracketExpression {
    private final long n;
    private static final char open = '(';
    private static final char closed = ')';
    private final char[] bracketExpression;
    /**
     * Replacing brackets with numbers to use the algorithm
     */
    private static final int openNumber = 0;
    private static final int closedNumber = 1;
    private int[] bracketsToNumbers;

    public BracketExpression(int n){
        this.n = n;
        bracketExpression = new char[n * 2];
        bracketsToNumbers = new int[n * 2];
        int i = 0;
        while(i < bracketExpression.length){
            if(i >= n){
                bracketExpression[i] = closed;
                bracketsToNumbers[i] = closedNumber;
            }
            else{
                bracketExpression[i] = open;
                bracketsToNumbers[i] = openNumber;
            }
            i++;
        }
    }

    /**
     * Method for displaying short answer
     */
    public void getCorrectSequenceOfBrackets(){
        int[] buffer = Arrays.copyOf(bracketsToNumbers, bracketsToNumbers.length);
        long correctSequenceOfBrackets = 0;
        BigInteger permutations = permutationsWithRepetitions(n);
        BigInteger MAX_VALUE_INT = BigInteger.valueOf(Integer.MAX_VALUE);
        int compare = permutations.compareTo(MAX_VALUE_INT);
        if(compare != 1){
            int intValue = permutations.intValue();
            for(long i = 0; i < intValue; i++){
                if(correctSequence(bracketsToNumbers)){
                    correctSequenceOfBrackets++;
                }
                nextSequence(bracketsToNumbers);
            }
            System.out.println("Number of correct sequences of brackets : " + correctSequenceOfBrackets);
            bracketsToNumbers = Arrays.copyOf(buffer, buffer.length);
        }
        else{
            System.out.println("The number is too large.");
            System.exit(0);
        }
    }
    /**
     * Method for displaying answer with explanation
     */
    public void getInfo(){
        int[] buffer = Arrays.copyOf(bracketsToNumbers, bracketsToNumbers.length);
        System.out.print("N = " + n + ", Array of brackets : ");
        System.out.println(Arrays.toString(bracketExpression));
        long correctSequenceOfBrackets = 0;
        BigInteger permutations = permutationsWithRepetitions(n);
        BigInteger MAX_VALUE_INT = BigInteger.valueOf(Integer.MAX_VALUE);
        int compare = permutations.compareTo(MAX_VALUE_INT);
        if(compare == 1){
            System.out.println("The number is too large.");
            System.exit(0);
        }
        long counter = 1;
        System.out.println("Correct sequences of brackets : ");
        for(long i = 0; i < permutations.intValue(); i++){
            if(correctSequence(bracketsToNumbers)){
                correctSequenceOfBrackets++;
                System.out.print(counter + " : " );
                System.out.println(numbersToBrackets(bracketsToNumbers));
                counter++;
            }
            nextSequence(bracketsToNumbers);
        }
        System.out.println("Number of correct sequences of brackets : " + correctSequenceOfBrackets);
        System.out.println("\nAll sequences of brackets : ");
        counter = 1;
        bracketsToNumbers = Arrays.copyOf(buffer, buffer.length);
        for(long i = 0; i < permutations.intValue(); i++){
            System.out.print(counter + " : " );
            System.out.println(numbersToBrackets(bracketsToNumbers));
            counter++;
            nextSequence(bracketsToNumbers);
        }
        System.out.println("Number of sequences of brackets : " + permutations);
        bracketsToNumbers = Arrays.copyOf(buffer, buffer.length);
    }

    /**
     * Method for converting an array of numbers into a bracket expression
     */
    private static char[] numbersToBrackets(int... numbers){
        char[] brackets = new char[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == openNumber){
                brackets[i] = open;
            }
            else{
                brackets[i] = closed;
            }
        }
        return brackets;
    }
    /**
     * Method for checking a bracket expression for the correct sequence
     */
    private static boolean correctSequence(int... numbers){
        int counter = 0;
        for (int num : numbers) {
            if (num == openNumber) {
                counter++;
            } else {
                counter--;
            }
            if (counter < 0) {
                return false;
            }
        }
        return counter == 0;
    }

    /**
     * Method for obtaining a new bracket expression using Narayana's algorithm
     */
    private int[] nextSequence(int... numbers){
        //1 step
        int indexI = searchIndexI(numbers);
        int I = numbers[indexI];
        //2 step
        int indexJ = searchIndexJ(I, numbers);
        int J = numbers[indexJ];
        //3 step
        swap(indexI, indexJ, numbers);
        //4 step
        sorting(indexI, numbers);

        return numbers;
    }

    /**
     * Method for finding the number of permutations with repetitions by the formula
     */
    public static BigInteger permutationsWithRepetitions(long n){
        BigInteger numerator = getFactorial(n * 2);
        BigInteger denominator = getFactorial(n).multiply(getFactorial(n));
        return numerator.divide(denominator);
    }
    public static BigInteger getFactorial(long f) {
        BigInteger bigInteger = BigInteger.valueOf(f);
        if (f <= 1) {
            return BigInteger.valueOf(1);
        }
        else {
            return bigInteger.multiply(getFactorial(f - 1));
        }
    }

    /**
     * Methods for Implementing the Narayana Algorithm :
     */
    private static int searchIndexI(int... numbers) {
        int indexI = 0;
        int maxIndex = numbers.length - 1;
        for(int index = maxIndex; index >= 1; index--){
            if(numbers[index - 1] < numbers[index]){
                indexI = index - 1;
                return indexI;
            }
        }
        return indexI;
    }
    private static int searchIndexJ(int I, int... numbers){
        int indexJ = 0;
        int maxIndex = numbers.length - 1;
        for(int index = maxIndex; index >= 0; index--){
            if(numbers[index] > I){
                indexJ = index;
                return indexJ;
            }
        }
        return indexJ;
    }
    private static void swap(int indexI, int indexJ, int... numbers){
        int buffer = numbers[indexI];
        numbers[indexI] = numbers[indexJ];
        numbers[indexJ] = buffer;
    }
    private static void sorting(int indexI, int... numbers){
        int[] bufferArray = new int[numbers.length - (indexI + 1)];
        int index = 0;
        for(int i = indexI + 1; i < numbers.length; i++){
            bufferArray[index] = numbers[i];
            index++;
        }
        reverse(bufferArray);
        index = 0;
        for(int i = indexI + 1; i < numbers.length; i++){
            numbers[i] = bufferArray[index];
            index++;
        }
    }
    private static int[] reverse(int... array){
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }
}

