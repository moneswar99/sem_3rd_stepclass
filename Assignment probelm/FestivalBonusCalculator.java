import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class FestivalBonusCalculator {
    private interface Employee { String name(); double bonus(); }
    private static final class FullTimeEmployee implements Employee {
        private final String name;
        private final double salary;
        FullTimeEmployee(String name, double salary) { this.name = name; this.salary = salary; }
        public String name() { return name; }
        public double bonus() { return salary * 0.10; }
    }
    private static final class PartTimeEmployee implements Employee {
        private final String name;
        private final double salary;
        PartTimeEmployee(String name, double salary) { this.name = name; this.salary = salary; }
        public String name() { return name; }
        public double bonus() { return salary * 0.05; }
    }
    private static final class Intern implements Employee {
        private final String name;
        Intern(String name) { this.name = name; }
        public String name() { return name; }
        public double bonus() { return 2000.0; }
    }
    private static Employee createEmployee(String[] parts) {
        String name = parts[1];
        double salary = Double.parseDouble(parts[2]);
        switch (parts[0].toUpperCase(Locale.ROOT)) {
            case "FULLTIME": return new FullTimeEmployee(name, salary);
            case "PARTTIME": return new PartTimeEmployee(name, salary);
            case "INTERN": return new Intern(name);
            default: throw new IllegalArgumentException("Unknown employee type: " + parts[0]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++)
            employees.add(createEmployee(scanner.nextLine().trim().split(" +")));
        double total = 0;
        for (Employee employee : employees) {
            double bonus = employee.bonus();
            System.out.printf(Locale.US, "%s: %.2f%n", employee.name(), bonus);
            total += bonus;
        }
        System.out.printf(Locale.US, "Total Bonus: %.2f%n", total);
    }
}
