package ru.academit.khrushchev.shape.shapes;

public class Rectangle implements Shape {
    private double width;
    private double length;

    public Rectangle(double lengthSide1, double lengthSide2) {
        this.width = Math.min(lengthSide1, lengthSide2);
        this.length = Math.max(lengthSide1, lengthSide2);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return length;
    }

    @Override
    public double getArea() {
        return width * length;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + length);
    }

    @Override
    public String toString() {
        return String.format("The shape is rectangle.%nLength = %.2f%nWidth = %.2f%nArea = %.2f%nPerimeter = %.2f%n",
                length, width, getArea(), getPerimeter());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Rectangle r = (Rectangle) obj;

        return r.width == width && r.length == length;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = hash * prime + Double.hashCode(length);
        hash = hash * prime + Double.hashCode(width);

        return hash;
    }
}
