package org.suai.lab3.matrices;

import org.suai.lab3.exceptions.*;

public class Matrix {
    
    protected final int[][] matrix;
    protected int rows;
    protected int columns;

    protected void checkIndex(int row, int column) {
        if (row < 0 || row >= getRows() || column < 0 || column >= getColumns()) {
            throw new MatrixIndexOutOfBoundsException(
                "Index (" + row + ", " + column + ") outside matrix boundary: "
                + getRows() + "x" + getColumns()
            );
        }
    }
    
    public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new BadMatrixSizesException(
                "Matrix dimensions must be positive: rows=" + rows + ", columns=" + columns
            );
        }

        matrix = new int[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public int getElement(int row, int column) {
        checkIndex(row, column);
        return matrix[row][column];
    }

    public void setElement(int row, int column, int value) {
        checkIndex(row, column);
        matrix[row][column] = value;
    }

    public int getRows() {
        return rows;
    }

    public final int getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getRows(); i++) {
            sb.append("[");

            for (int j = 0; j < getColumns(); j++) {
                sb.append(getElement(i, j));
                if (j < getColumns() - 1) {
                    sb.append(", ");
                }
            }

            sb.append("]\n");
        }

        return sb.toString();
    }

    public Matrix sum(Matrix other) {
        if (other.getRows() != this.getRows() || other.getColumns() != this.getColumns()) {
            throw new MatrixDimensionMismatchException(
                "Matrix sizes must match for addition. " +
                "First matrix: " + getRows() + "x" + getColumns() +
                ", Second matrix: " + other.getRows() + "x" + other.getColumns()
            );
        }

        Matrix result = new Matrix(getRows(), getColumns());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(i, j, getElement(i, j) + other.getElement(i, j));
            }
        }

        return result;
    }

    public Matrix product(Matrix other) {
        if (getColumns() != other.getRows()) {
            throw new MatrixDimensionMismatchException(
                "Matrix multiplication impossible. " +
                "Columns of first matrix must equal rows of second matrix. " +
                "First matrix: " + getRows() + "x" + getColumns() +
                ", Second matrix: " + other.getRows() + "x" + other.getColumns()
            );
        }

        int rowsA = getRows();
        int colsA = getColumns();
        int colsB = other.getColumns();

        Matrix result = new Matrix(rowsA, colsB);

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                int sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += getElement(i, k) * other.getElement(k, j);
                }
                result.setElement(i, j, sum);
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj  == null) {
            return false;
        }

        if (!(obj instanceof Matrix)) {
            return false;
        }

        Matrix other = (Matrix) obj;

        if (this.getColumns() != other.getColumns() || this.getRows() != other.getRows()) {
            return false;
        }

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (this.getElement(i, j) != other.getElement(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                hash = 31 * hash + matrix[i][j];
            }
        }

        return hash;
    }
}