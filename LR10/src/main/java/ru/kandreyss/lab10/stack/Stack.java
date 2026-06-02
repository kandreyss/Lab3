package ru.kandreyss.lab10.stack;

public class Stack {

    protected static class Node {

        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    protected Node top = null;

    public void push(int value) {
        Node node = new Node(value);
        node.next = top;
        top = node;
    }

    public int pop() {
        if (top == null) {
            throw new RuntimeException("Stack is empty");
        }

        int value = top.value;
        top = top.next;

        return value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = top;

        while (current != null) {
            sb.append(current.value);

            if (current.next != null) {
                sb.append(", ");
            }

            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Stack other)) {
            return false;
        }

        Node a = this.top;
        Node b = other.top;

        while (a != null && b != null) {
            if (a.value != b.value) {
                return false;
            }

            a = a.next;
            b = b.next;
        }

        return a == null && b == null;
    }
}