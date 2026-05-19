package ru.kandreyss.lab9;

public class Deadlock {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    static void main() {

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: захватил lock1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1: ждёт lock2");

                synchronized (lock2) {
                    System.out.println("Thread 1: захватил lock2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: захватил lock2");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: ждёт lock1");

                synchronized (lock1) {
                    System.out.println("Thread 2: захватил lock1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}