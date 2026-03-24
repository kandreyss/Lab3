package org.suai.lab4.sortedlists;

import java.util.LinkedList;
import java.util.ListIterator;

public class SortedIntegerList {

    private final LinkedList<Integer> list;
    private final boolean allowDuplicates;
    private boolean reversed;

    public SortedIntegerList(boolean allowDuplicates, boolean reversed) {
        this.allowDuplicates = allowDuplicates;
        this.list = new LinkedList<Integer>();
        this.reversed = reversed;
    }

    public void add(int value) {
        ListIterator<Integer> iterator = list.listIterator();

        while (iterator.hasNext()) {
            int current = iterator.next();

            if (current == value && !allowDuplicates) {
                return;
            }

            if (!reversed) {
                if (current > value) {
                    iterator.previous();
                    iterator.add(value);
                    return;
                }
            } else {
                if (current < value) {
                    iterator.previous();
                    iterator.add(value);
                    return;
                }
            }
        }

        iterator.add(value);
    }

    public void remove(int value) {
        ListIterator<Integer> iterator = list.listIterator();

        while (iterator.hasNext()) {
            int current = iterator.next();

            if (current == value) {
                iterator.remove();
                return;
            }

            if (!reversed) {
                if (current > value) {
                    return;
                }
            } else {
                if (current < value) {
                    return;
                }
            }
        }
    }

    public void reverse() {
        this.reversed = !this.reversed;

        LinkedList<Integer> newList = new LinkedList<>();
        ListIterator<Integer> it = list.listIterator(this.size());

        while (it.hasPrevious()) {
            newList.add(it.previous());
        }

        list.clear();
        list.addAll(newList);
    }

    public int size() {
        return list.size();
    }

    public int get(int index) {
        return list.get(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SortedIntegerList)) {
            return false;
        }

        SortedIntegerList other = (SortedIntegerList) obj;

        if (this.size() != other.size()) {
            return false;
        }

        ListIterator<Integer> it1 = this.list.listIterator();
        ListIterator<Integer> it2 = other.list.listIterator();

        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().equals(it2.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}