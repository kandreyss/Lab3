package org.suai.lab3.matrices;

import org.suai.lab3.exceptions.MatrixDimensionMismatchException;

public class SquareMatrix extends Matrix {
    public SquareMatrix(int size) {
        super(size, size);

        for (int i = 0; i < size; i++) {
            setElement(i, i, 1);
        }
    }

    public int getSize() {
        return getRows();
    }

    @Override
    public SquareMatrix sum(Matrix other) {
        if (other.getRows() != this.getRows() || other.getColumns() != this.getColumns()) {
            throw new MatrixDimensionMismatchException(
                "Matrix sizes must match for addition. " +
                "First matrix: " + getRows() + "x" + getColumns() +
                ", Second matrix: " + other.getRows() + "x" + other.getColumns()
            );
        }

        int size = getRows();
        SquareMatrix result = new SquareMatrix(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.setElement(i, j, getElement(i, j) + other.getElement(i, j));
            }
        }

        return result;
    }
}
