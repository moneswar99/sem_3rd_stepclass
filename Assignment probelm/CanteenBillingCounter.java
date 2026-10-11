import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CanteenBillingCounter {
    private interface Customer { String type(); double finalAmount(double amount); }
    private static final class Student implements Customer {
        public String type() { return "STUDENT"; }
        public double finalAmount(double amount) { return amount * 0.90; }
    }
    private static final class Staff implements Customer {
        public String type() { return "STAFF"; }
        public double finalAmount(double amount) { return amount * 0.95; }
    }
    private static final class Guest implements Customer {
        public String type() { return "GUEST"; }
        public double finalAmount(double amount) { return amount + 10; }
    }
    private static Customer customerFor(String type) {
        switch (type.toUpperCase(Locale.ROOT)) {
            case "STUDENT": return new Student();
            case "STAFF": return new Staff();
            case "GUEST": return new Guest();
            default: throw new IllegalArgumentException("Unknown customer type: " + type);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Customer> customers = new ArrayList<>();
        List<Double> amounts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split(" +");
            customers.add(customerFor(parts[0]));
            amounts.add(Double.parseDouble(parts[1]));
        }
        double total = 0;
        for (int i = 0; i < n; i++) {
            Customer customer = customers.get(i);
            double amount = customer.finalAmount(amounts.get(i));
            System.out.printf(Locale.US, "%s: %.2f%n", customer.type(), amount);
            total += amount;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
