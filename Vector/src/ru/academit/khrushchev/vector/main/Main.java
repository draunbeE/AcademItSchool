package ru.academit.khrushchev.vector.main;

import ru.academit.khrushchev.vector.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);
        Vector vector2 = new Vector(vector1);
        Vector vector3 = new Vector(new double[]{0});
        Vector vector4 = new Vector(new double[]{2.1, 5.2, 6.1, 8.2});


        System.out.println(vector3);
        System.out.println(vector4);
        System.out.println(vector3.addVector(vector4));
        System.out.println(vector4.addVector(vector3));
        System.out.println(vector3.subtractVector(vector4));
        System.out.println(vector4.subtractVector(vector3));
        System.out.println(vector4.reverseVector());
        System.out.println(vector4.reverseVector());
        System.out.println(vector3.getLength());

    }
}
