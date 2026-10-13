import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Counts exam scores in the inclusive range [low, high] in a sorted array.
 *
 * A linear scan takes O(n) time and O(1) additional space. This solution
 * takes O(log n) time and O(1) additional space by using two binary searches.
 * Lower bound finds the first score >= low; upper bound finds the first score
 * > high. Their difference includes duplicates at both band edges.
 */
public class ExamScoreBandCounter {
    public static int countInBand(int[] scores, int low, int high) {
        if (scores == null || scores.length == 0) {
            throw new IllegalArgumentException("At least one score is required.");
        }
        if (low > high) {
            throw new IllegalArgumentException("low must not be greater than high.");
        }

        int firstAtLeastLow = lowerBound(scores, low);
        int firstGreaterThanHigh = upperBound(scores, high);
        return firstGreaterThanHigh - firstAtLeastLow;
    }

    // Returns the first index whose value is >= target, or scores.length.
    private static int lowerBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (scores[middle] < target) left = middle + 1;
            else right = middle;
        }
        return left;
    }

    // Returns the first index whose value is > target, or scores.length.
    private static int upperBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (scores[middle] <= target) left = middle + 1;
            else right = middle;
        }
        return left;
    }

    public static void main(String[] args) {
        StringBuilder input = new StringBuilder();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) input.append(scanner.nextLine()).append(' ');
        }

        Matcher arrayMatcher = Pattern.compile("\\[([^\\]]*)\\]").matcher(input.toString());
        if (!arrayMatcher.find()) {
            throw new IllegalArgumentException("Enter scores as an ascending array in brackets.");
        }

        List<Integer> values = new ArrayList<>();
        Matcher scoreMatcher = Pattern.compile("\\d+").matcher(arrayMatcher.group(1));
        while (scoreMatcher.find()) values.add(Integer.parseInt(scoreMatcher.group()));
        if (values.isEmpty()) throw new IllegalArgumentException("The scores array cannot be empty.");

        Matcher lowMatcher = Pattern.compile("(?i)low\\s*=\\s*(\\d+)").matcher(input);
        Matcher highMatcher = Pattern.compile("(?i)high\\s*=\\s*(\\d+)").matcher(input);
        if (!lowMatcher.find() || !highMatcher.find()) {
            throw new IllegalArgumentException("Enter both low = value and high = value.");
        }

        int[] scores = values.stream().mapToInt(Integer::intValue).toArray();
        int low = Integer.parseInt(lowMatcher.group(1));
        int high = Integer.parseInt(highMatcher.group(1));
        System.out.println(countInBand(scores, low, high));
    }
}
