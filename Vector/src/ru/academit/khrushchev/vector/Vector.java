package ru.academit.khrushchev.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0, now it is " + n);
        }

        components = new double[n];
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0, now it is " + n);
        }

        this.components = Arrays.copyOf(components, n);
    }

    public Vector(Vector vector) {
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Components length is 0, it must be greater");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public int getSize() {
        return components.length;
    }

    public double getComponent(int componentIndex) {
        return components[componentIndex];
    }

    public void setComponent(double component, int componentIndex) {
        components[componentIndex] = component;
    }

    public void addVector(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < Math.min(components.length, vector.components.length); i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtractVector(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < Math.min(components.length, vector.components.length); i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double component : components) {
            sum += Math.pow(component, 2);
        }

        return Math.sqrt(sum);
    }

    public static Vector getVectorsSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);

        resultVector.addVector(vector2);

        return resultVector;
    }

    public static Vector getVectorsSubtraction(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);

        resultVector.subtractVector(vector2);

        return resultVector;
    }

    public static double getVectorsScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;

        for (int i = 0; i < Math.min(vector1.components.length, vector2.components.length); i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (double component : components) {
            stringBuilder.append(component).append(", ");
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "}");

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = hash * prime + Arrays.hashCode(components);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Vector v = (Vector) obj;

        return v.components.length == components.length && Arrays.equals(components, v.components);
    }
}
