package org.suai.lab05.matrices;

public class SquareMatrix extends UsualMatrix {
    public SquareMatrix(int size) {
        super(size, size);
        
        for (int i = 0; i < getRows(); i++) {
            super.setElement(i, i, 1);
        }
    }

    public int getSize() {
        return super.getRows();
    }

    @Override
    protected IMatrix createInstance(int rows, int columns) {
        if (rows != columns) {
            throw new IllegalArgumentException(
                "SquareMatrix requires square dimensions: " + rows + "x" + columns
            );
        }
        return new SquareMatrix(rows);
    }
}
