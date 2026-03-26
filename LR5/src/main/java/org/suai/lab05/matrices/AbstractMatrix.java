package org.suai.lab05.matrices;

import org.suai.lab05.matrices.exceptions.MatrixDimensionMismatchException;
import org.suai.lab05.matrices.exceptions.MatrixIndexOutOfBoundsException;

public abstract class AbstractMatrix implements IMatrix {
    
    protected abstract IMatrix createInstance(int rows, int columns);

    protected void checkIndex(int row, int column) {
        if (row < 0 || row >= getRows()) {
            throw new MatrixIndexOutOfBoundsException(
                "Row index out of bounds: " + row
            );
        }

        if (column < 0 || column >= getColumns()) {
            throw new MatrixIndexOutOfBoundsException(
                "Column index out of bounds: " + column
            );
        }
    }
    
    @Override
    public IMatrix sum(IMatrix other) {
        if (this.getRows() != other.getRows() || this.getColumns() != other.getColumns()) {
            throw new MatrixDimensionMismatchException(
                "Matrix sizes must match for addition. " +
                "First matrix: " + getRows() + "x" + getColumns() +
                ", Second matrix: " + other.getRows() + "x" + other.getColumns()
            );
        }

        IMatrix result = createInstance(this.getRows(), this.getColumns());

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                result.setElement(i, j, this.getElement(i, j) + other.getElement(i, j));
            }
        }

        return result;
    }

    @Override
    public IMatrix product(IMatrix other) {
        if (this.getColumns() != other.getRows()) {
            throw new MatrixDimensionMismatchException(
                "Matrix dimensions must match for multiplication. " +
                "First matrix columns: " + getColumns() +
                ", Second matrix rows: " + other.getRows()
            );
        }

        IMatrix result = createInstance(this.getRows(), other.getColumns());

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < other.getColumns(); j++) {
                double sum = 0;
                for (int k = 0; k < this.getColumns(); k++) {
                    sum += this.getElement(i, k) * other.getElement(k, j);
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

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof IMatrix)) {
            return false;
        }

        IMatrix other = (IMatrix) obj;

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
}
