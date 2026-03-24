package org.suai.lab4.sortedlists;

public class SortedIntegerListTest {

    public static void main(String[] args) {
        testAdd();
        testRemove();
        testEquals();
        testDuplicates();
        testIsSorted();
        testIsSortedReversed();
        testReversedAdd();
        testReverseMethod();

        System.out.println("All tests passed!");
    }

    static void testAdd() {
        final String testName = "testAdd";
        System.out.println(testName);

        SortedIntegerList list = new SortedIntegerList(false, false);

        list.add(5);
        list.add(2);
        list.add(3);

        check(testName, list.size(), 3);
        check(testName, list.get(0), 2);
        check(testName, list.get(1), 3);
        check(testName, list.get(2), 5);
    }

    static void testRemove() {
        final String testName = "testRemove";
        System.out.println(testName);

        SortedIntegerList list = new SortedIntegerList(true, false);

        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(2);

        check(testName, list.size(), 2);
        check(testName, list.get(0), 1);
        check(testName, list.get(1), 3);
    }

    static void testEquals() {
        final String testName = "testEquals";
        System.out.println(testName);

        SortedIntegerList list1 = new SortedIntegerList(true, false);
        SortedIntegerList list2 = new SortedIntegerList(true, false);

        list1.add(1);
        list1.add(2);

        list2.add(1);
        list2.add(2);

        check(testName, list1.equals(list2), true);

        list2.add(3);

        check(testName, list1.equals(list2), false);
    }

    static void testDuplicates() {
        final String testName = "testDuplicates";
        System.out.println(testName);

        SortedIntegerList list = new SortedIntegerList(false, false);

        list.add(2);
        list.add(2);

        check(testName, list.size(), 1);

        SortedIntegerList list2 = new SortedIntegerList(true, false);

        list2.add(2);
        list2.add(2);

        check(testName, list2.size(), 2);
    }

    static void testIsSorted() {
        final String testName = "testIsSorted";
        System.out.println(testName);

        SortedIntegerList list = new SortedIntegerList(true, false);

        list.add(10);
        list.add(1);
        list.add(5);
        list.add(3);

        check(testName, isSorted(list, false), true);
    }

    static void testIsSortedReversed() {
        final String testName = "testIsSortedReversed";
        System.out.println(testName);

        SortedIntegerList list = new SortedIntegerList(false, true);
        list.add(10);
        list.add(1);
        list.add(45);
        list.add(12);
        list.add(8);

        check(testName, isSorted(list, true), true);
    }

    static void testReversedAdd() {
        final String testName = "testReversedAdd";
        System.out.println(testName);

        SortedIntegerList list = new SortedIntegerList(true, true);

        list.add(2);
        list.add(5);
        list.add(3);

        check(testName, list.size(), 3);
        check(testName, list.get(0), 5);
        check(testName, list.get(1), 3);
        check(testName, list.get(2), 2);
    }

    static void testReverseMethod() {
        final String testName = "testReverseMethod";
        System.out.println(testName);

        SortedIntegerList list = new SortedIntegerList(false, false);

        list.add(1);
        list.add(3);
        list.add(2);

        list.reverse();

        check(testName, list.size(), 3);
        check(testName, list.get(0), 3);
        check(testName, list.get(1), 2);
        check(testName, list.get(2), 1);
    }


    static boolean isSorted(SortedIntegerList list, boolean reversed) {
        if (list.size() < 2) return true;

        for (int i = 1; i < list.size(); i++) {
            if (!reversed) {
                if (list.get(i - 1) > list.get(i)) {
                    return false;
                }
            } else {
                if (list.get(i - 1) < list.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    static void check(String testName, Object actual, Object expected) {
        if (!actual.equals(expected)) {
            throw new RuntimeException(
                testName + ": expected " + expected + ", but got " + actual
            );
        }
    }
}