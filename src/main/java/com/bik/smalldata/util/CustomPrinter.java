package com.bik.smalldata.util;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class CustomPrinter {

    // Prints matrix in readable form
    public static void print(RealMatrix matrix) {
        final int rowDim = matrix.getRowDimension();
        final int columnDim =  matrix.getColumnDimension();
        for (int i = 0; i < rowDim; i++) {
            for (int j = 0; j < columnDim; j++) {
                System.out.println(matrix.getEntry(i,j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    // Prints vector in readable form
    public static void print(RealVector vector) {
        final int dim = vector.getDimension();
        for (int i = 0; i < dim; i++) {
            System.out.println(vector.getEntry(i));
            System.out.print(" ");
        }
        System.out.println();
    }
}
