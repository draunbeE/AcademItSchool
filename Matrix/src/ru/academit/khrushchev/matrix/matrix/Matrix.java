package ru.academit.khrushchev.matrix.matrix;

import ru.academit.khrushchev.vector.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private final int linesAmount;
    private final int lineLength;
    private final Vector[] lines;

    public Matrix(int linesAmount, int lineLength) {
        this.linesAmount = linesAmount;
        this.lineLength = lineLength;

        lines = new Vector[linesAmount];

        Arrays.fill(lines, new Vector(lineLength));
    }

    public Matrix(Matrix matrix) {
        linesAmount = matrix.linesAmount;
        lineLength = matrix.lineLength;

        lines = Arrays.copyOf(matrix.lines, linesAmount);
    }

    public Matrix(double[][] matrix) {
        int maxLineLength = 0;

        for (double[] line : matrix) {
            maxLineLength = Math.max(maxLineLength, line.length);
        }

        linesAmount = matrix.length;
        lineLength = maxLineLength;

        lines = new Vector[linesAmount];

        for (int i = 0; i < linesAmount; i++) {
            lines[i] = new Vector(maxLineLength, matrix[i]);
        }
    }

    public Matrix(Vector[] lines) {
        int maxLineLength = 0;

        for (Vector line : lines) {
            maxLineLength = Math.max(maxLineLength, line.getSize());
        }

        linesAmount = lines.length;
        lineLength = maxLineLength;
        this.lines = new Vector[linesAmount];
        double[] components = new double[lineLength];

        for (int i = 0; i < linesAmount; i++) {
            for (int j = 0; j < lines[i].getSize(); j++) {
                components[j] = lines[i].getComponent(j);
            }

            this.lines[i] = new Vector(lineLength, components);
        }
    }

    public int getLinesAmount() {
        return linesAmount;
    }

    public int getColumnsAmount() {
        return lineLength;
    }

    public void setLine(Vector line, int lineIndex) {
        if (lineIndex < 0 || lineIndex >= lines.length) {
            throw new IllegalArgumentException("Argument lineIndex must be >= 0 and less than lines amount");
        }

        lines[lineIndex] = line;
    }

    public Vector getLine(int lineIndex) {
        if (lineIndex < 0 || lineIndex >= lines.length) {
            throw new IllegalArgumentException("Argument lineIndex must be >= 0 and less than lines amount");
        }

        return lines[lineIndex];
    }

    public Vector getColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= lineLength) {
            throw new IllegalArgumentException("Argument columnIndex must be >= 0 and less than line length");
        }

        Vector column = new Vector(linesAmount);

        for (int i = 0; i < linesAmount; i++) {
            column.setComponent(lines[i].getComponent(columnIndex), i);
        }

        return column;
    }

    public Matrix transpose() {
        int matrixLinesAmount = linesAmount;
        Vector[] lines = new Vector[matrixLinesAmount];

        for (int i = 0; i < matrixLinesAmount; i++) {
            lines[i] = getColumn(i);

        }

        return new Matrix(lines);
    }

    public void multiplyOnScalar(double scalar) {
        double resultComponent;

        for (int i = 0; i < linesAmount; i++) {
            for (int j = 0; j < lineLength; j++) {
                resultComponent = lines[i].getComponent(j) * scalar;
                lines[i].setComponent(resultComponent, j);
            }
        }
    }

    public double getDeterminant() {
        if (linesAmount == 2) {
            return lines[0].getComponent(0) * lines[1].getComponent(1) -
                    lines[0].getComponent(1) * lines[1].getComponent(0);
        }

        double determinant = 0;

        final int i = 0;

        for (int j = 0; j < lineLength; j++) {
            Matrix minor = getMinor(j);

            determinant += minor.getDeterminant() * Math.pow(-1, i + j + 2) * lines[i].getComponent(j);
        }


        return determinant;
    }

    private Matrix getMinor(int column) {
        if (column < 0 || column >= lineLength) {
            throw new IllegalArgumentException("Argument column must be >= 0 and less than line length");
        }

        final int line = 0;
        final int linesAmount = getLinesAmount();
        final int columnsAmount = getColumnsAmount();

        Vector[] resultLines = new Vector[linesAmount - 1];
        Vector resultLine = new Vector(columnsAmount - 1);

        for (int i = 0, k = 0; i < linesAmount; i++, k++) {
            if (i == line) {
                if (i == linesAmount - 1) {
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

                resultLine.setComponent(lines[i].getComponent(j), m);
            }

            resultLines[k] = new Vector(resultLine);
        }

        return new Matrix(resultLines);
    }

    public Matrix multiplyOnVector(Vector vector) {
        Matrix resultMatrix;

        if (lineLength == vector.getSize()) {
            resultMatrix = new Matrix(vector.getSize(), 1);
        } else if (linesAmount == vector.getSize() && lineLength == 1) {
            resultMatrix = new Matrix(vector.getSize(), vector.getSize());
        } else {
            throw new IllegalArgumentException("Matrix must be a column if vector is a line. " +
                    "Or matrix width must yields to vector length");
        }

        double resultNumber;
        double number;

        for (int i = 0; i < resultMatrix.linesAmount; i++) {
            resultNumber = 0;
            number = vector.getComponent(i);
            Vector resultVector = new Vector(resultMatrix.lineLength);

            for (int j = 0; j < lineLength; j++) {
                resultNumber += number * lines[i].getComponent(j);
            }

            for (int k = 0; k < resultVector.getSize(); k++) {
                resultVector.setComponent(resultNumber, k);
            }

            resultMatrix.setLine(resultVector, i);
        }

        return resultMatrix;
    }

    public void addMatrix(Matrix matrix) {
        if (!this.equals(matrix)) {
            throw new IllegalArgumentException("Matrices sizes must equals");
        }

        double resultNumber;

        for (int i = 0; i < linesAmount; i++) {
            for (int j = 0; j < lineLength; j++) {
                resultNumber = lines[i].getComponent(j) + matrix.lines[i].getComponent(j);
                lines[i].setComponent(resultNumber, j);
            }
        }
    }

    public void subtractMatrix(Matrix matrix) {
        if (!this.equals(matrix)) {
            throw new IllegalArgumentException("Matrices sizes must equals");
        }

        double resultNumber;

        for (int i = 0; i < linesAmount; i++) {
            for (int j = 0; j < lineLength; j++) {
                resultNumber = lines[i].getComponent(j) - matrix.lines[i].getComponent(j);
                lines[i].setComponent(resultNumber, j);
            }
        }
    }

    public static Matrix addMatrices(Matrix matrix1, Matrix matrix2) {
        if (!matrix1.equals(matrix2)) {
            throw new IllegalArgumentException("Matrices sizes must equals");
        }

        Matrix resultMatrix = new Matrix(matrix1.linesAmount, matrix1.lineLength);
        double resultNumber;

        for (int i = 0; i < matrix1.linesAmount; i++) {
            for (int j = 0; j < matrix1.lineLength; j++) {
                resultNumber = matrix1.lines[i].getComponent(j) + matrix2.lines[i].getComponent(j);
                resultMatrix.lines[i].setComponent(resultNumber, j);
            }
        }

        return resultMatrix;
    }

    public static Matrix subtractMatrices(Matrix matrix1, Matrix matrix2) {
        if (!matrix1.equals(matrix2)) {
            throw new IllegalArgumentException("Matrices sizes must equals");
        }

        Matrix resultMatrix = new Matrix(matrix1.linesAmount, matrix1.lineLength);
        double resultNumber;

        for (int i = 0; i < matrix1.linesAmount; i++) {
            for (int j = 0; j < matrix1.lineLength; j++) {
                resultNumber = matrix1.lines[i].getComponent(j) - matrix2.lines[i].getComponent(j);
                resultMatrix.lines[i].setComponent(resultNumber, j);
            }
        }

        return resultMatrix;
    }

    public static Matrix multiplyMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.linesAmount != matrix2.lineLength || matrix1.lineLength != matrix2.linesAmount) {
            throw new IllegalArgumentException("Wrong matrices sizes");
        }

        int resultMatrixSize = matrix2.lineLength;
        Matrix resultMatrix = new Matrix(resultMatrixSize, resultMatrixSize);
        double resultNumber;

        for (int i = 0; i < matrix1.linesAmount; i++) {
            Vector currentLine = matrix1.getLine(i);
            Vector resultVector = new Vector(resultMatrixSize);

            for (int j = 0; j < matrix2.lineLength; j++) {
                Vector column = matrix2.getColumn(j);
                resultNumber = 0;

                for (int k = 0; k < column.getSize(); k++) {
                    resultNumber += currentLine.getComponent(k) * column.getComponent(k);
                }

                resultVector.setComponent(resultNumber, j);
            }

            resultMatrix.setLine(resultVector, i);
        }

        return resultMatrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (Vector line : lines) {
            stringBuilder.append(line.toString()).append(", ");
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

        Matrix m = (Matrix) obj;

        return m.lineLength == lineLength && m.linesAmount == linesAmount;
    }
}
