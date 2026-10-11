public final class NameTag {
    private final String firstName;
    private final char lastInitial;

    public NameTag(String fullName) {
        if (fullName == null) {
            throw new IllegalArgumentException("Full name cannot be null.");
        }
        String[] parts = fullName.split(" ", -1);
        if (parts.length != 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new IllegalArgumentException("Enter exactly a first and last name separated by one space.");
        }
        firstName = parts[0];
        lastInitial = parts[1].charAt(0);
    }

    public String getNickname() {
        return firstName + " " + lastInitial + ".";
    }

    public static void main(String[] args) {
        NameTag tag = new NameTag("Maria Gomez");
        System.out.println("Nickname: " + tag.getNickname());
    }
}
