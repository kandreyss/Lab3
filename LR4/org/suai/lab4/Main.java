package org.suai.lab4;

import org.suai.lab4.sortedlists.SortedIntegerList;

public class Main {

    public static void main(String[] args) {
        SortedIntegerList list = new SortedIntegerList(false, false);

        list.add(5);
        list.add(2);
        list.add(3);

        System.out.println("List: " + list);

        list.remove(2);

        System.out.println("After remove: " + list);
    }
}