import java.util.Scanner;

public class LeapYearDetector {
    private static boolean isLeapYear(long year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLong()) {
                long year = scanner.nextLong();
                System.out.println(isLeapYear(year) ? "Leap year" : "Not a leap year");
            }
        }
    }
}
