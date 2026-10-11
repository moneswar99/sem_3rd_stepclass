import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentResultCardGenerator {
    private static final Pattern MARK_PATTERN = Pattern.compile("-?\\d+(?:\\.\\d+)?");

    private static char gradeFor(double average) {
        if (average >= 90.0) return 'A';
        if (average >= 75.0) return 'B';
        if (average >= 60.0) return 'C';
        if (average >= 40.0) return 'D';
        return 'F';
    }

    private static void printResult(String line) {
        Matcher matcher = MARK_PATTERN.matcher(line);
        double[] marks = new double[3];
        int count = 0;
        int firstMarkStart = -1;
        while (matcher.find() && count < marks.length) {
            if (firstMarkStart < 0) firstMarkStart = matcher.start();
            marks[count++] = Double.parseDouble(matcher.group());
        }
        if (count != 3) {
            throw new IllegalArgumentException("Each student line must contain a name and three marks: " + line);
        }

        String name = line.substring(0, firstMarkStart).replaceAll("[\\[,:\\s]+$", "").trim();
        if (name.isEmpty()) throw new IllegalArgumentException("Student name is missing: " + line);

        double average = (marks[0] + marks[1] + marks[2]) / 3.0;
        System.out.printf("%s: Average %.1f, Grade %c%n",
                name.toUpperCase(Locale.ROOT), average, gradeFor(average));
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) printResult(line);
            }
        }
    }
}
