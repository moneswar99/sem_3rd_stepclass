public class PiggyBank {
    private double savings;
    private final String id;

    public PiggyBank(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be blank.");
        }
        this.id = id;
        this.savings = 0.0;
    }

    public String getId() {
        return id;
    }

    public double getSavings() {
        return savings;
    }

    public void deposit(double amount) {
        if (amount <= 0 || !Double.isFinite(amount)) {
            throw new IllegalArgumentException("Deposit must be a positive amount.");
        }
        savings += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || !Double.isFinite(amount)) {
            throw new IllegalArgumentException("Withdrawal must be a positive amount.");
        }
        if (amount > savings) {
            return false;
        }
        savings -= amount;
        return true;
    }

    public static void main(String[] args) {
        PiggyBank pb = new PiggyBank("PB-1");
        pb.deposit(100);
        System.out.println("After deposit: " + pb.getSavings());
        System.out.println("Withdrawal of 30 accepted: " + pb.withdraw(30));
        System.out.println("Savings: " + pb.getSavings());
        System.out.println("Withdrawal of 500 accepted: " + pb.withdraw(500));
        System.out.println("Savings: " + pb.getSavings());
    }
}
