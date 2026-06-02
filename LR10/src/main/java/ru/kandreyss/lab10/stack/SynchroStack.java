package ru.kandreyss.lab10.stack;

public class SynchroStack extends Stack {

    @Override
    public synchronized void push(int value) {
        super.push(value);
    }

    @Override
    public synchronized int pop() {
        return super.pop();
    }

    @Override
    public synchronized String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SynchroStack other))
            return false;

        SynchroStack first =
                this.hashCode() <= obj.hashCode() ? this : other;
        SynchroStack second =
                this.hashCode() <= obj.hashCode() ? other : this;

        synchronized (first) {
            synchronized (second) {
                return super.equals(obj);
            }
        }
    }
}