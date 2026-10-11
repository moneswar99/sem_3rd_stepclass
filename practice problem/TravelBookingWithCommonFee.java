import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TravelBookingWithCommonFee {
    private abstract static class Booking {
        private static final double BOOKING_FEE = 50.0;
        private final double distanceKm;
        Booking(double distanceKm) { this.distanceKm = distanceKm; }
        double distanceKm() { return distanceKm; }
        abstract String mode();
        abstract double baseFare();
        final double totalFare() { return baseFare() + BOOKING_FEE; }
    }
    private static final class BusBooking extends Booking {
        BusBooking(double distanceKm) { super(distanceKm); }
        String mode() { return "BUS"; }
        double baseFare() { return distanceKm() * 2.0; }
    }
    private static final class TrainBooking extends Booking {
        TrainBooking(double distanceKm) { super(distanceKm); }
        String mode() { return "TRAIN"; }
        double baseFare() { return distanceKm() * 1.5; }
    }
    private static final class FlightBooking extends Booking {
        FlightBooking(double distanceKm) { super(distanceKm); }
        String mode() { return "FLIGHT"; }
        double baseFare() { return 2500.0 + distanceKm() * 4.0; }
    }
    private static Booking createBooking(String[] p) {
        double distance = Double.parseDouble(p[1]);
        switch (p[0].toUpperCase(Locale.ROOT)) {
            case "BUS": return new BusBooking(distance);
            case "TRAIN": return new TrainBooking(distance);
            case "FLIGHT": return new FlightBooking(distance);
            default: throw new IllegalArgumentException("Unknown travel mode: " + p[0]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Booking> bookings = new ArrayList<>();
        for (int i = 0; i < n; i++) bookings.add(createBooking(scanner.nextLine().trim().split(" +")));
        for (Booking booking : bookings)
            System.out.printf(Locale.US, "%s: %.2f%n", booking.mode(), booking.totalFare());
    }
}
