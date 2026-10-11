import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PublicTransportFareCalculator {
    private interface Transport { String name(); double fare(); }
    private static final class Bus implements Transport {
        private final double distance;
        Bus(double distance) { this.distance = distance; }
        public String name() { return "BUS"; }
        public double fare() { return Math.min(10, 2 + 0.10 * distance); }
    }
    private static final class Train implements Transport {
        private final double distance;
        Train(double distance) { this.distance = distance; }
        public String name() { return "TRAIN"; }
        public double fare() { return 3 + 0.15 * distance; }
    }
    private static final class Metro implements Transport {
        private final double distance, peakHourFactor;
        Metro(double distance, double peakHourFactor) {
            this.distance = distance; this.peakHourFactor = peakHourFactor;
        }
        public String name() { return "METRO"; }
        public double fare() { return (1.50 + 0.20 * distance) * peakHourFactor; }
    }
    private static Transport createTransport(String[] p) {
        switch (p[0].toUpperCase(Locale.ROOT)) {
            case "BUS": return new Bus(Double.parseDouble(p[1]));
            case "TRAIN": return new Train(Double.parseDouble(p[1]));
            case "METRO": return new Metro(Double.parseDouble(p[1]), Double.parseDouble(p[2]));
            default: throw new IllegalArgumentException("Unknown transport type: " + p[0]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Transport> journeys = new ArrayList<>();
        for (int i = 0; i < n; i++)
            journeys.add(createTransport(scanner.nextLine().trim().split(" +")));
        double total = 0;
        for (Transport journey : journeys) {
            double fare = journey.fare();
            System.out.printf(Locale.US, "%s: %.2f%n", journey.name(), fare);
            total += fare;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
