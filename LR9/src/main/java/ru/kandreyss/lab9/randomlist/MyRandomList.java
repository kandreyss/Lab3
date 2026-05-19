package ru.kandreyss.lab9.randomlist;

import java.util.LinkedList;
import java.util.Random;

public class MyRandomList {

    private final LinkedList<Integer> list = new LinkedList<>();
    private final Random random = new Random();

    public synchronized void addNumber() {
        int value = random.nextInt(100);
        list.add(value);
    }

    public synchronized void removeNumber() {
        if (list.isEmpty()) {
            return;
        }

        int index = random.nextInt(list.size());
        list.remove(index);
    }

    public synchronized int calcZero() {
        int count = 0;
        for (int x : list) {
            if (x != 0) {
                count++;
            }
        }

        return count;
    }
}
