import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class LibraryLateFineCounter {
    private abstract static class LibraryItem {
        private final String title;
        private final int daysLate;
        LibraryItem(String title, int daysLate) { this.title = title; this.daysLate = daysLate; }
        String title() { return title; }
        int daysLate() { return daysLate; }
        abstract double fine();
    }
    private static final class Book extends LibraryItem {
        Book(String title, int daysLate) { super(title, daysLate); }
        double fine() { return daysLate() * 2.0; }
    }
    private static final class DVD extends LibraryItem {
        DVD(String title, int daysLate) { super(title, daysLate); }
        double fine() { return Math.min(daysLate() * 5.0, 50.0); }
    }
    private static final class Magazine extends LibraryItem {
        Magazine(String title, int daysLate) { super(title, daysLate); }
        double fine() { return daysLate(); }
    }
    private static LibraryItem createItem(String[] p) {
        String title = p[1];
        int daysLate = Integer.parseInt(p[2]);
        switch (p[0].toUpperCase(Locale.ROOT)) {
            case "BOOK": return new Book(title, daysLate);
            case "DVD": return new DVD(title, daysLate);
            case "MAGAZINE": return new Magazine(title, daysLate);
            default: throw new IllegalArgumentException("Unknown library item type: " + p[0]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<LibraryItem> items = new ArrayList<>();
        for (int i = 0; i < n; i++) items.add(createItem(scanner.nextLine().trim().split(" +")));
        double total = 0;
        for (LibraryItem item : items) {
            double fine = item.fine();
            System.out.printf(Locale.US, "%s: %.2f%n", item.title(), fine);
            total += fine;
        }
        System.out.printf(Locale.US, "Total Fines: %.2f%n", total);
    }
}
