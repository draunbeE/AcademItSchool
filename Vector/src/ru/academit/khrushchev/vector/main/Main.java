package ru.academit.khrushchev.vector.main;

import ru.academit.khrushchev.vector.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);
        Vector vector2 = new Vector(vector1);
        Vector vector3 = new Vector(new double[]{2.1, 5.2, 6.1, 8.2});
        Vector vector4 = new Vector(new double[]{2.1, 5.2, 6.1, 8.2});

        System.out.println(vector3);
        System.out.println(vector4);
        System.out.println(vector2);

        System.out.println(vector3.addVector(vector4));
        System.out.println(vector4.addVector(vector3));
        System.out.println(Vector.addVectors(vector3, vector4));
        System.out.println(Vector.addVectors(vector4, vector3));

        System.out.println(vector3.subtractVector(vector4));
        System.out.println(vector4.subtractVector(vector3));
        System.out.println(Vector.subtractVectors(vector3, vector4));
        System.out.println(Vector.subtractVectors(vector4, vector3));

        System.out.println(vector4.reverseVector());
        System.out.println(vector4.reverseVector());

        System.out.println(vector3.getLength());

        System.out.println(vector3.equals(vector4));
        System.out.println(vector4.equals(vector3));

        System.out.println(vector3.getComponent(2));
        vector3.setComponent(5.2, 2);
        System.out.println(vector3.getComponent(2));

        System.out.println(Vector.getVectorsScalarMultiplication(vector3, vector4));
        System.out.println(Vector.getVectorsScalarMultiplication(vector4, vector3));

        System.out.println(vector3.multiplyOnScalar(2));
    }
}
