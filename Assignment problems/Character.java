public class Character {
    private int health;
    private final int maximumHealth;

    public Character(int maximumHealth) {
        if (maximumHealth <= 0) {
            throw new IllegalArgumentException("Maximum health must be positive.");
        }
        this.maximumHealth = maximumHealth;
        this.health = maximumHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Damage cannot be negative.");
        }
        health = amount >= health ? 0 : health - amount;
    }

    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Healing cannot be negative.");
        }
        health = amount >= maximumHealth - health ? maximumHealth : health + amount;
    }

    public static void main(String[] args) {
        Character character = new Character(100);
        character.takeDamage(30);
        System.out.println("After 30 damage: " + character.getHealth());
        character.heal(50);
        System.out.println("After healing 50: " + character.getHealth());
        character.takeDamage(150);
        System.out.println("After 150 damage: " + character.getHealth());
    }
}
