package Task3;
import java.math.BigInteger;
/**
 * Using BigInteger to get big numbers
 */

public class Digits {
    /**
     * The method outputs the answer
     */
    public static void getSumOfDigits(long n){
        BigInteger bigInteger = getFactorial(n);
        System.out.println(sumOfDigits(bigInteger));
    }
    /**
     * The method returns the sum of digits
     */
    private static BigInteger sumOfDigits(BigInteger factorial){
        BigInteger bi = BigInteger.valueOf(0);
        String factorialToString = factorial.toString();
        char[] factorialToCharArray = factorialToString.toCharArray();
        for(char number : factorialToCharArray){
            BigInteger bi2 = BigInteger.valueOf(Character.getNumericValue(number));
            bi = bi.add(bi2);
        }
        return bi;
    }
    /**
     * The method returns the factorial of a number
     */
    private static BigInteger getFactorial(long f) {
        BigInteger bigInteger = BigInteger.valueOf(f);
        if (f <= 1) {
            return BigInteger.valueOf(1);
        }
        else {
            return bigInteger.multiply(getFactorial(f - 1));
        }
    }
}

