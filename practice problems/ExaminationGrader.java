import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ExaminationGrader {
    private interface Question { String type(); double grade(); }
    private static final class ObjectiveQuestion implements Question {
        private final String type, correct, answer;
        private final double points;
        ObjectiveQuestion(String type, String correct, String answer, double points) {
            this.type = type; this.correct = correct; this.answer = answer; this.points = points;
        }
        public String type() { return type; }
        public double grade() { return correct.equals(answer) ? points : 0; }
    }
    private static final class EssayQuestion implements Question {
        private final String correct, answer;
        private final double points;
        EssayQuestion(String correct, String answer, double points) {
            this.correct = correct; this.answer = answer; this.points = points;
        }
        public String type() { return "ESSAY"; }
        public double grade() {
            String response = answer.toLowerCase(Locale.ROOT);
            int matched = 0;
            for (String keyword : correct.split(",")) {
                String term = keyword.trim().toLowerCase(Locale.ROOT);
                if (!term.isEmpty() && response.contains(term)) matched++;
            }
            if (matched >= 2) return points * 0.75;
            if (matched == 1) return points * 0.50;
            return 0;
        }
    }
    private static List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        boolean quoted = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == 34) quoted = !quoted;
            else if (Character.isWhitespace(c) && !quoted) {
                if (token.length() > 0) { tokens.add(token.toString()); token.setLength(0); }
            } else token.append(c);
        }
        if (token.length() > 0) tokens.add(token.toString());
        return tokens;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> p = tokenize(scanner.nextLine());
            String type = p.get(0).toUpperCase(Locale.ROOT);
            String correct = p.get(2), answer = p.get(3);
            double points = Double.parseDouble(p.get(4));
            if (type.equals("ESSAY")) questions.add(new EssayQuestion(correct, answer, points));
            else if (type.equals("MCQ") || type.equals("TF"))
                questions.add(new ObjectiveQuestion(type, correct, answer, points));
            else throw new IllegalArgumentException("Unknown question type: " + type);
        }
        double total = 0;
        for (Question question : questions) {
            double score = question.grade();
            System.out.printf(Locale.US, "%s: %.2f%n", question.type(), score);
            total += score;
        }
        System.out.printf(Locale.US, "Total Score: %.2f%n", total);
    }
}
