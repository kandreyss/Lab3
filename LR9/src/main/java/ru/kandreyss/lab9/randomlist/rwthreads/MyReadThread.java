package ru.kandreyss.lab9.randomlist.rwthreads;

import ru.kandreyss.lab9.randomlist.MyRandomList;
import ru.kandreyss.lab9.randomlist.MyRandomListUnsafe;

public class MyReadThread extends Thread {
    private final Object list;

    public MyReadThread(Object list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                int result;
                if (list instanceof MyRandomListUnsafe) {
                    result = ((MyRandomListUnsafe) list).calcZero();
                } else {
                    result = ((MyRandomList) list).calcZero();
                }
                System.out.println("Non-zero count: " + result);
            }
        } catch (Exception e) {
            System.err.println("\n!!! [ERROR] Поток упал в небезопасной версии: " + e + " !!!\n");
        }
    }
}