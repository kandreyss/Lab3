package org.suai.lab05.matrices;

import java.util.ListIterator;
import java.util.LinkedList;

public class SparseMatrix extends AbstractMatrix {

    private static class Element {

        private final int row;
        private final int column;
        private double value;

        private Element(int row, int column, double value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }

        public int row() {
            return row;
        }

        public int column() {
            return column;
        }

        public double value() {
            return value;
        }
    }

    private final int rows;
    private final int columns;
    private final LinkedList<Element> list;

    public SparseMatrix(int rows, int columns) {
        list = new LinkedList<>();
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
        for (Element e : list) {
            if (e.row() == row && e.column() == column) {
                return e.value();
            }
        }

        return 0.0;
    }

    @Override
    protected IMatrix createInstance(int rows, int columns) {
        return new SparseMatrix(rows, columns);
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
