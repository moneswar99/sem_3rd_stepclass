import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class HostelElectricityBill {
    private interface Room { String type(); double bill(); }
    private static final class SingleRoom implements Room {
        private final int units;
        SingleRoom(int units) { this.units = units; }
        public String type() { return "SINGLE"; }
        public double bill() { return units * 8.0; }
    }
    private static final class SharedRoom implements Room {
        private final int units, occupants;
        SharedRoom(int units, int occupants) { this.units = units; this.occupants = occupants; }
        public String type() { return "SHARED"; }
        public double bill() { return units * 6.0 / occupants; }
    }
    private static final class AcRoom implements Room {
        private final int units;
        AcRoom(int units) { this.units = units; }
        public String type() { return "AC"; }
        public double bill() { return units * 10.0 + 200.0; }
    }
    private static Room createRoom(String[] parts) {
        int units = Integer.parseInt(parts[1]);
        switch (parts[0].toUpperCase(Locale.ROOT)) {
            case "SINGLE": return new SingleRoom(units);
            case "SHARED": return new SharedRoom(units, Integer.parseInt(parts[2]));
            case "AC": return new AcRoom(units);
            default: throw new IllegalArgumentException("Unknown room type: " + parts[0]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++)
            rooms.add(createRoom(scanner.nextLine().trim().split(" +")));
        double total = 0;
        for (Room room : rooms) {
            double amount = room.bill();
            System.out.printf(Locale.US, "%s: %.2f%n", room.type(), amount);
            total += amount;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
