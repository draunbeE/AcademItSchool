package ru.academit.khrushchev.matrix.main;

import ru.academit.khrushchev.matrix.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(3,3);
        System.out.println(matrix);

        Matrix matrix2 = new Matrix(matrix);
        System.out.println(matrix2);

        double[][] arrayMatrix = new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix matrix3 = new Matrix(arrayMatrix);
        System.out.println(matrix3);
    }
}
