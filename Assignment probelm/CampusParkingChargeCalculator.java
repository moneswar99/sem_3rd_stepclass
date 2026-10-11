import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CampusParkingChargeCalculator {
    private interface Vehicle { String type(); double charge(); }
    private static final class Bike implements Vehicle {
        private final int hours;
        Bike(int hours) { this.hours = hours; }
        public String type() { return "BIKE"; }
        public double charge() { return 10.0 * hours; }
    }
    private static final class Car implements Vehicle {
        private final int hours;
        Car(int hours) { this.hours = hours; }
        public String type() { return "CAR"; }
        public double charge() { return 30.0 + 20.0 * (hours - 1); }
    }
    private static final class Truck implements Vehicle {
        private final int hours;
        Truck(int hours) { this.hours = hours; }
        public String type() { return "TRUCK"; }
        public double charge() { return Math.max(100.0, 50.0 * hours); }
    }
    private static Vehicle createVehicle(String[] parts) {
        int hours = Integer.parseInt(parts[1]);
        switch (parts[0].toUpperCase(Locale.ROOT)) {
            case "BIKE": return new Bike(hours);
            case "CAR": return new Car(hours);
            case "TRUCK": return new Truck(hours);
            default: throw new IllegalArgumentException("Unknown vehicle type: " + parts[0]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < n; i++)
            vehicles.add(createVehicle(scanner.nextLine().trim().split(" +")));
        double total = 0;
        for (Vehicle vehicle : vehicles) {
            double charge = vehicle.charge();
            System.out.printf(Locale.US, "%s: %.2f%n", vehicle.type(), charge);
            total += charge;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
