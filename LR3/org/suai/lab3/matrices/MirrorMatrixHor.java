package org.suai.lab3.matrices;

public class MirrorMatrixHor extends Matrix {
    public MirrorMatrixHor(int rows, int columns) {
        super(rows / 2 + rows % 2, columns);
        super.rows = rows;
    }

    @Override
    public int getElement(int row, int column) {
        int targetRow = row;
        if (row >= matrix.length) {
            targetRow = (this.rows - 1) - row;
        }

        return matrix[targetRow][column];
    }

    @Override
    public void setElement(int row, int column, int value) {
        int targetRow = row;
        if (row >= matrix.length) {
            targetRow = (this.rows - 1) - row;
        }

        matrix[targetRow][column] = value;
    }
}