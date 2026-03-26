package org.suai.lab05.matrices.exceptions;

public class MatrixDimensionMismatchException extends RuntimeException {
    public MatrixDimensionMismatchException() {
        super();
    }

    public MatrixDimensionMismatchException(String message) {
        super(message);
    }
}
