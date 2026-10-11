import java.util.Scanner;

public class StudentGradeAssignment {
    private static char gradeFor(int marks) {
        if (marks >= 90) return 'A';
        if (marks >= 75) return 'B';
        if (marks >= 60) return 'C';
        if (marks >= 40) return 'D';
        return 'F';
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextInt()) {
                int marks = scanner.nextInt();
                System.out.println("Grade " + gradeFor(marks));
            }
        }
    }
}
