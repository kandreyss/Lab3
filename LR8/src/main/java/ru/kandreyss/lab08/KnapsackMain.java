package ru.kandreyss.lab08;

import ru.kandreyss.lab08.KnapsackSolver.*;

public class KnapsackMain {
    static void main() {
        int n = 26;
        int[] weights = new int[n];
        int[] values = new int[n];

        java.util.Random rand = new java.util.Random();
        int totalWeight = 0;

        for (int i = 0; i < n; i++) {
            weights[i] = rand.nextInt(100);
            values[i] = rand.nextInt(100);

            totalWeight += weights[i];
        }

        int capacity = totalWeight / 2;

        Knapsack k1 = new Knapsack(weights, values, capacity);
        KnapsackSolver solver = new KnapsackSolver();


        long start = System.currentTimeMillis();
        solver.solveKnapsack(k1, 1);
        long end = System.currentTimeMillis();

        System.out.println("Time with 1 thread: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        solver.solveKnapsack(k1, 4);
        end = System.currentTimeMillis();

        System.out.println("Time with 4 threads: " + (end - start) + "ms");
    }
}
