public class Locker {
    private final int lockerNumber;
    private String combinationCode;

    public Locker(int lockerNumber, String initialCode) {
        if (initialCode == null || initialCode.isBlank()) {
            throw new IllegalArgumentException("Combination code cannot be blank.");
        }
        this.lockerNumber = lockerNumber;
        this.combinationCode = initialCode;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }

    // The current code is intentionally never returned or exposed.
    public boolean changeCode(String currentCode, String newCode) {
        if (currentCode == null || newCode == null || newCode.isBlank()) {
            return false;
        }
        if (!combinationCode.equals(currentCode)) {
            return false;
        }
        combinationCode = newCode;
        return true;
    }

    public static void main(String[] args) {
        Locker locker = new Locker(101, "1234");
        System.out.println("Correct code changed: " + locker.changeCode("1234", "5678"));
        System.out.println("Wrong code changed: " + locker.changeCode("0000", "9999"));
        System.out.println("Locker number: " + locker.getLockerNumber());
        System.out.println("The combination code is never displayed.");
    }
}
