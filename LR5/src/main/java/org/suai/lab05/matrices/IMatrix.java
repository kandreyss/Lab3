package org.suai.lab05.matrices;

public interface IMatrix {
    int getRows();
    int getColumns();
    double getElement(int row, int column);
    void setElement(int row, int column, double value);
    IMatrix sum(IMatrix other);
    IMatrix product(IMatrix other);
}