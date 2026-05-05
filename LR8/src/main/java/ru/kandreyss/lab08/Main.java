package ru.kandreyss.lab08;

import ru.kandreyss.lab08.matrices.ParallelMatrixProduct;
import ru.kandreyss.lab08.matrices.UsualMatrix;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random rnd = new Random();

        int rows1 = 100 + rnd.nextInt(200);
        int cols1 = 100 + rnd.nextInt(200);
        UsualMatrix m1 = new UsualMatrix(rows1, cols1);
        fillMatrix(m1, rnd);

        int rows2 = cols1;
        int cols2 = 100 + rnd.nextInt(200);
        UsualMatrix m2 = new UsualMatrix(rows2, cols2);
        fillMatrix(m2, rnd);

        int threadsCount = 10;
        ParallelMatrixProduct pm = new ParallelMatrixProduct(threadsCount);

        long startParallel = System.currentTimeMillis();
        UsualMatrix parallelResult = pm.parallelProduct(m1, m2);
        long endParallel = System.currentTimeMillis();

        System.out.println("Parallel time: " + (endParallel - startParallel) + " ms");

        long startStandard = System.currentTimeMillis();
        UsualMatrix standardResult = m1.product(m2);
        long endStandard = System.currentTimeMillis();

        System.out.println("Standard time: " + (endStandard - startStandard) + " ms");

        double speedup = (double) (endStandard - startStandard) /
                (endParallel - startParallel) * 100;

        System.out.println("Speedup: " + speedup + "%");

        System.out.println("Equal: " + standardResult.equals(parallelResult));
    }

    private static void fillMatrix(UsualMatrix matrix, Random rnd) {

        int min = 1;
        int max = 10;

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                int x = min + rnd.nextInt(max - min + 1);
                matrix.set(i, j, x);
            }
        }
    }
}