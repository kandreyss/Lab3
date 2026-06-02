package ru.kandreyss.lab10;

import ru.kandreyss.lab10.stack.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int WRITER_THREADS = 2;
    private static final int READER_THREADS = 8;
    private static final int OPERATIONS = 1000;

    static void main() throws InterruptedException {

        SynchroStack slowStack = new SynchroStack();
        SynchroStackFast fastStack = new SynchroStackFast();

        for (int i = 0; i < 1000; i++) {
            slowStack.push(i);
            fastStack.push(i);
        }

        System.out.println("Multithreading:");
        System.out.println("Readers : " + READER_THREADS);
        System.out.println("Writers : " + WRITER_THREADS);
        System.out.println("Operations per thread : " + OPERATIONS);
        System.out.println();

        long slowTime = benchmark(slowStack);
        long fastTime = benchmark(fastStack);

        System.out.println("SynchroStack time     : " + slowTime + " ms");
        System.out.println("SynchroStackFast time : " + fastTime + " ms");

        double speedup = (double) slowTime / fastTime;

        System.out.printf("Speedup : %.2fx%n", speedup);
    }

    private static long benchmark(Stack stack)
            throws InterruptedException {

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < WRITER_THREADS; i++) {

            Thread writer = new Thread(() -> {
                for (int j = 0; j < OPERATIONS; j++) {

                    if (j % 2 == 0) {
                        stack.push(j);
                    } else {
                        stack.pop();
                    }
                }
            });

            threads.add(writer);
        }

        for (int i = 0; i < READER_THREADS; i++) {

            Thread reader = new Thread(() -> {

                for (int j = 0; j < OPERATIONS; j++) {
                    stack.toString();
                }
            });

            threads.add(reader);
        }

        long start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.currentTimeMillis();

        return end - start;
    }
}