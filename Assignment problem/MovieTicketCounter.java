import java.util.Locale;
import java.util.Scanner;

public class MovieTicketCounter {
    private abstract static class Seat {
        private static final double CONVENIENCE_FEE = 20.0;

        protected abstract double ticketPrice();

        final double bookingAmount(int count) {
            return (ticketPrice() + CONVENIENCE_FEE) * count;
        }
    }

    private static final class RegularSeat extends Seat {
        @Override protected double ticketPrice() { return 150.0; }
    }

    private static final class PremiumSeat extends Seat {
        @Override protected double ticketPrice() { return 250.0; }
    }

    private static final class ReclinerSeat extends Seat {
        @Override protected double ticketPrice() { return 400.0; }
    }

    private static Seat createSeat(String type) {
        switch (type) {
            case "REGULAR": return new RegularSeat();
            case "PREMIUM": return new PremiumSeat();
            case "RECLINER": return new ReclinerSeat();
            default: throw new IllegalArgumentException("Unknown seat type: " + type);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
            int bookings = scanner.nextInt();
            double total = 0.0;
            for (int i = 0; i < bookings; i++) {
                String type = scanner.next().toUpperCase(Locale.ROOT);
                int count = scanner.nextInt();
                double amount = createSeat(type).bookingAmount(count);
                System.out.printf("%s: %.2f%n", type, amount);
                total += amount;
            }
            System.out.printf("Total: %.2f%n", total);
        }
    }
}
