import java.util.Scanner;

/**
 * Counts present days and finds the longest consecutive attendance streak.
 * attendanceSummary runs in O(n) time and uses O(1) additional space.
 */
public class ClassAttendanceTracker {
    public static final class Summary {
        public final int presentDays;
        public final int longestStreak;

        private Summary(int presentDays, int longestStreak) {
            this.presentDays = presentDays;
            this.longestStreak = longestStreak;
        }
    }

    /** Returns the number of 1s and the longest consecutive run of 1s. */
    public static Summary attendanceSummary(int[] days) {
        if (days == null || days.length == 0) {
            throw new IllegalArgumentException("At least one attendance day is required.");
        }

        int presentDays = 0;
        int currentStreak = 0;
        int longestStreak = 0;

        for (int day : days) {
            if (day == 1) {
                presentDays++;
                currentStreak++;
                longestStreak = Math.max(longestStreak, currentStreak);
            } else if (day == 0) {
                currentStreak = 0;
            } else {
                throw new IllegalArgumentException("Each attendance value must be 0 or 1.");
            }
        }

        return new Summary(presentDays, longestStreak);
    }

    public static void main(String[] args) {
        StringBuilder input = new StringBuilder();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) input.append(scanner.nextLine()).append(' ');
        }

        // Accepts array-style input such as [1, 1, 0, 1] or whitespace-separated values.
        String valuesOnly = input.toString().replaceAll("[^01]", "");
        if (valuesOnly.isEmpty()) {
            throw new IllegalArgumentException("Enter attendance values consisting of 0s and 1s.");
        }

        int[] days = new int[valuesOnly.length()];
        for (int i = 0; i < valuesOnly.length(); i++) {
            days[i] = valuesOnly.charAt(i) - '0';
        }

        Summary summary = attendanceSummary(days);
        System.out.printf("Present: %d, Longest streak: %d%n",
                summary.presentDays, summary.longestStreak);
    }
}
