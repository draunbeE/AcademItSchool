package ru.academit.khrushchev.matrix.matrix;

import java.util.Arrays;

public class Matrix {
    private int linesAmount;
    private int lineLength;
    private Vector[] lines;
    private Vector matrixLine;

    public Matrix(int linesAmount, int lineLength) {
        this.linesAmount = linesAmount;
        this.lineLength = lineLength;

        lines = new Vector[linesAmount];
        matrixLine = new Vector(lineLength);

        Arrays.fill(lines, matrixLine);
    }

    public Matrix(Matrix matrix) {
        linesAmount = matrix.linesAmount;
        lineLength = matrix.lineLength;

        lines = Arrays.copyOf(matrix.lines, matrix.lines.length);
        matrixLine = new Vector(lineLength);
    }

    //todo it doesn`t work correctly. Something wrong with rewriting matrixLines;
    public Matrix(double[][] matrix) {
        linesAmount = matrix.length;
        lineLength = matrix[0].length;

        lines = new Vector[linesAmount];
        matrixLine = new Vector(lineLength);

        for(int i = 0; i < linesAmount; i++) {
            matrixLine = new Vector(matrix[i]);

            lines[i] = matrixLine;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for(int i = 0; i < lines.length; i++) {
            stringBuilder.append(matrixLine.toString()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }
}
