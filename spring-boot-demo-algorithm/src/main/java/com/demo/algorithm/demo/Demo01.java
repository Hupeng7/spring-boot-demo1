package com.demo.algorithm.demo;

/**
 * @ClassName Demo01
 * @Description
 * @Author H
 * @Date 2021/3/10 15:16
 * @Version 1.0
 */
public class Demo01 {

    public static void spiralOrderPrint(int[][] matrix) {
        int row1 = 0;
        int col1 = 0;
        int row2 = matrix.length - 1;
        int col2 = matrix[0].length - 1;
        while (row1 <= row2 && col1 <= col2) {
            printSqure(matrix, row1++, col1++, row2--, col2--);
        }
        System.out.println();
    }

    private static void printSqure(int[][] matrix, int row1, int col1, int row2, int col2) {
        int curR = row1;
        int curC = col1;
        if (row1 == row2) {
            while (curC != col2 + 1) {
                System.out.println(matrix[curR][curC++] + " ");
            }
        } else if (col1 == col2) {
            while (curR != row2 + 1) {
                System.out.println(matrix[curR++][curC] + " ");
            }
        } else {
            while (curC != col2) {
                System.out.println(matrix[curR][curC++] + " ");
            }
            while (curR != row2) {
                System.out.println(matrix[curR++][curC] + " ");
            }
            while (curC != col1) {
                System.out.println(matrix[curR][curC--] + " ");
            }
            while (curR != row1) {
                System.out.println(matrix[curR--][curC] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        spiralOrderPrint(m);

    }

}
