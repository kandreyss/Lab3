package ru.kandreyss.lab9.randomlist.rwthreads;

import ru.kandreyss.lab9.randomlist.MyRandomList;
import ru.kandreyss.lab9.randomlist.MyRandomListUnsafe;

public class MyWriteThread extends Thread {
    private final Object list;

    public MyWriteThread(Object list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            if (list instanceof MyRandomListUnsafe) {
                MyRandomListUnsafe u = (MyRandomListUnsafe) list;
                if (Math.random() < 0.5) {
                    u.addNumber();
                } else {
                    u.removeNumber();
                }
            } else {
                MyRandomList s = (MyRandomList) list;
                if (Math.random() < 0.5) {
                    s.addNumber();
                } else {
                    s.removeNumber();
                }
            }
        }
    }
}