package org.suai.lab05.matrices.exceptions;

public class MatrixIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public MatrixIndexOutOfBoundsException() {
        super();
    }

    public MatrixIndexOutOfBoundsException(int rows, int columns) {
        super("Index out of bounds: (" + rows + ", " + columns + ")");
    }

    public MatrixIndexOutOfBoundsException(String message) {
        super(message);
    }
}