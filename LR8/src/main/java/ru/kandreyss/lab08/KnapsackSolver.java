package ru.kandreyss.lab08;

public class KnapsackSolver {

    public record Knapsack(int[] weights, int[] values, int capacity) {}

    private static class KnapsackWorker implements Runnable {
        private final Knapsack k;
        private final long startMask;
        private final long endMask;

        int[] bestSelection;
        int maxValue = -1;

        public KnapsackWorker(Knapsack k, long startMask, long endMask) {
            this.k = k;
            this.startMask = startMask;
            this.endMask = endMask;
        }

        @Override
        public void run() {
            int n = k.weights.length;
            long bestMask = 0;

            for (long mask = startMask; mask < endMask; mask++) {
                int currentWeight = 0;
                int currentValue = 0;

                for (int i = 0; i < n; i++) {
                    if (((mask >> i) & 1) == 1) {
                        currentWeight += k.weights[i];
                        currentValue += k.values[i];
                    }
                }

                if (currentWeight <= k.capacity && currentValue > maxValue) {
                    maxValue = currentValue;
                    bestMask = mask;
                }
            }

            bestSelection = new int[n];
            for (int i = 0; i < n; i++) {
                bestSelection[i] = (int) ((bestMask >> i) & 1);
            }
        }
    }

    public int[] solveKnapsack(Knapsack k, int threadNum) {
        int n = k.weights.length;
        long totalCombinations = 1L << n;

        Thread[] threads = new Thread[threadNum];
        KnapsackWorker[] workers = new KnapsackWorker[threadNum];

        long chunk = totalCombinations / threadNum;
        long remainder = totalCombinations % threadNum;

        long currentStart = 0;
        for (int i = 0; i < threadNum; i++) {
            long currentEnd = currentStart + chunk + (i < remainder ? 1 : 0);

            workers[i] = new KnapsackWorker(k, currentStart, currentEnd);
            threads[i] = new Thread(workers[i]);
            threads[i].start();

            currentStart = currentEnd;
        }

        for (int i = 0; i < threadNum; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new int[n];
            }
        }

        int globalMaxValue = -1;
        int[] globalBestSelection = new int[n];

        for (int i = 0; i < threadNum; i++) {
            if (workers[i].maxValue > globalMaxValue) {
                globalMaxValue = workers[i].maxValue;
                globalBestSelection = workers[i].bestSelection;
            }
        }

        return globalBestSelection;
    }
}

