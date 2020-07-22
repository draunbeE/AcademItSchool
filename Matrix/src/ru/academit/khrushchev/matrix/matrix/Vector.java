package ru.academit.khrushchev.matrix.matrix;

import java.util.Arrays;

public class Vector {
    final private int n;
    final private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        this.n = n;
        components = new double[n];
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        this.n = n;
        this.components = Arrays.copyOf(components, n);
    }

    public Vector(Vector vector) {
        this.n = vector.n;
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        this.n = components.length;
        this.components = components;
    }

    public int getSize() {
        return n;
    }

    public double getComponent(int componentIndex) {
        return components[componentIndex];
    }

    public void setComponent(double component, int componentIndex) {
        components[componentIndex] = component;
    }

    public double[] getComponents() {
        return components;
    }

    public Vector addVector(Vector vector) {
        Vector resultVector;
        double[] addedComponents;

        if (vector.getSize() > getSize()) {
            resultVector = new Vector(vector);
            addedComponents = Arrays.copyOf(components, components.length);
        } else {
            resultVector = new Vector(this);
            addedComponents = Arrays.copyOf(vector.components, vector.components.length);
        }

        double[] resultComponents = resultVector.components;

        for (int i = 0; i < addedComponents.length; i++) {
            resultComponents[i] += addedComponents[i];
        }

        return resultVector;
    }

    public Vector subtractVector(Vector vector) {
        Vector resultVector;
        double[] subtractedComponents;

        if (vector.getSize() > getSize()) {
            resultVector = new Vector(vector);
            subtractedComponents = Arrays.copyOf(components, components.length);

            for (int i = components.length; i < resultVector.components.length; i++) {
                resultVector.components[i] *= -1;
            }
        } else {
            resultVector = new Vector(this);
            subtractedComponents = Arrays.copyOf(vector.components, vector.components.length);
        }

        double[] resultComponents = resultVector.components;

        for (int i = 0; i < subtractedComponents.length; i++) {
            resultComponents[i] -= subtractedComponents[i];
        }

        return resultVector;
    }

    public Vector multiplyOnScalar(double scalar) {
        Vector resultVector = new Vector(this);
        double[] resultVectorComponents = resultVector.components;

        for (int i = 0; i < resultVectorComponents.length; i++) {
            resultVectorComponents[i] *= scalar;
        }

        return resultVector;
    }

    public Vector reverseVector() {
        Vector resultVector = new Vector(this);
        double[] resultVectorComponents = resultVector.components;

        for (int i = 0; i < resultVectorComponents.length; i++) {
            resultVectorComponents[i] *= -1;
        }

        return resultVector;
    }

    public double getLength() {
        double scalarComposition = 0;

        for (double component : components) {
            scalarComposition += Math.pow(component, 2);
        }

        return Math.sqrt(scalarComposition);
    }

    public static Vector addVectors(Vector vector1, Vector vector2) {
        Vector resultVector;
        double[] addedComponents;

        if (vector1.getSize() > vector2.getSize()) {
            resultVector = new Vector(vector1);
            addedComponents = Arrays.copyOf(vector2.components, vector2.components.length);
        } else {
            resultVector = new Vector(vector2);
            addedComponents = Arrays.copyOf(vector1.components, vector1.components.length);
        }

        double[] resultComponents = resultVector.components;

        for (int i = 0; i < addedComponents.length; i++) {
            resultComponents[i] += addedComponents[i];
        }

        return resultVector;
    }

    public static Vector subtractVectors(Vector vector1, Vector vector2) {
        Vector resultVector;
        double[] subtractedComponents;

        if (vector2.getLength() > vector1.getLength()) {
            resultVector = new Vector(vector2);
            subtractedComponents = Arrays.copyOf(vector1.components, vector1.getSize());

            for (int i = vector1.getSize(); i < vector2.getSize(); i++) {
                resultVector.components[i] *= -1;
            }
        } else {
            resultVector = new Vector(vector1);
            subtractedComponents = Arrays.copyOf(vector2.components, vector2.getSize());
        }

        double[] resultComponents = resultVector.components;

        for (int i = 0; i < subtractedComponents.length; i++) {
            resultComponents[i] -= subtractedComponents[i];
        }

        return resultVector;
    }

    public static double getVectorsScalarMultiplication(Vector vector1, Vector vector2) {
        double result = 0;

        for (int i = 0; i < Math.min(vector1.getSize(), vector2.getSize()); i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (int i = 0; i < n; i++) {
            stringBuilder.append(components[i]).append(", ");
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "}");

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = hash * prime + n;
        hash = hash * prime + Arrays.hashCode(components);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Vector v = (Vector) obj;

        return v.n == n && Arrays.equals(components, v.components);
    }
}
