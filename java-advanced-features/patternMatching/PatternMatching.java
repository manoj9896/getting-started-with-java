package patternMatching;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Pattern Matching - instanceof (type pattern) - switch statements - prevents
 * from type cast
 */

interface Shape {
    public double getArea();

    public double getPeremeter();
}

class Square implements Shape {

    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPeremeter() {
        return 4 * side;
    }

    public double getSide() {
        return side;
    }

}

class Rectangle implements Shape {

    private double length;
    private double breadth;

    public Rectangle(double length, double breadth) {
        this.breadth = breadth;
        this.length = length;
    }

    @Override
    public double getArea() {
        return length * breadth;
    }

    @Override
    public double getPeremeter() {
        return 2 * (length + breadth);
    }

    public double getBreadth() {
        return breadth;
    }

    public double getLength() {
        return length;
    }

}

public class PatternMatching {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        List<Integer> vector = new Vector<>();

        if (vector instanceof ArrayList<Integer> a) {
            System.out.println(a.size());
        }

        if (vector instanceof List<Integer> l) {
            System.out.println(l.size());
        }

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Square(5D));
        shapes.add(new Square(20D));
        shapes.add(new Rectangle(4, 3));
        shapes.add(new Rectangle(8.5, 9.0));
        shapes.add(new Square(34D));

        for (Shape shape : shapes) {
            switch (shape) {
                case Square s -> System.out.println(s.getSide());
                case Rectangle r -> System.out.println(r.getLength() + " " + r.getBreadth());
                default -> System.out.println(shape.getClass());
            }

            String ans = switch (shape) {
                case Square s -> String.format("Side of the square is : %s", s.getSide());
                case Rectangle r ->
                    String.format("Length of Rectangle : %s, Breadth of Rectangle : %s", r.getLength(), r.getBreadth());
                default -> "";
            };

            System.out.println(ans);
        }
    }

}
