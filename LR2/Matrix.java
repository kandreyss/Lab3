public class Matrix {
    private int[][] matrix;

    public Matrix(int n) {
        matrix = new int[n][n];
        for(int i = 0; i < n ; i++) {
            matrix[i][i] = 1;
        }
    }    
    
    public void setElement(int row, int column, int value) {
        matrix[row][column] = value;
    }  

    public int getElement(int row, int column) {
        return matrix[row][column];
    }
    
    public Matrix sum(Matrix other) {
        Matrix result = new Matrix(matrix.length);

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) 
                result.setElement(i, j, getElement(i, j) + other.getElement(i, j));
        }

        return result;
    }

    public Matrix product(Matrix other) {
        int n = matrix.length;
        int m = matrix[0].length; 
        int p = other.matrix[0].length;

        Matrix result = new Matrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                int sum = 0;
                for (int k = 0; k < m; k++) {
                    sum += getElement(i, k) * other.getElement(k, j);
                
                result.setElement(i, j, sum);
                }
            }
        }

        return result;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int matrixSize = matrix.length;
        for(int i = 0; i < matrixSize; i++) {
            result.append("[");
            for(int j = 0; j < matrixSize; j++) {
                result.append(getElement(i, j));
                if(j != matrixSize - 1) {
                    result.append(" ");
                }
            }
            result.append("]");
            result.append("\n");
            }
        return result.toString();
    }
} 