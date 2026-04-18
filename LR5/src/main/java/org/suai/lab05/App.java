package org.suai.lab05;

import java.util.Random;

import org.suai.lab05.matrices.*;

public class App {
    public static void main(String[] args) {
        int size = 13;

        SparseMatrix sparseA = new SparseMatrix(13, 13);
        fillMatrix(sparseA, new Random(43), false);
        SquareMatrix squareB = new SquareMatrix(13);
        fillMatrix(squareB, new Random(83), false);

        SquareMatrix squareA = new SquareMatrix(13);
        fillMatrix(squareA, new Random(43), false);
        SparseMatrix sparseB = new SparseMatrix(13, 13);
        fillMatrix(sparseB, new Random(83), false);

        IMatrix squareProduct = squareA.product(squareB);
        IMatrix sparseProduct = sparseA.product(sparseB);

        System.out.println("SquareMatrix product equals SparseMatrix product: "
            + squareProduct.equals(sparseProduct));

        Random rnd = new Random();


        long start = System.currentTimeMillis();
        SparseMatrixTree smt = new SparseMatrixTree(100, 100);
        fillMatrix(smt, rnd, false);
        long end = System.currentTimeMillis();
        System.out.println("SparseMatrixTree(100x100) was created: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        SparseMatrix sp = new SparseMatrix(100, 100);
        fillMatrix(sp, rnd, false);
        end = System.currentTimeMillis();
        System.out.println("SparseMatrix(100x100) was created: " + (end - start) + " ms");

    }

    private static void fillMatrix(IMatrix matrix, Random rand, boolean showStat) {
        for (int row = 0; row < matrix.getRows(); row++) {
            for (int col = 0; col < matrix.getColumns(); col++) {
                matrix.setElement(row, col, rand.nextInt(10));
                if (showStat) {
                    System.out.printf("Created (%d, %d)\n", row, col);
                }
            }
        }
    }
}
