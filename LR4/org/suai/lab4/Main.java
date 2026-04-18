package org.suai.lab4;

import java.util.Random;
import org.suai.lab4.sortedlists.SortedIntegerList;

public class Main {

    public static void main(String[] args) {
        Random rnd = new Random();

        testWithoutDuplicates(rnd);
        testWithDuplicates(rnd);
        testRemoveCases();
        testEquals();
        testEdgeCases();
        testReverse();
        testAllFlagCombinations(rnd);
    }

    private static void testWithoutDuplicates(Random rnd) {
        System.out.println("=== WITHOUT DUPLICATES ===");
        SortedIntegerList list = new SortedIntegerList(false, false);
        for (int i = 0; i < 20; i++) {
            list.add(rnd.nextInt(10));
        }
        System.out.println("List: " + list);
        int value = rnd.nextInt(10);
        list.add(value);
        System.out.println("After adding " + value + ": " + list);
        list.remove(value);
        System.out.println("After removing " + value + ": " + list);
    }

    private static void testWithDuplicates(Random rnd) {
        System.out.println("\n=== WITH DUPLICATES ===");
        SortedIntegerList list = new SortedIntegerList(true, false);
        for (int i = 0; i < 20; i++) {
            list.add(rnd.nextInt(5));
        }
        System.out.println("List: " + list);
        int value = rnd.nextInt(5);
        list.add(value);
        System.out.println("After adding " + value + ": " + list);
        list.remove(value);
        System.out.println("After removing ONE " + value + ": " + list);
    }

    private static void testRemoveCases() {
        System.out.println("\n=== REMOVE CASES ===");
        SortedIntegerList list = new SortedIntegerList(true, false);
        list.add(1); list.add(3); list.add(5); list.add(7);
        System.out.println("Original: " + list);
        list.remove(1);
        System.out.println("Remove first (1): " + list);
        list.remove(5);
        System.out.println("Remove middle (5): " + list);
        list.remove(7);
        System.out.println("Remove last (7): " + list);
        list.remove(100);
        System.out.println("Remove non-existing (100): " + list);
    }

    private static void testEquals() {
        System.out.println("\n=== EQUALS TEST ===");
        SortedIntegerList list1 = new SortedIntegerList(true, false);
        SortedIntegerList list2 = new SortedIntegerList(true, false);
        list1.add(1); list1.add(2); list1.add(2);
        list2.add(1); list2.add(2); list2.add(2);
        System.out.println("List1: " + list1);
        System.out.println("List2: " + list2);
        System.out.println("Equal: " + list1.equals(list2));
        list2.add(3);
        System.out.println("After change List2: " + list2);
        System.out.println("Equal: " + list1.equals(list2));
    }

    private static void testEdgeCases() {
        System.out.println("\n=== EDGE CASES ===");
        SortedIntegerList list = new SortedIntegerList(true, false);
        System.out.println("Empty list: " + list);
        list.remove(10);
        System.out.println("Remove from empty: " + list);
        list.add(5);
        System.out.println("One element: " + list);
        list.remove(5);
        System.out.println("After removing only element: " + list);
    }

    private static void testReverse() {
        System.out.println("\n=== REVERSE TEST ===");
        SortedIntegerList list = new SortedIntegerList(true, false);
        list.add(1); list.add(3); list.add(2); list.add(4); list.add(3);
        System.out.println("Original list: " + list);
        list.reverse();
        System.out.println("After reverse: " + list);
        list.reverse();
        System.out.println("After reverse again: " + list);
    }

    // ===== 6. Проверка всех комбинаций флагов =====
    private static void testAllFlagCombinations(Random rnd) {
        System.out.println("\n=== TEST ALL FLAG COMBINATIONS ===");

        // allowDuplicates=false, reversed=true
        SortedIntegerList list1 = new SortedIntegerList(false, true);
        System.out.println("\nList1 (allowDuplicates=false, reversed=true)");
        for (int i = 0; i < 10; i++) list1.add(rnd.nextInt(10));
        System.out.println("After add: " + list1);
        list1.remove(5);
        System.out.println("After remove 5: " + list1);
        list1.reverse();
        System.out.println("After reverse: " + list1);

        // allowDuplicates=true, reversed=false
        SortedIntegerList list2 = new SortedIntegerList(true, false);
        System.out.println("\nList2 (allowDuplicates=true, reversed=false)");
        for (int i = 0; i < 10; i++) list2.add(rnd.nextInt(5));
        System.out.println("After add: " + list2);
        list2.remove(2);
        System.out.println("After remove 2: " + list2);
        list2.reverse();
        System.out.println("After reverse: " + list2);

        // allowDuplicates=true, reversed=true
        SortedIntegerList list3 = new SortedIntegerList(true, true);
        System.out.println("\nList3 (allowDuplicates=true, reversed=true)");
        for (int i = 0; i < 10; i++) list3.add(rnd.nextInt(5));
        System.out.println("After add: " + list3);
        list3.remove(3);
        System.out.println("After remove 3: " + list3);
        list3.reverse();
        System.out.println("After reverse: " + list3);

        // Проверка equals на развернутых списках
        System.out.println("\nEquals check between list2 and list3: " + list2.equals(list3));
    }
}