package ru.academit.khrushchev.matrix;

import ru.academit.khrushchev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsAmount, int columnsAmount) {
        if (rowsAmount <= 0 || columnsAmount <= 0) {
            throw new IllegalArgumentException("Matrix sizes must be more than 0. Now rowsAmount is " + rowsAmount +
                    ", columnsAmount is " + columnsAmount + ".");
        }

        rows = new Vector[rowsAmount];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsAmount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] matrix) {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Matrix must have more than 0 rows. Now it is " + matrix.length);
        }

        int maxRowLength = 0;

        for (double[] row : matrix) {
            maxRowLength = Math.max(maxRowLength, row.length);
        }

        if (maxRowLength == 0) {
            throw new IllegalArgumentException("All matrix rows are empty");
        }

        int rowsAmount = matrix.length;

        rows = new Vector[rowsAmount];

        for (int i = 0; i < rowsAmount; i++) {
            rows[i] = new Vector(maxRowLength, matrix[i]);
        }
    }

    public Matrix(Vector[] rows) {
        if (rows.length == 0) {
            throw new IllegalArgumentException("Rows amount must be more than 0. Now it is " + rows.length);
        }

        int maxRowLength = 0;

        for (Vector row : rows) {
            maxRowLength = Math.max(maxRowLength, row.getSize());
        }

        int rowsAmount = rows.length;
        this.rows = new Vector[rowsAmount];

        for (int i = 0; i < rowsAmount; i++) {
            double[] components = new double[maxRowLength];

            for (int j = 0; j < rows[i].getSize(); j++) {
                components[j] = rows[i].getComponent(j);
            }

            this.rows[i] = new Vector(maxRowLength, components);
        }
    }

    public int getRowsAmount() {
        return rows.length;
    }

    public int getColumnsAmount() {
        return rows[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows.length) {
            throw new IndexOutOfBoundsException("Argument rowIndex must be >= 0 and less than rows amount (" +
                    getRowsAmount() + ") . Now index is " + rowIndex);
        }

        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector row) {
        if (rowIndex < 0 || rowIndex >= rows.length) {
            throw new IndexOutOfBoundsException("Argument rowIndex must be >= 0 and less than rows amount (" +
                    getRowsAmount() + ") . Now index is " + rowIndex);
        }

        rows[rowIndex] = new Vector(row);
    }

    public Vector getColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= getColumnsAmount()) {
            throw new IndexOutOfBoundsException("Argument columnIndex must be >= 0 and less than columns amount. " +
                    "Now its " + columnIndex + " when columnsAmount is " + getColumnsAmount());
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(columnIndex));
        }

        return column;
    }

    public void transpose() {
        Vector[] newRows = new Vector[getColumnsAmount()];

        for (int i = 0; i < getColumnsAmount(); i++) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsAmount()) {
            throw new UnsupportedOperationException("Matrix must be square. Now sizes are " + rows.length +
                    " and " + getColumnsAmount());
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }

        if (rows.length == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1) -
                    rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        double determinant = 0;

        final int i = 0;

        for (int j = 0; j < getColumnsAmount(); j++) {
            Matrix minor = getMinor(j);

            determinant += minor.getDeterminant() * Math.pow(-1, i + j + 2) * rows[i].getComponent(j);
        }


        return determinant;
    }

    private Matrix getMinor(int column) {
        if (column < 0 || column >= getColumnsAmount()) {
            throw new IllegalArgumentException("Argument column must be >= 0 and less than row length. Now it is " + column);
        }

        final int row = 0;
        final int rowsAmount = getRowsAmount();
        final int columnsAmount = getColumnsAmount();

        Vector[] resultRows = new Vector[rowsAmount - 1];
        Vector resultRow = new Vector(columnsAmount - 1);

        for (int i = 0, k = 0; i < rowsAmount; i++, k++) {
            if (i == row) {
                if (i == rowsAmount - 1) {
                    break;
                }

                k--;
                continue;
            }

            for (int j = 0, m = 0; j < columnsAmount; j++, m++) {
                if (j == column) {
                    if (j == columnsAmount - 1) {
                        break;
                    }

                    m--;
                    continue;
                }

                resultRow.setComponent(m, rows[i].getComponent(j));
            }

            resultRows[k] = new Vector(resultRow);
        }

        return new Matrix(resultRows);
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsAmount()) {
            throw new IllegalArgumentException("Vector size must equals to matrix columns amount. " +
                    "Now vector size is " + vector.getSize() + " matrix columns amount is " + getColumnsAmount());
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            double resultNumber = Vector.getScalarProduct(rows[i], vector);

            resultVector.setComponent(i, resultNumber);
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsAmount() != matrix.getColumnsAmount()) {
            throw new IllegalArgumentException("Matrices sizes must be equal. Now employed matrix rows amount is " + rows.length +
                    ", columns amount is " + getColumnsAmount() + ". And the argument matrix rows amount is " + matrix.rows.length +
                    ", columns amount is " + matrix.getColumnsAmount());
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (rows.length != matrix.rows.length || getColumnsAmount() != matrix.getColumnsAmount()) {
            throw new IllegalArgumentException("Matrices sizes must be equal. Now employed matrix rows amount is " + rows.length +
                    ", columns amount is " + getColumnsAmount() + ". And the argument matrix rows amount is " + matrix.rows.length +
                    ", columns amount is " + matrix.getColumnsAmount());
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsAmount() != matrix2.getColumnsAmount()) {
            throw new IllegalArgumentException("Matrices sizes must be equal. Now first matrix rows amount is " +
                    matrix1.rows.length + ", columns amount is " + matrix1.getColumnsAmount() +
                    ". And the second matrix rows amount is " + matrix2.rows.length + ", columns amount is "
                    + matrix2.getColumnsAmount());
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);
        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsAmount() != matrix2.getColumnsAmount()) {
            throw new IllegalArgumentException("Matrices sizes must be equal. Now first matrix rows amount is " +
                    matrix1.rows.length + ", columns amount is " + matrix1.getColumnsAmount() +
                    ". And the second matrix rows amount is " + matrix2.rows.length + ", columns amount is "
                    + matrix2.getColumnsAmount());
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);
        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsAmount() != matrix2.rows.length) {
            throw new IllegalArgumentException("First matrix columns amount must be equal to the second matrix rows amount. " +
                    "Now it is " + matrix1.getColumnsAmount() + " and " + matrix2.rows.length + " respectively.");
        }

        Matrix resultMatrix = new Matrix(matrix1.rows.length, matrix2.getColumnsAmount());
        double resultNumber;

        for (int i = 0; i < matrix1.rows.length; i++) {
            Vector currentRow = matrix1.rows[i];

            for (int j = 0; j < matrix2.getColumnsAmount(); j++) {
                Vector currentColumn = matrix2.getColumn(j);
                resultNumber = 0;

                for (int k = 0; k < currentColumn.getSize(); k++) {
                    resultNumber += currentRow.getComponent(k) * currentColumn.getComponent(k);
                }

                resultMatrix.rows[i].setComponent(j, resultNumber);
            }
        }

        return resultMatrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Vector row : rows) {
            stringBuilder.append(row).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Matrix receivedMatrix = (Matrix) obj;

        return Arrays.equals(receivedMatrix.rows, rows);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = hash * prime + Arrays.hashCode(rows);

        return hash;
    }
}
