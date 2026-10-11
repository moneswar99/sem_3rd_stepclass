import java.util.Locale;
import java.util.Scanner;

public class CollegeFeeCounter {
    private interface BusCommuter {
        double TRANSPORT_FEE = 12_000.0;

        default double transportFee() { return TRANSPORT_FEE; }
    }

    private abstract static class Student {
        protected final String name;

        Student(String name) { this.name = name; }

        abstract double tuition();

        double accommodationFee() { return 0.0; }

        final double totalFee() {
            double transport = this instanceof BusCommuter
                    ? ((BusCommuter) this).transportFee() : 0.0;
            return tuition() + accommodationFee() + transport;
        }
    }

    private static final class DayScholar extends Student implements BusCommuter {
        DayScholar(String name) { super(name); }
        @Override double tuition() { return 40_000.0; }
    }

    private static final class Hosteller extends Student {
        Hosteller(String name) { super(name); }
        @Override double tuition() { return 40_000.0; }
        @Override double accommodationFee() { return 60_000.0; }
    }

    private static final class ScholarshipStudent extends Student implements BusCommuter {
        ScholarshipStudent(String name) { super(name); }
        @Override double tuition() { return 20_000.0; }
    }

    private static Student createStudent(String type, String name) {
        switch (type) {
            case "DAY_SCHOLAR": return new DayScholar(name);
            case "HOSTELLER": return new Hosteller(name);
            case "SCHOLAR": return new ScholarshipStudent(name);
            default: throw new IllegalArgumentException("Unknown student type: " + type);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
            int students = scanner.nextInt();
            double totalCollected = 0.0;
            for (int i = 0; i < students; i++) {
                String type = scanner.next().toUpperCase(Locale.ROOT);
                String name = scanner.next();
                Student student = createStudent(type, name);
                double fee = student.totalFee();
                System.out.printf("%s: %.2f%n", student.name, fee);
                totalCollected += fee;
            }
            System.out.printf("Total Collected: %.2f%n", totalCollected);
        }
    }
}
