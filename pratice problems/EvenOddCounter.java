import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvenOddCounter {
    public static void main(String[] args) {
        StringBuilder input = new StringBuilder();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) input.append(scanner.nextLine()).append(' ');
        }

        List<Long> numbers = extractIntegers(input.toString());
        int even = 0;
        for (long number : numbers) {
            if (number % 2 == 0) even++;
        }
        System.out.println("Even: " + even);
        System.out.println("Odd: " + (numbers.size() - even));
    }

    private static List<Long> extractIntegers(String input) {
        List<Long> numbers = new ArrayList<>();
        Matcher matcher = Pattern.compile("-?\\d+").matcher(input);
        while (matcher.find()) numbers.add(Long.parseLong(matcher.group()));
        return numbers;
    }
}
