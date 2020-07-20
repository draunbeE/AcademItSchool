package ru.academit.khrushchev.shape.main;

import ru.academit.khrushchev.shape.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(1), new Square(3), new Rectangle(8, 2.2),
                new Triangle(2, 4.1, 5.2, 3.1, 8.4, 9.2), new Circle(2),
                new Triangle(2, 2, 4, 2.1, 5.2, 3), new Rectangle(3, 8.6)};

        Shape firstGreatestAreaShape = getMaxAreaShape(shapes, 1);
        Shape secondGreatestAreaShape = getMaxAreaShape(shapes, 2);

        System.out.println(firstGreatestAreaShape);
        System.out.println(secondGreatestAreaShape);

        Shape rectangle = new Rectangle(5, 2);

        System.out.println("Rectangle width equals = " + rectangle.getWidth());
        System.out.println("Rectangle height equals = " + rectangle.getHeight());
    }

    public static Shape getMaxAreaShape(Shape[] shapes, int areaValueOrder) {
        ShapeAreaComparator comparator = new ShapeAreaComparator();

        Shape[] gotShapesArray = Arrays.copyOf(shapes, shapes.length);

        Arrays.sort(gotShapesArray, comparator);

        return gotShapesArray[gotShapesArray.length - areaValueOrder];
    }
}
