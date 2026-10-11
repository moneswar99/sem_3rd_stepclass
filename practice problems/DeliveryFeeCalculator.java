import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
public class DeliveryFeeCalculator {
    private interface Delivery {
        String name();
        double fee();
    }
    private static final class StandardDelivery implements Delivery {
        private final double weight, distance;
        StandardDelivery(double weight, double distance) { this.weight = weight; this.distance = distance; }
        public String name() { return "STANDARD"; }
        public double fee() { return 5 + 0.50 * weight + 0.10 * distance; }
    }
    private static final class ExpressDelivery implements Delivery {
        private final double weight, distance;
        ExpressDelivery(double weight, double distance) { this.weight = weight; this.distance = distance; }
        public String name() { return "EXPRESS"; }
        public double fee() { return 15 + weight + 0.20 * distance; }
    }
    private static final class InternationalDelivery implements Delivery {
        private final double weight, distance, customsFee;
        InternationalDelivery(double weight, double distance, double customsFee) {
            this.weight = weight; this.distance = distance; this.customsFee = customsFee;
        }
        public String name() { return "INTERNATIONAL"; }
        public double fee() { return 25 + 2 * weight + 0.50 * distance + customsFee; }
    }
    private static Delivery createDelivery(String[] p) {
        switch (p[0].toUpperCase(Locale.ROOT)) {
          case "STANDARD": return new StandardDelivery(Double.parseDouble(p[1]), Double.parseDouble(p[2]));
          case "EXPRESS": return new ExpressDelivery(Double.parseDouble(p[1]), Double.parseDouble(p[2]));
          case "INTERNATIONAL": return new InternationalDelivery(
                    Double.parseDouble(p[1]), Double.parseDouble(p[2]), Double.parseDouble(p[3]));
          default: throw new IllegalArgumentException("Unknown delivery type: " + p[0]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Delivery> deliveries = new ArrayList<>();
        for (int i = 0; i < n; i++)
            deliveries.add(createDelivery(scanner.nextLine().trim().split(" +")));
        double total = 0;
        for (Delivery delivery : deliveries) {
            double fee = delivery.fee();
            System.out.printf(Locale.US, "%s: %.2f%n", delivery.name(), fee);
            total += fee;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
