package com.sun.base.thread.B_d;

import java.util.Random;

/**
 * Created by louis on 2014/11/11.
 */

/**
 * Auxiliary class
 */
public class MatrixMock {
    private int data[][];
    public MatrixMock(int rows,int cols,int number){
        int counter=0;
        data=new int[rows][cols];
        Random random=new Random();
        for (int i = 0; i < rows; i++) {
            for(int j=0;j<cols;j++){
                data[i][j]=random.nextInt(10);
                if (data[i][j] == number) {
                    counter++;
                }
            }

        }
        System.out.printf("Mock: There are %d occurrence of %d in generated data.\n",counter,number);
    }

    public int[] getRow(int row) {
        if ((row >= 0) && (row < data.length)) {
            return data[row];
        }
        return null;
    }
}
