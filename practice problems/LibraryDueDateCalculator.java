import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
public class LibraryDueDateCalculator {
    private static final LocalDate TODAY = LocalDate.of(2023, 10, 26);
    private interface LibraryItem {
        String title();
        int loanDays();
        default LocalDate dueDate() { return TODAY.plusDays(loanDays()); }
    }
    private static final class Book implements LibraryItem {
        private final String title;
        Book(String title) { this.title = title; }
        public String title() { return title; }
        public int loanDays() { return 14; }
    }
    private static final class DVD implements LibraryItem {
        private final String title;
        DVD(String title) { this.title = title; }
        public String title() { return title; }
        public int loanDays() { return 7; }
    }
    private static final class Magazine implements LibraryItem {
        private final String title;
        Magazine(String title) { this.title = title; }
        public String title() { return title; }
        public int loanDays() { return 3; }
    }
    private static LibraryItem createItem(String type, String title) {
        switch (type.toUpperCase(Locale.ROOT)) {
          case "BOOK": return new Book(title);
          case "DVD": return new DVD(title);
          case "MAGAZINE": return new Magazine(title);
          default: throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }
    private static String titleFromLine(String line) {
        int firstSpace = line.indexOf(' ');
        String title = line.substring(firstSpace + 1).trim();
        return title.replace(String.valueOf((char) 34), "").trim();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<LibraryItem> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            int firstSpace = line.indexOf(' ');
            items.add(createItem(line.substring(0, firstSpace), titleFromLine(line)));
        }
        for (LibraryItem item : items)
            System.out.println(item.title() + ": " + item.dueDate());
    }
}
