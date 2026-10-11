public final class PasswordChecker {
    private final String password;

    public PasswordChecker(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null.");
        }
        this.password = password;
    }

    public String getStrength() {
        int length = password.length();
        if (length < 6) {
            return "Weak";
        }
        if (length <= 9) {
            return "Medium";
        }
        return "Strong";
    }

    public static void main(String[] args) {
        PasswordChecker shortPassword = new PasswordChecker("abcd");
        PasswordChecker mediumPassword = new PasswordChecker("abcdefgh");
        PasswordChecker longPassword = new PasswordChecker("abcdefghijkl");
        System.out.println("4-character password: " + shortPassword.getStrength());
        System.out.println("8-character password: " + mediumPassword.getStrength());
        System.out.println("12-character password: " + longPassword.getStrength());
    }
}
