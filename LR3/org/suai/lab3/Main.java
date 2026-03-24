package org.suai.lab3;

import org.suai.lab3.matrices.Matrix;
import org.suai.lab3.matrices.SquareMatrix;
import org.suai.lab3.matrices.MirrorMatrixHor;

public class Main {
    public static void main(String[] args) {

        Matrix matrix = new Matrix(12, 8);
        SquareMatrix squareMatrix = new SquareMatrix(8);
        MirrorMatrixHor mirrorMatrix = new MirrorMatrixHor(7, 10);

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                matrix.setElement(i, j, (i * j + 2) % 9);
            }
        }

        for (int i = 0; i < squareMatrix.getRows(); i++) {
            for (int j = 0; j < squareMatrix.getColumns(); j++) {
                squareMatrix.setElement(i, j, (i + j * 2) % 7);
            }
        }

        for (int i = 0; i < mirrorMatrix.getRows(); i++) {
            for (int j = 0; j < mirrorMatrix.getColumns(); j++) {
                mirrorMatrix.setElement(i, j, (i + j * 3) % 8);
            }
        }

        System.out.printf("Matrix %dx%d:\n", matrix.getRows(), matrix.getColumns());
        System.out.println(matrix);
        System.out.printf("SquareMatrix %dx%d:\n", squareMatrix.getRows(), squareMatrix.getColumns());
        System.out.println(squareMatrix);
        System.out.printf("MirrorMatrixHor %dx%d:\n", mirrorMatrix.getRows(), mirrorMatrix.getColumns());
        System.out.println(mirrorMatrix);

        System.out.println("Test 1: Sum Matrix 8x8 + SquareMatrix 8x8");
        Matrix m1 = new Matrix(8, 8);
        SquareMatrix s1 = new SquareMatrix(8);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                m1.setElement(i, j, i + j);
                s1.setElement(i, j, (i + 1) * (j + 1));
            }
        }

        System.out.println("Matrix m1:");
        System.out.println(m1);
        System.out.println("SquareMatrix s1:");
        System.out.println(s1);

        Matrix sum1 = m1.sum(s1);
        System.out.println("Sum:");
        System.out.println(sum1);

        System.out.println("Test 2: Sum MirrorMatrixHor 6x9 + Matrix 6x9");
        MirrorMatrixHor mirror = new MirrorMatrixHor(6, 9);
        Matrix m2 = new Matrix(6, 9);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                mirror.setElement(i, j, i * 2 + j);
                m2.setElement(i, j, i + j * 2);
            }
        }

        System.out.println("MirrorMatrixHor mirror:");
        System.out.println(mirror);
        System.out.println("Matrix m2:");
        System.out.println(m2);

        Matrix sum2 = mirror.sum(m2);
        System.out.println("Sum:");
        System.out.println(sum2);

        System.out.println("Test 3: Product SquareMatrix 7x7 * MirrorMatrixHor 7x6");
        SquareMatrix s3 = new SquareMatrix(7);
        MirrorMatrixHor mirror3 = new MirrorMatrixHor(7, 6);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                s3.setElement(i, j, i + j + 1);
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                mirror3.setElement(i, j, i + j + 2);
            }
        }

        System.out.println("SquareMatrix s3:");
        System.out.println(s3);
        System.out.println("MirrorMatrixHor mirror3:");
        System.out.println(mirror3);

        Matrix product1 = s3.product(mirror3);
        System.out.println("Product:");
        System.out.println(product1);

        System.out.println("Test 4: Product Matrix 9x6 * SquareMatrix 6x6");
        Matrix m4 = new Matrix(9, 6);
        SquareMatrix s4 = new SquareMatrix(6);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 6; j++) {
                m4.setElement(i, j, i + j + 1);
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                s4.setElement(i, j, (i + 1) * (j + 2));
            }
        }

        System.out.println("Matrix m4:");
        System.out.println(m4);
        System.out.println("SquareMatrix s4:");
        System.out.println(s4);

        Matrix product2 = m4.product(s4);
        System.out.println("Product:");
        System.out.println(product2);
    }
}