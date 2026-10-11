import java.util.Scanner;

public class PrimeNumberChecker {
    private static boolean isPrime(long number) {
        if (number < 2) return false;
        if (number % 2 == 0) return number == 2;
        for (long divisor = 3; divisor <= number / divisor; divisor += 2) {
            if (number % divisor == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLong()) {
                long number = scanner.nextLong();
                System.out.println(number + (isPrime(number) ? " is prime" : " is not prime"));
            }
        }
    }
}
