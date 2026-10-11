import java.util.Locale;
import java.util.Scanner;

public class ParcelShippingDesk {
    private interface Insurable {
        double INSURANCE_RATE = 0.02;

        double declaredValue();

        default double insuranceAmount() {
            return declaredValue() * INSURANCE_RATE;
        }
    }

    private abstract static class Parcel {
        protected final double weightKg;

        Parcel(double weightKg) {
            this.weightKg = weightKg;
        }

        abstract double shippingCharge();

        double insuranceAmount() { return 0.0; }

        final double total() { return shippingCharge() + insuranceAmount(); }
    }

    private static final class StandardParcel extends Parcel {
        StandardParcel(double weightKg) { super(weightKg); }
        @Override double shippingCharge() { return 40.0 + 10.0 * weightKg; }
    }

    private static final class ExpressParcel extends Parcel implements Insurable {
        private final double value;

        ExpressParcel(double weightKg, double value) {
            super(weightKg);
            this.value = value;
        }

        @Override double shippingCharge() { return 80.0 + 15.0 * weightKg; }
        @Override public double declaredValue() { return value; }
        @Override double insuranceAmount() { return Insurable.super.insuranceAmount(); }
    }

    private static final class FragileParcel extends Parcel implements Insurable {
        private final double value;

        FragileParcel(double weightKg, double value) {
            super(weightKg);
            this.value = value;
        }

        @Override double shippingCharge() { return 40.0 + 10.0 * weightKg + 50.0; }
        @Override public double declaredValue() { return value; }
        @Override double insuranceAmount() { return Insurable.super.insuranceAmount(); }
    }

    private static Parcel createParcel(String type, double weight, double value) {
        switch (type) {
            case "STANDARD": return new StandardParcel(weight);
            case "EXPRESS": return new ExpressParcel(weight, value);
            case "FRAGILE": return new FragileParcel(weight, value);
            default: throw new IllegalArgumentException("Unknown parcel type: " + type);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
            int parcels = scanner.nextInt();
            double grandTotal = 0.0;
            for (int i = 0; i < parcels; i++) {
                String type = scanner.next().toUpperCase(Locale.ROOT);
                double weight = scanner.nextDouble();
                double declaredValue = scanner.nextDouble();
                Parcel parcel = createParcel(type, weight, declaredValue);
                double charge = parcel.shippingCharge();
                double insurance = parcel.insuranceAmount();
                double total = parcel.total();
                System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                        type, charge, insurance, total);
                grandTotal += total;
            }
            System.out.printf("Grand Total: %.2f%n", grandTotal);
        }
    }
}
