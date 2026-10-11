import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ElectricityConnectionBilling {
    private abstract static class Connection {
        private final int units;
        Connection(int units) { this.units = units; }
        int units() { return units; }
        abstract String type();
        abstract double bill();
    }
    private static final class Home extends Connection {
        Home(int units) { super(units); }
        String type() { return "HOME"; }
        double bill() {
            int units = units();
            return Math.min(units, 100) * 5.0 + Math.max(0, units - 100) * 7.0;
        }
    }
    private static final class Shop extends Connection {
        Shop(int units) { super(units); }
        String type() { return "SHOP"; }
        double bill() { return units() * 8.0 + 100.0; }
    }
    private static final class Factory extends Connection {
        Factory(int units) { super(units); }
        String type() { return "FACTORY"; }
        double bill() { return Math.max(units() * 6.0, 1000.0); }
    }
    private static Connection createConnection(String[] p) {
        int units = Integer.parseInt(p[1]);
        switch (p[0].toUpperCase(Locale.ROOT)) {
            case "HOME": return new Home(units);
            case "SHOP": return new Shop(units);
            case "FACTORY": return new Factory(units);
            default: throw new IllegalArgumentException("Unknown connection type: " + p[0]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < n; i++) connections.add(createConnection(scanner.nextLine().trim().split(" +")));
        double total = 0;
        for (Connection connection : connections) {
            double amount = connection.bill();
            System.out.printf(Locale.US, "%s: %.2f%n", connection.type(), amount);
            total += amount;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
