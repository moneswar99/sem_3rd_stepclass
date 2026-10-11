public class TrafficLight {
    private enum Color {
        RED,
        GREEN,
        YELLOW
    }

    private final String id;
    private Color currentColor;

    public TrafficLight(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Traffic light ID cannot be blank.");
        }
        this.id = id;
        this.currentColor = Color.RED;
    }

    public String getId() {
        return id;
    }

    public String getColor() {
        return currentColor.name();
    }

    public void next() {
        switch (currentColor) {
            case RED:
                currentColor = Color.GREEN;
                break;
            case GREEN:
                currentColor = Color.YELLOW;
                break;
            case YELLOW:
                currentColor = Color.RED;
                break;
        }
    }

    public static void main(String[] args) {
        TrafficLight light = new TrafficLight("TL-9");
        System.out.println(light.getColor());
        light.next();
        System.out.println(light.getColor());
        light.next();
        System.out.println(light.getColor());
        light.next();
        System.out.println(light.getColor());
    }
}
