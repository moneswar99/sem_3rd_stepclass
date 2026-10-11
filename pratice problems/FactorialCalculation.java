import java.math.BigInteger;
import java.util.Scanner;

public class FactorialCalculation {
    private static BigInteger factorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int factor = 2; factor <= number; factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }
        return result;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number < 0) {
                    throw new IllegalArgumentException("Factorial is defined here for non-negative integers.");
                }
                System.out.println(factorial(number));
            }
        }
    }
}
