import java.util.Locale;
import java.util.Scanner;

public class HomeApplianceEnergyReport {
    private interface SaverMode {
        default double applySaver(double units) { return units * 0.75; }
    }

    private abstract static class Appliance {
        private static final double COST_PER_UNIT = 8.0;

        abstract double powerWatts();

        final double unitsFor(double hours) {
            return powerWatts() * hours / 1000.0;
        }

        final double costFor(double units) { return units * COST_PER_UNIT; }
    }

    private static final class Fridge extends Appliance {
        @Override double powerWatts() { return 150.0; }
    }

    private static final class AirConditioner extends Appliance implements SaverMode {
        @Override double powerWatts() { return 1500.0; }
    }

    private static final class Television extends Appliance {
        @Override double powerWatts() { return 100.0; }
    }

    private static final class Washer extends Appliance implements SaverMode {
        @Override double powerWatts() { return 500.0; }
    }

    private static Appliance createAppliance(String type) {
        switch (type) {
            case "FRIDGE": return new Fridge();
            case "AC": return new AirConditioner();
            case "TV": return new Television();
            case "WASHER": return new Washer();
            default: throw new IllegalArgumentException("Unknown appliance: " + type);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
            int entries = scanner.nextInt();
            double totalCost = 0.0;
            for (int i = 0; i < entries; i++) {
                String type = scanner.next().toUpperCase(Locale.ROOT);
                double hours = scanner.nextDouble();
                boolean saverRequested = scanner.hasNext("(?i)SAVER");
                if (saverRequested) scanner.next();

                Appliance appliance = createAppliance(type);
                if (saverRequested && !(appliance instanceof SaverMode)) {
                    System.out.printf("%s: saver mode not supported%n", type);
                    continue;
                }

                double units = appliance.unitsFor(hours);
                if (saverRequested) units = ((SaverMode) appliance).applySaver(units);
                double cost = appliance.costFor(units);
                System.out.printf("%s: Units=%.2f Cost=%.2f%n", type, units, cost);
                totalCost += cost;
            }
            System.out.printf("Total Cost: %.2f%n", totalCost);
        }
    }
}
