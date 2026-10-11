import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class StreamingPlanRenewalReminder {
    private interface Plan { String name(); LocalDate renewalDate(); }
    private abstract static class SubscriptionPlan implements Plan {
        private final String subscriberName;
        private final LocalDate startDate;
        SubscriptionPlan(String subscriberName, LocalDate startDate) {
            this.subscriberName = subscriberName;
            this.startDate = startDate;
        }
        public String name() { return subscriberName; }
        protected abstract int validityDays();
        public LocalDate renewalDate() { return startDate.plusDays(validityDays()); }
    }
    private static final class BasicPlan extends SubscriptionPlan {
        BasicPlan(String name, LocalDate date) { super(name, date); }
        protected int validityDays() { return 30; }
    }
    private static final class StandardPlan extends SubscriptionPlan {
        StandardPlan(String name, LocalDate date) { super(name, date); }
        protected int validityDays() { return 90; }
    }
    private static final class PremiumPlan extends SubscriptionPlan {
        PremiumPlan(String name, LocalDate date) { super(name, date); }
        protected int validityDays() { return 365; }
    }
    private static Plan createPlan(String[] parts) {
        String subscriberName = parts[1];
        LocalDate startDate = LocalDate.parse(parts[2]);
        switch (parts[0].toUpperCase(Locale.ROOT)) {
            case "BASIC": return new BasicPlan(subscriberName, startDate);
            case "STANDARD": return new StandardPlan(subscriberName, startDate);
            case "PREMIUM": return new PremiumPlan(subscriberName, startDate);
            default: throw new IllegalArgumentException("Unknown plan type: " + parts[0]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Plan> subscribers = new ArrayList<>();
        for (int i = 0; i < n; i++)
            subscribers.add(createPlan(scanner.nextLine().trim().split(" +")));
        for (Plan plan : subscribers)
            System.out.println(plan.name() + ": " + plan.renewalDate());
    }
}
