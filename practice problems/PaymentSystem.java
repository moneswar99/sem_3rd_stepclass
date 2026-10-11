import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
public class PaymentSystem {
    private interface PaymentMethod {
        String name();
        double finalAmount(double amount);
    }
    private static final class Card implements PaymentMethod {
        public String name() { return "CARD"; }
        public double finalAmount(double amount) { return amount * 1.02; }
    }
    private static final class Wallet implements PaymentMethod {
        public String name() { return "WALLET"; }
        public double finalAmount(double amount) { return amount * 1.01; }
    }
    private static final class BankTransfer implements PaymentMethod {
        public String name() { return "BANKTRANSFER"; }
        public double finalAmount(double amount) { return amount; }
    }
    private static final class Transaction {
        private final PaymentMethod method;
        private final double amount;
        Transaction(PaymentMethod method, double amount) { this.method = method; this.amount = amount; }
        double process() { return method.finalAmount(amount); }
    }
    private static PaymentMethod methodFor(String type) {
        switch (type.toUpperCase(Locale.ROOT)) {
          case "CARD": return new Card();
          case "WALLET": return new Wallet();
          case "BANKTRANSFER": return new BankTransfer();
          default: throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split("\s+");
            transactions.add(new Transaction(methodFor(parts[0]), Double.parseDouble(parts[1])));
        }
        double total = 0;
        for (Transaction transaction : transactions) {
            double adjusted = transaction.process();
            System.out.printf(Locale.US, "%s: %.2f%n", transaction.method.name(), adjusted);
            total += adjusted;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
