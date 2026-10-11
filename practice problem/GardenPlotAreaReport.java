import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class GardenPlotAreaReport {
    private abstract static class Plot {
        private final String owner;
        Plot(String owner) { this.owner = owner; }
        String owner() { return owner; }
        abstract String shape();
        abstract double area();
    }
    private static final class Circle extends Plot {
        private final double radius;
        Circle(String owner, double radius) { super(owner); this.radius = radius; }
        String shape() { return "CIRCLE"; }
        double area() { return Math.PI * radius * radius; }
    }
    private static final class Rectangle extends Plot {
        private final double length, width;
        Rectangle(String owner, double length, double width) {
            super(owner); this.length = length; this.width = width;
        }
        String shape() { return "RECTANGLE"; }
        double area() { return length * width; }
    }
    private static final class Triangle extends Plot {
        private final double base, height;
        Triangle(String owner, double base, double height) {
            super(owner); this.base = base; this.height = height;
        }
        String shape() { return "TRIANGLE"; }
        double area() { return 0.5 * base * height; }
    }
    private static Plot createPlot(String[] p) {
        switch (p[0].toUpperCase(Locale.ROOT)) {
            case "CIRCLE": return new Circle(p[1], Double.parseDouble(p[2]));
            case "RECTANGLE": return new Rectangle(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]));
            case "TRIANGLE": return new Triangle(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]));
            default: throw new IllegalArgumentException("Unknown plot shape: " + p[0]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Plot> plots = new ArrayList<>();
        for (int i = 0; i < n; i++) plots.add(createPlot(scanner.nextLine().trim().split(" +")));
        double total = 0;
        for (Plot plot : plots) {
            double area = plot.area();
            System.out.printf(Locale.US, "%s (%s): %.2f%n", plot.owner(), plot.shape(), area);
            total += area;
        }
        System.out.printf(Locale.US, "Total Area: %.2f%n", total);
    }
}
