package ru.academit.khrushchev.vector.vector;

import java.util.Arrays;

public class Vector {
    private int n;
    private double[] components;

    public Vector(int n) {
        this.n = n;
        components = new double[n];
    }

    public Vector(Vector vector) {
        this.n = vector.n;
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        this.n = components.length;
        this.components = components;
    }

    public Vector(int n, double[] components) {
        this.n = n;
        this.components = Arrays.copyOf(components, n);
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


    //todo check whether it rather to use copy-constructor in addition and subtraction methods;
    public Vector addVector(Vector vector) {
        double[] addedComponents = vector.components;
        double[] resultComponents;

        if (addedComponents.length > components.length) {
            resultComponents = Arrays.copyOf(addedComponents, addedComponents.length);
            addedComponents = components;
        } else {
            resultComponents = Arrays.copyOf(components, components.length);
        }

        for (int i = 0; i < Math.min(addedComponents.length, components.length); i++) {
            resultComponents[i] += addedComponents[i];
        }

        return new Vector(resultComponents);
    }

    public Vector subtractVector(Vector vector) {
        double[] addedComponents = vector.components;
        double[] resultComponents;

        if (addedComponents.length > components.length) {
            resultComponents = Arrays.copyOf(addedComponents, addedComponents.length);

            for (int i = components.length; i < resultComponents.length; i++) {
                resultComponents[i] *= -1;
            }

            addedComponents = Arrays.copyOf(components, components.length);
        } else {
            resultComponents = Arrays.copyOf(components, components.length);
        }

        for (int i = 0; i < Math.min(addedComponents.length, components.length); i++) {
            resultComponents[i] -= addedComponents[i];
        }

        return new Vector(resultComponents);
    }

    public Vector multiplyOnScalar(double scalar) {
        Vector resultVector = new Vector(this);
        double[] resultVectorComponents = resultVector.components;

        for(int i = 0; i < resultVectorComponents.length; i++) {
            resultVectorComponents[i] *= scalar;
        }

        return resultVector;
    }

    public Vector reverseVector() {
        Vector resultVector = new Vector(this);
        double[] resultVectorComponents = resultVector.components;

        for(int i = 0; i < resultVectorComponents.length; i++) {
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
}
