package org.suai.lab05;

import java.util.Random;

import org.suai.lab05.matrices.*;

public class App {
    public static void main(String[] args) {
        Random rnd = new Random();
        int rows = rnd.nextInt(150) + 100;
        int columns = rnd.nextInt(150) + 100;
        long start = System.currentTimeMillis();
        SparseMatrixTree smt = new SparseMatrixTree(rows, columns);
        fillMatrix(smt, rnd, false);
        long end = System.currentTimeMillis();
        System.out.println("SparseMatrixTree(" + rows + " x " + columns + ") was created: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        SparseMatrix sp = new SparseMatrix(rows, columns);
        fillMatrix(sp, rnd, false);
        end = System.currentTimeMillis();
        System.out.println("SparseMatrix(" + rows + " x " + columns + ") was created: " + (end - start) + " ms");
    }

    private static void fillMatrix(IMatrix matrix, Random rand, boolean showStat) {
        for (int row = 0; row < matrix.getRows(); row++) {
            for (int col = 0; col < matrix.getColumns(); col++) {
                matrix.setElement(row, col, (double) rand.nextInt(1000) / 100.0);
                if (showStat) {
                    System.out.printf("Created (%d, %d)\n", row, col);
                }
            }
        }
    }
}
