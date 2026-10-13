import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Finds the second-highest distinct score without sorting.
 *
 * The scan takes O(n) time and O(1) additional space. Sorting first would
 * typically take O(n log n) time and may use additional space, then still
 * require a scan to skip duplicate top scores.
 */
public class SecondBestScore {
    public static int secondHighest(int[] scores) {
        if (scores == null || scores.length == 0) {
            throw new IllegalArgumentException("At least one score is required.");
        }

        int highest = -1;
        int secondHighest = -1;

        for (int score : scores) {
            if (score < 0 || score > 100) {
                throw new IllegalArgumentException("Scores must be between 0 and 100.");
            }

            if (score > highest) {
                secondHighest = highest;
                highest = score;
            } else if (score < highest && score > secondHighest) {
                secondHighest = score;
            }
        }

        return secondHighest;
    }

    public static void main(String[] args) {
        StringBuilder input = new StringBuilder();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) input.append(scanner.nextLine()).append(' ');
        }

        String valuesText = input.toString();
        Matcher arrayMatcher = Pattern.compile("\\[([^\\]]*)\\]").matcher(valuesText);
        boolean hasArray = arrayMatcher.find();
        if (hasArray) valuesText = arrayMatcher.group(1);

        Matcher matcher = Pattern.compile("\\d+").matcher(valuesText);
        List<Integer> values = new ArrayList<>();
        while (matcher.find()) values.add(Integer.parseInt(matcher.group()));

        // Supports an optional leading count when it matches the remaining values.
        if (!hasArray && values.size() > 1 && values.get(0) == values.size() - 1) {
            values.remove(0);
        }
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Enter at least one score.");
        }

        int[] scores = values.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(secondHighest(scores));
    }
}
