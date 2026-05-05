package ru.kandreyss.lab08.matrices;

public class ParallelMatrixProduct {

    private final int threadsCount;

    private static class Worker implements Runnable {

        private final UsualMatrix a;
        private final UsualMatrix b;
        private final UsualMatrix result;
        private final int rowStart;
        private final int rowEnd;

        public Worker(UsualMatrix a, UsualMatrix b, UsualMatrix result,
                      int rowStart, int rowEnd) {
            this.a = a;
            this.b = b;
            this.result = result;
            this.rowStart = rowStart;
            this.rowEnd = rowEnd;
        }

        @Override
        public void run() {
            for (int i = rowStart; i < rowEnd; i++) {
                for (int j = 0; j < b.cols; j++) {

                    int sum = 0;

                    for (int k = 0; k < a.cols; k++) {
                        sum += a.get(i, k) * b.get(k, j);
                    }

                    result.set(i, j, sum);
                }
            }
        }
    }

    public ParallelMatrixProduct(int threadsCount) {
        this.threadsCount = threadsCount;
    }

    public UsualMatrix parallelProduct(UsualMatrix a, UsualMatrix b) {

        if (a.cols != b.rows) {
            throw new IllegalArgumentException("Matrix sizes are incompatible");
        }

        UsualMatrix result = new UsualMatrix(a.rows, b.cols);

        Thread[] threads = new Thread[threadsCount];

        int rows = a.rows;
        int baseRowsPerThread = rows / threadsCount;
        int remainder = rows % threadsCount;

        int currentRow = 0;

        for (int t = 0; t < threadsCount; t++) {

            int rowsForThread = baseRowsPerThread + (t < remainder ? 1 : 0);

            int start = currentRow;
            int end = start + rowsForThread;

            threads[t] = new Thread(new Worker(a, b, result, start, end));
            threads[t].start();

            currentRow = end;
        }

        for (int t = 0; t < threadsCount; t++) {
            try {
                threads[t].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}