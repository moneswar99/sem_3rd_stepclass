import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Rotates a roster to the right without performing one rotation at a time.
 *
 * The method takes O(n) time and O(n) additional space. Rotating one place
 * at a time k times can take O(n * k) time, which is slow for large k.
 */
public class DutyRosterRotation {
    public static String[] rotateRoster(String[] names, long k) {
        if (names == null || names.length == 0) {
            throw new IllegalArgumentException("The roster must contain at least one name.");
        }
        if (k < 0) {
            throw new IllegalArgumentException("Rotation count cannot be negative.");
        }

        int length = names.length;
        int shift = (int) (k % length);
        String[] rotated = new String[length];

        for (int i = 0; i < length; i++) {
            rotated[(i + shift) % length] = names[i];
        }
        return rotated;
    }

    public static void main(String[] args) {
        StringBuilder input = new StringBuilder();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) input.append(scanner.nextLine()).append(' ');
        }

        Matcher rosterMatcher = Pattern.compile("\\[([^\\]]*)\\]").matcher(input.toString());
        if (!rosterMatcher.find()) {
            throw new IllegalArgumentException("Enter the roster in brackets, for example [A, B, C].");
        }

        String[] names = Arrays.stream(rosterMatcher.group(1).split(","))
                .map(String::trim)
                .toArray(String[]::new);

        Matcher kMatcher = Pattern.compile("(?i)k\\s*=\\s*(\\d+)").matcher(input.toString());
        if (!kMatcher.find()) {
            throw new IllegalArgumentException("Enter the rotation count as k = number.");
        }
        long k = Long.parseLong(kMatcher.group(1));

        System.out.println(Arrays.toString(rotateRoster(names, k)));
    }
}
