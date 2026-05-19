package ru.kandreyss.lab9;

import ru.kandreyss.lab9.randomlist.MyRandomList;
import ru.kandreyss.lab9.randomlist.MyRandomListUnsafe;
import ru.kandreyss.lab9.randomlist.rwthreads.MyReadThread;
import ru.kandreyss.lab9.randomlist.rwthreads.MyWriteThread;

public class Main {
    static void main() throws InterruptedException {

        System.out.println("STARTING UNSAFE VERSION");
        MyRandomListUnsafe unsafeList = new MyRandomListUnsafe();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 8; i++) {
            threads[i] = new MyWriteThread(unsafeList);
        }
        threads[8] = new MyReadThread(unsafeList);
        threads[9] = new MyReadThread(unsafeList);

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();


        System.out.println("\nSTARTING SAFE VERSION");
        MyRandomList safeList = new MyRandomList();
        Thread[] threads2 = new Thread[10];

        for (int i = 0; i < 8; i++) {
            threads2[i] = new MyWriteThread(safeList);
        }
        threads2[8] = new MyReadThread(safeList);
        threads2[9] = new MyReadThread(safeList);

        for (Thread t : threads2) t.start();
        for (Thread t : threads2) t.join();

        System.out.println("FINISHED SUCCESSFULLY");
    }
}