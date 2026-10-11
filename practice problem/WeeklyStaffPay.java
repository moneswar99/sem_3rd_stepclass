import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class WeeklyStaffPay {
    private abstract static class StaffMember {
        private final String name;
        StaffMember(String name) { this.name = name; }
        String name() { return name; }
        abstract double weeklyPay();
    }
    private static final class FullTimeStaff extends StaffMember {
        private final double salary;
        FullTimeStaff(String name, double salary) { super(name); this.salary = salary; }
        double weeklyPay() { return salary; }
    }
    private static final class HourlyStaff extends StaffMember {
        private final double hours, rate;
        HourlyStaff(String name, double hours, double rate) {
            super(name); this.hours = hours; this.rate = rate;
        }
        double weeklyPay() {
            double regularHours = Math.min(hours, 40.0);
            double overtimeHours = Math.max(0.0, hours - 40.0);
            return regularHours * rate + overtimeHours * rate * 1.5;
        }
    }
    private static final class Intern extends StaffMember {
        private final double stipend;
        Intern(String name, double stipend) { super(name); this.stipend = stipend; }
        double weeklyPay() { return stipend; }
    }
    private static StaffMember createStaff(String[] p) {
        switch (p[0].toUpperCase(Locale.ROOT)) {
            case "FULLTIME": return new FullTimeStaff(p[1], Double.parseDouble(p[2]));
            case "HOURLY": return new HourlyStaff(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]));
            case "INTERN": return new Intern(p[1], Double.parseDouble(p[2]));
            default: throw new IllegalArgumentException("Unknown staff type: " + p[0]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<StaffMember> staff = new ArrayList<>();
        for (int i = 0; i < n; i++) staff.add(createStaff(scanner.nextLine().trim().split(" +")));
        double total = 0;
        for (StaffMember member : staff) {
            double pay = member.weeklyPay();
            System.out.printf(Locale.US, "%s: %.2f%n", member.name(), pay);
            total += pay;
        }
        System.out.printf(Locale.US, "Total Payroll: %.2f%n", total);
    }
}
