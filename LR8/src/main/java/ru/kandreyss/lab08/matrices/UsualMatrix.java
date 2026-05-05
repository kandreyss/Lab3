package ru.kandreyss.lab08.matrices;

public class UsualMatrix {

    final int rows;
    final int cols;
    final int[][] data;

    public UsualMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    public int get(int row, int col) {
        return data[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return cols;
    }

    public void set(int row, int col, int value) {
        data[row][col] = value;
    }

    public UsualMatrix sum(UsualMatrix other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException();
        }

        UsualMatrix result = new UsualMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j] + other.data[i][j];
            }
        }

        return result;
    }

    public UsualMatrix product(UsualMatrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException();
        }

        UsualMatrix result = new UsualMatrix(this.rows, other.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                int sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (!(o instanceof UsualMatrix)) {
            return false;
        }

        UsualMatrix other = (UsualMatrix) o;

        if (this.getRows() != other.getRows() ||
                this.getColumns() != other.getColumns()) {
            return false;
        }

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {

                if (this.get(i, j) != other.get(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(data[i][j]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}