package org.suai.lab05.matrices;

import java.util.ListIterator;
import java.util.LinkedList;

public class SparceMatrix extends AbstractMatrix {

    private static class Element {
        private int row;
        private int column;
        private double value;

        public Element(int row, int column, double value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        public double getValue() {
            return value;
        }
    }

    private int rows;
    private int columns;
    private LinkedList<Element> list;

    public SparceMatrix(int rows, int columns) {
        list = new LinkedList<Element>();
        this.rows = rows;
        this.columns = columns;
    } 

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public double getElement(int row, int column) {
        ListIterator<Element> it = list.listIterator();

        while(it.hasNext()) {
            Element e = it.next();

            if(e.getRow() == row && e.getColumn() == column) {
                return e.getValue();
            }
        }

        return 0.0;
    }

    @Override
    protected IMatrix createInstance(int rows, int columns) {
        return new SparceMatrix(rows, columns);
    }

    @Override
    public void setElement(int row, int column, double value) {
        ListIterator<Element> it = list.listIterator();

        while (it.hasNext()) {
            Element e = it.next();

            if (e.row == row && e.column == column) {
                if (value == 0.0) {
                    it.remove();
                } else {
                    e.value = value;
                }
                return;
            }
        }

        if (value != 0.0) {
            list.add(new Element(row, column, value));
        }
    }
}
