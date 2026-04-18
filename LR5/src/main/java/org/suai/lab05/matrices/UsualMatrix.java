package org.suai.lab05.matrices;

public class UsualMatrix extends AbstractMatrix {
    private final double[][] matrix;
    private final int rows;
    private final int columns;

    public UsualMatrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Matrix size must be positive");
        }

        matrix = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    protected IMatrix createInstance(int rows, int columns) {
        return new UsualMatrix(rows, columns);
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public void setElement(int row, int column, double value) {
        checkIndex(row, column);
        matrix[row][column] = value;
    }

    @Override
    public double getElement(int row, int column) {
        checkIndex(row, column);
        return matrix[row][column];
    }


}