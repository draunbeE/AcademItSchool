package ru.academit.khrushchev.shape.shapes;

public class Triangle implements Shape {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(x3, Math.max(x1, x2)) - Math.min(x3, Math.min(x1, x2));
    }

    @Override
    public double getHeight() {
        return Math.max(y3, Math.max(y1, y2)) - Math.min(y3, Math.min(y1, y2));
    }

    @Override
    public double getArea() {
        double side1 = getSide(x1, y1, x2, y2);
        double side2 = getSide(x1, y1, x3, y3);
        double side3 = getSide(x2, y2, x3, y3);

        double perimeter = side1 + side2 + side3;

        double semiPerimeter = perimeter / 2;

        return Math.sqrt(semiPerimeter * (semiPerimeter - side1) * (semiPerimeter - side2) * (semiPerimeter - side3));
    }

    @Override
    public double getPerimeter() {
        double side1 = getSide(x1, y1, x2, y2);
        double side2 = getSide(x1, y1, x3, y3);
        double side3 = getSide(x2, y2, x3, y3);

        return side1 + side2 + side3;
    }

    private static double getSide(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public String toString() {
        return String.format("The shape is triangle.%nX1 = %.2f%nY1 = %.2f%nX2 = %.2f%nY2 = %.2f%nX3 = %.2f%nY3 = %.2f%n" +
                "Area = " +
                "%.2f%nPerimeter = %.2f%n", x1, y1, x2, y2, x3, y3, getArea(), getPerimeter());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Triangle t = (Triangle) obj;

        return x1 == t.x1 && x2 == t.x2 && x3 == t.x3 && y1 == t.y1 && y2 == t.y2 && y3 == t.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = hash * prime + Double.hashCode(x1);
        hash = hash * prime + Double.hashCode(x2);
        hash = hash * prime + Double.hashCode(x3);
        hash = hash * prime + Double.hashCode(y1);
        hash = hash * prime + Double.hashCode(y2);
        hash = hash * prime + Double.hashCode(y3);

        return hash;
    }
}
