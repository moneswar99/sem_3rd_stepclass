import java.math.BigInteger;
import java.util.Scanner;

public class DigitSumAndNumberReversal {
    private static BigInteger reverseDigits(BigInteger number) {
        BigInteger reversed = BigInteger.ZERO;
        BigInteger ten = BigInteger.TEN;
        while (number.signum() > 0) {
            BigInteger[] division = number.divideAndRemainder(ten);
            reversed = reversed.multiply(ten).add(division[1]);
            number = division[0];
        }
        return reversed;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextBigInteger()) {
                BigInteger number = scanner.nextBigInteger();
                if (number.signum() < 0) {
                    throw new IllegalArgumentException("Input must be a positive integer.");
                }
                int sum = 0;
                for (char digit : number.toString().toCharArray()) sum += digit - '0';
                System.out.println("Sum of digits: " + sum);
                System.out.println("Reverse: " + reverseDigits(number));
            }
        }
    }
}
