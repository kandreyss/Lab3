package org.suai.lab05.matrices;

import java.util.TreeMap;

public class SparseMatrixTree extends AbstractMatrix {

    private static class Element implements Comparable<Element> {
        private final int row;
        private final int column;

        private Element(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public int compareTo(Element o) {
            if (this.row != o.row) {
                return Integer.compare(this.row, o.row);
            }
            return Integer.compare(this.column, o.column);
        }
    }

    private final int rows;
    private final int columns;
    private final TreeMap<Element, Double> map;

    public SparseMatrixTree(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.map = new TreeMap<>();
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
        Double value = map.get(new Element(row, column));
        return value == null ? 0.0 : value;
    }

    @Override
    protected IMatrix createInstance(int rows, int columns) {
        return new SparseMatrixTree(rows, columns);
    }

    @Override
    public void setElement(int row, int column, double value) {
        Element key = new Element(row, column);

        if (value == 0.0) {
            map.remove(key);
        } else {
            map.put(key, value);
        }
    }
}