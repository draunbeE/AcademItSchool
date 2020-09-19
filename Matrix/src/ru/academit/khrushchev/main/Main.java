package ru.academit.khrushchev.main;

import ru.academit.khrushchev.matrix.Matrix;
import ru.academit.khrushchev.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 3);

        Matrix matrix2 = new Matrix(new double[][]{{}, {4, 5, 6}, {7, 8, 9, 10}});
        Matrix matrix3 = new Matrix(matrix2);
        Vector vector1 = new Vector(new double[]{2, 2, 2, 2});

        Matrix matrix4 = new Matrix(new double[][]{{4, 2}, {3, 1}, {1, 5}});
        Matrix matrix5 = new Matrix(new double[][]{{1, 2, 2}, {3, 1, 1}});

        Vector[] rows = new Vector[]{new Vector(new double[]{1, 2, 3}), new Vector(new double[]{2, 3, 4}),
                new Vector(new double[]{3, 4, 5})};

        Matrix matrix6 = new Matrix(rows);

        Matrix matrix7 = new Matrix(new double[][]{{1, 2, 3}, {2, 3, 4}, {3, 4, 5}});
        Matrix matrix8 = new Matrix(new double[][]{{4, 5, 6}, {5, 6, 7}, {6, 7, 8}});

//        System.out.println(matrix6);
//        System.out.println(matrix6.getRowsAmount());
//        System.out.println(matrix6.getColumnsAmount());
//
//        System.out.println(matrix1);
//        matrix1.setRow(vector1, 0);
//        System.out.println(matrix1);
//
//        System.out.println(matrix1.getRow(0));
//        System.out.println(matrix1.getColumn(0));
//
//        System.out.println(matrix2);
//        matrix2.transpose();
//        System.out.println(matrix2);
//
//        System.out.println(matrix3);
//        matrix3.multiplyByScalar(2);
//        System.out.println(matrix3);
//
//        System.out.println(matrix6);
//        System.out.println(matrix6.getDeterminant());
//
//        System.out.println(matrix2);
//        System.out.println(matrix2.multiplyByVector(vector1));
//
//        System.out.println(matrix7);
//        System.out.println(matrix8);
//        matrix7.add(matrix8);
//        System.out.println(matrix7);
//        matrix8.add(matrix7);
//        System.out.println(matrix8);
//
//        matrix7.subtract(matrix8);
//        System.out.println(matrix7);
//        matrix8.subtract(matrix7);
//        System.out.println(matrix8);
//
//        System.out.println(matrix7);
//        System.out.println(matrix8);
//
//        System.out.println(Matrix.getSum(matrix7, matrix8));
//        System.out.println(Matrix.getSum(matrix8, matrix7));
//        System.out.println(Matrix.getDifference(matrix7, matrix8));
//        System.out.println(Matrix.getDifference(matrix8, matrix7));
//
//        System.out.println(matrix4);
//        System.out.println(matrix5);
//        System.out.println(Matrix.getProduct(matrix5, matrix4));
//
//        System.out.println(matrix2.equals(matrix3));
    }
}