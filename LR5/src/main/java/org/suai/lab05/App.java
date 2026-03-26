package org.suai.lab05;

import java.util.Random;

import org.suai.lab05.matrices.IMatrix;
import org.suai.lab05.matrices.SparceMatrix;
import org.suai.lab05.matrices.SquareMatrix;
import org.suai.lab05.matrices.UsualMatrix;

public class App {
    public static void main(String[] args) {
        testAddition();
        testMultiplication();
        testSquareMatrices();
        testSparseMatrices();
    }
    
    private static void testAddition() {
        System.out.println("Тест 1: Сложение матриц 10x11\n");
        
        Random rand = new Random(42);
        
        System.out.println("1. UsualMatrix + UsualMatrix:");
        IMatrix um1 = createUsualMatrix(10, 11, rand);
        IMatrix um2 = createUsualMatrix(10, 11, rand);
        IMatrix umSum = um1.sum(um2);
        System.out.println(umSum);
        
        System.out.println("2. SparceMatrix + SparceMatrix:");
        IMatrix sm1 = createSparceMatrix(10, 11, rand, 0.3);
        IMatrix sm2 = createSparceMatrix(10, 11, rand, 0.3);
        IMatrix smSum = sm1.sum(sm2);
        System.out.println(smSum);
        
        System.out.println("3. UsualMatrix + SparceMatrix:");
        IMatrix mixedSum = um1.sum(sm1);
        System.out.println(mixedSum);
    }
    
    private static void testMultiplication() {
        System.out.println("Тест 2: Умножение матриц 10x11 * 11x13\n");
        
        Random rand = new Random(42);
        
        System.out.println("1. UsualMatrix(10x11) * UsualMatrix(11x13):");
        IMatrix um1 = createUsualMatrix(10, 11, rand);
        IMatrix um2 = createUsualMatrix(11, 13, rand);
        IMatrix umProduct = um1.product(um2);
        System.out.println(umProduct);
        
        System.out.println("2. SparceMatrix(10x11) * SparceMatrix(11x13):");
        IMatrix sm1 = createSparceMatrix(10, 11, rand, 0.3);
        IMatrix sm2 = createSparceMatrix(11, 13, rand, 0.3);
        IMatrix smProduct = sm1.product(sm2);
        System.out.println(smProduct);
        
        System.out.println("3. UsualMatrix(10x11) * SparceMatrix(11x13):");
        IMatrix mixedProduct = um1.product(sm2);
        System.out.println(mixedProduct);
    }
    
    private static void testSquareMatrices() {
        System.out.println("Тест 3: Квадратные матрицы\n");
        
        Random rand = new Random(42);
        
        System.out.println("1. SquareMatrix(10x10) + SquareMatrix(10x10):");
        IMatrix sq1 = createSquareMatrix(10, rand);
        IMatrix sq2 = createSquareMatrix(10, rand);
        IMatrix sqSum = sq1.sum(sq2);
        System.out.println(sqSum);
        
        System.out.println("2. SquareMatrix(7x7) * SquareMatrix(7x7):");
        IMatrix sq3 = createSquareMatrix(7, rand);
        IMatrix sq4 = createSquareMatrix(7, rand);
        IMatrix sqProduct = sq3.product(sq4);
        System.out.println(sqProduct);
        
        System.out.println("3. SquareMatrix(10x10) + UsualMatrix(10x10):");
        IMatrix sq5 = createSquareMatrix(10, rand);
        IMatrix um = createUsualMatrix(10, 10, rand);
        IMatrix mixedSum = sq5.sum(um);
        System.out.println(mixedSum);
    }
    
    private static void testSparseMatrices() {
        System.out.println("Тест 4: Разреженные матрицы\n");
        
        Random rand = new Random(42);
        
        System.out.println("1. SparceMatrix(13x7) + SparceMatrix(13x7):");
        IMatrix sparse1 = createSparceMatrix(13, 7, rand, 0.05);
        IMatrix sparse2 = createSparceMatrix(13, 7, rand, 0.05);
        IMatrix sparseSum = sparse1.sum(sparse2);
        System.out.println(sparseSum);
        
        System.out.println("2. SparceMatrix(13x7) * UsualMatrix(7x11):");
        IMatrix sparse = createSparceMatrix(13, 7, rand, 0.1);
        IMatrix usual = createUsualMatrix(7, 11, rand);
        IMatrix mixedProduct = sparse.product(usual);
        System.out.println(mixedProduct);
        
        System.out.println("3. SparceMatrix(10x11) * SparceMatrix(11x7):");
        IMatrix sparse3 = createSparceMatrix(10, 11, rand, 0.2);
        IMatrix sparse4 = createSparceMatrix(11, 7, rand, 0.2);
        IMatrix sparseProduct = sparse3.product(sparse4);
        System.out.println(sparseProduct);
    }
    
    private static IMatrix createUsualMatrix(int rows, int cols, Random rand) {
        IMatrix matrix = new UsualMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.setElement(i, j, rand.nextInt(100));
            }
        }
        return matrix;
    }
    
    private static IMatrix createSquareMatrix(int size, Random rand) {
        IMatrix matrix = new SquareMatrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix.setElement(i, j, rand.nextInt(100));
            }
        }
        return matrix;
    }
    
    private static IMatrix createSparceMatrix(int rows, int cols, Random rand, double density) {
        IMatrix matrix = new SparceMatrix(rows, cols);
        int elements = (int)(rows * cols * density);
        for (int n = 0; n < elements; n++) {
            int i = rand.nextInt(rows);
            int j = rand.nextInt(cols);
            matrix.setElement(i, j, rand.nextInt(100));
        }
        return matrix;
    }
}
