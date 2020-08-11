package ru.academit.khrushchev.shape.main;

import ru.academit.khrushchev.shape.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(1),
                new Square(3),
                new Rectangle(8, 2.2),
                new Triangle(2, 4.1, 5.2, 3.1, 8.4, 9.2),
                new Circle(2),
                new Triangle(2, 2, 4, 2.1, 5.2, 3),
                new Rectangle(3, 8.6)
        };

        Shape largestAreaShape = getLargestAreaShape(shapes);
        Shape secondLargestPerimeterShape = getSecondLargestPerimeterShape(shapes);

        System.out.printf("The largest area shape:%n" + largestAreaShape + "%n");
        System.out.printf("The second largest perimeter shape:%n" + secondLargestPerimeterShape + "%n");

        Shape rectangle = new Rectangle(5, 2);

        System.out.println("Rectangle width equals = " + rectangle.getWidth());
        System.out.println("Rectangle height equals = " + rectangle.getHeight());
    }

    public static Shape getLargestAreaShape(Shape[] shapes) {
        ShapeAreaComparator comparator = new ShapeAreaComparator();

        Shape[] shapesCopy = Arrays.copyOf(shapes, shapes.length);

        Arrays.sort(shapesCopy, comparator);

        return shapesCopy[shapesCopy.length - 1];
    }

    public static Shape getSecondLargestPerimeterShape(Shape[] shapes) {
        ShapePerimeterComparator comparator = new ShapePerimeterComparator();

        Shape[] shapesCopy = Arrays.copyOf(shapes, shapes.length);

        Arrays.sort(shapesCopy, comparator);

        return shapesCopy[shapesCopy.length - 2];
    }
}