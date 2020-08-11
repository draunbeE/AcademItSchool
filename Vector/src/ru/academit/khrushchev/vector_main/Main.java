package ru.academit.khrushchev.vector_main;

import ru.academit.khrushchev.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);
        Vector vector2 = new Vector(vector1);
        Vector vector3 = new Vector(new double[]{3.1, 6.2, 7.1});
        Vector vector4 = new Vector(new double[]{2.1, 5.2, 6.1, 8.2, 3.8});

        System.out.println(vector3);
        System.out.println(vector4);
//        System.out.println(vector2);
//
//        vector3.add(vector4);
//        System.out.println(vector3);
//        vector4.add(vector3);
//        System.out.println(vector4);

//        System.out.println(Vector.getSum(vector3, vector4));
//        System.out.println(Vector.getSum(vector4, vector3));
//
//        vector3.subtract(vector4);
//        System.out.println(vector3);
//        vector4.subtract(vector3);
//        System.out.println(vector4);

//        System.out.println(Vector.getSubtraction(vector3, vector4));
//        System.out.println(Vector.getSubtraction(vector4, vector3));

//        System.out.println(vector4.reverse());
//        System.out.println(vector4.reverse());
//
//        System.out.println(vector3.getLength());
//
//        System.out.println(vector3.equals(vector4));
//        System.out.println(vector4.equals(vector3));
//
//        System.out.println(vector3.getComponent(2));
//        vector3.setComponent(5.2, 2);
//        System.out.println(vector3.getComponent(2));
//
//        System.out.println(Vector.getScalarProduct(vector3, vector4));
//        System.out.println(Vector.getScalarProduct(vector4, vector3));

//        System.out.println(vector3.multiplyByScalar(2));
    }
}