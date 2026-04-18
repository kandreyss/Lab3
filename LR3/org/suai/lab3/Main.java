package org.suai.lab3;

import java.util.Random;

import org.suai.lab3.matrices.Matrix;
import org.suai.lab3.matrices.SquareMatrix;
import org.suai.lab3.matrices.MirrorMatrixHor;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();

        // ===== SUM Matrix + SquareMatrix =====
        {
            System.out.println("=== SUM Matrix + SquareMatrix ===");
            final int matrixSumSize = 7 + rnd.nextInt(7);

            Matrix matrixForSum = new Matrix(matrixSumSize, matrixSumSize);
            fillMatrix(matrixForSum, rnd);
            System.out.println("Matrix (" + matrixForSum.getRows() + " x " + matrixForSum.getColumns() + ")");
            System.out.println(matrixForSum);

            SquareMatrix squareMatrixForSum = new SquareMatrix(matrixSumSize);
            fillMatrix(squareMatrixForSum, rnd);
            System.out.println("SquareMatrix (" + squareMatrixForSum.getRows() + " x " + squareMatrixForSum.getColumns() + ")");
            System.out.println(squareMatrixForSum);

            Matrix resultOfSum = matrixForSum.sum(squareMatrixForSum);
            System.out.println("Result of Sum: ");
            System.out.println(resultOfSum);
        }

        // ===== PRODUCT Matrix × SquareMatrix =====
        {
            System.out.println("=== PRODUCT Matrix × SquareMatrix ===");
            final int matrixRows = 1 + rnd.nextInt(14);
            final int matrixColumns = 1 + rnd.nextInt(13);

            Matrix matrixForProduct = new Matrix(matrixRows, matrixColumns);
            fillMatrix(matrixForProduct, rnd);
            System.out.printf("Matrix (%d x %d):\n", matrixForProduct.getRows(), matrixForProduct.getColumns());
            System.out.println(matrixForProduct);

            SquareMatrix squareMatrixForProduct = new SquareMatrix(matrixColumns);
            fillMatrix(squareMatrixForProduct, rnd);
            System.out.printf("SquareMatrix (%d x %d):\n", matrixColumns, matrixColumns);
            System.out.println(squareMatrixForProduct);

            Matrix result = matrixForProduct.product(squareMatrixForProduct);
            System.out.println("Result of product:");
            System.out.println(result);
        }

        // ===== SUM Matrix + MirrorMatrixHor =====
        {
            System.out.println("=== SUM Matrix + MirrorMatrixHor ===");

            final int rows = 6 + rnd.nextInt(6);
            final int cols = 6 + rnd.nextInt(6);

            Matrix matrix = new Matrix(rows, cols);
            fillMatrix(matrix, rnd);
            System.out.printf("Matrix (%d x %d):\n", matrix.getRows(), matrix.getColumns());
            System.out.println(matrix);

            MirrorMatrixHor mirror = new MirrorMatrixHor(rows, cols);
            fillMatrix(mirror, rnd);
            System.out.printf("MirrorMatrixHor (%d x %d):\n", mirror.getRows(), mirror.getColumns());
            System.out.println(mirror);

            Matrix result = matrix.sum(mirror);
            System.out.println("Result of sum:");
            System.out.println(result);
        }

        // ===== PRODUCT MirrorMatrixHor × SquareMatrix =====
        {
            System.out.println("=== PRODUCT MirrorMatrixHor × SquareMatrix ===");

            final int rows = 5 + rnd.nextInt(5);
            final int cols = 5 + rnd.nextInt(5);

            MirrorMatrixHor mirror = new MirrorMatrixHor(rows, cols);
            fillMatrix(mirror, rnd);
            System.out.printf("MirrorMatrixHor (%d x %d):\n", mirror.getRows(), mirror.getColumns());
            System.out.println(mirror);

            SquareMatrix square = new SquareMatrix(cols);
            fillMatrix(square, rnd);
            System.out.printf("SquareMatrix (%d x %d):\n", square.getRows(), square.getColumns());
            System.out.println(square);

            Matrix result = mirror.product(square);
            System.out.println("Result of product:");
            System.out.println(result);
        }
    }

    public static void fillMatrix(Matrix m, Random rnd) {
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getColumns(); j++) {
                m.setElement(i, j, rnd.nextInt(100));
            }
        }
    }
}