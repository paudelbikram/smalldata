package com.bik.smalldata.matrix;

import com.bik.smalldata.util.CustomPrinter;
import com.bik.smalldata.util.RandomMatrix;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.linear.*;

/*
Demonstrating apache common implementation of Matrix
 */
public class ApacheMatrix {

    // There are many ways to create matrix
    private RealMatrix matrixWithDefinedSize;
    private RealMatrix matrixWith2DArray;
    private RealMatrix defaultMatrix;
    private RealMatrix matrixWithDeepCopy;

    private RealMatrix blockMatrix;
    private RealMatrix sparseMatrix;



    public void demo() {
        init();
        gettingAndSetting();
        createSubMatrices();
    }


    public void init() {

        /*
        Array Storage
         */
        // Initializing Matrix with defined size
        final int rowSize = 10;
        final int columnSize = 15;
        this.matrixWithDefinedSize = new Array2DRowRealMatrix(rowSize, columnSize);

        // Initializing Matrix with 2d array of doubles
        double[][] data = {{1.0, 1.2, 1.4, 1.7}, {2.0, 2.2, 2.4, 2.7}, {3.0, 3.2, 3.4,3.7}};
        this.matrixWith2DArray = new Array2DRowRealMatrix(data);

        //Initializing matrix sets all of its elements to 0
        double defaultValue = 3.0;
        this.defaultMatrix = new Array2DRowRealMatrix(rowSize, columnSize);
        defaultMatrix.scalarAdd(defaultValue);

        //Initializing Matrix with deep copy of another matrix
        this.matrixWithDeepCopy = defaultMatrix.copy();

        /*
        Block Storage: For large matrices with dimensions greater than 50, it is recommended to
        use block storage with BlockRealMatrix class. BlockRealMatrix is divides matrices in smaller
        block of data that are easy to cache and work on. BlockRealMatrix has similar API as
        Array2DRowRealMatrix
         */
        //Initializing BLockMatrix with size
        this.blockMatrix = new BlockRealMatrix(100, 100);

        /*
        MapVector: When a large matrix is almost zeros, it is called sparse. In this case,
        it makes sense to just store non-zero elements (could be implemented using HashMap)
         */
        int sparseMatrixRow = 50_000;
        int sparseMatrixColumn = 60_000;
        this.sparseMatrix = new OpenMapRealMatrix(sparseMatrixRow, sparseMatrixColumn);
    }


    public void gettingAndSetting() {
        System.out.println("Printing matrix with 2D array");
        CustomPrinter.print(matrixWith2DArray);

        matrixWith2DArray.setEntry(0,0, 333);
        System.out.println("After setting first element to 333");
        CustomPrinter.print(matrixWith2DArray);

        System.out.println("Printing matrix with Defined Size");
        CustomPrinter.print(matrixWithDefinedSize);
        matrixWithDefinedSize.scalarAdd(5);
        System.out.println("After adding 5");
        CustomPrinter.print(matrixWithDefinedSize);
    }


    public void createSubMatrices() {
        System.out.println("Original Matrix");
        CustomPrinter.print(matrixWithDefinedSize);

        System.out.println("SubMatrix");
        CustomPrinter.print(matrixWithDefinedSize.getSubMatrix(0, 3, 2, 4));

        System.out.println("Original Matrix");
        CustomPrinter.print(matrixWith2DArray);

        System.out.println("SubMatrix");
        CustomPrinter.print(matrixWith2DArray.getSubMatrix(new int[]{0, 2}, new int[]{3, 5}));
    }


    public void gettingRandomMatrix() {
        int numRows = 5;
        int numCols = 6;
        long seed = 0L;

        RandomMatrix rndMatrix = new RandomMatrix(new NormalDistribution(0.0, 0.5), seed);
        RealMatrix matrix = rndMatrix.getMatrix(numRows, numCols);

    }
}
