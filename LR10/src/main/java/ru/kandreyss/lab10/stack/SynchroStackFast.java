package ru.kandreyss.lab10.stack;

public class SynchroStackFast extends Stack {

    private int readers = 0;
    private boolean writer = false;

    private synchronized void beginRead() throws InterruptedException {
        while (writer) {
            wait();
        }
        readers++;
    }

    private synchronized void endRead() {
        readers--;
        if (readers == 0) {
            notifyAll();
        }
    }

    private synchronized void beginWrite() throws InterruptedException {
        while (writer || readers > 0) {
            wait();
        }
        writer = true;
    }

    private synchronized void endWrite() {
        writer = false;
        notifyAll();
    }

    @Override
    public void push(int value) {
        try {
            beginWrite();
            super.push(value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            endWrite();
        }
    }

    @Override
    public int pop() {
        try {
            beginWrite();
            return super.pop();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted");
        } finally {
            endWrite();
        }
    }

    @Override
    public String toString() {
        try {
            beginRead();
            return super.toString();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "[]";
        } finally {
            endRead();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SynchroStackFast other)) {
            return false;
        }

        SynchroStackFast first =
                this.hashCode() <= other.hashCode() ? this : other;
        SynchroStackFast second =
                this.hashCode() <= other.hashCode() ? other : this;

        try {
            first.beginRead();
            second.beginRead();
            return super.equals(obj);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            second.endRead();
            first.endRead();
        }
    }
}