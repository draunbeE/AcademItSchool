package ru.academit.khrushchev.shape.shapes;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return 4 * sideLength;
    }

    @Override
    public String toString() {
        return String.format("The shape is square.%nSide = %.2f%nArea = %.2f%nPerimeter = %.2f%n",
                sideLength, getArea(), getPerimeter());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Square s = (Square) obj;

        return s.sideLength == sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        return hash * prime + Double.hashCode(sideLength);
    }
}
