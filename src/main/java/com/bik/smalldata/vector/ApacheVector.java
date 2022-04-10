package com.bik.smalldata.vector;

import com.bik.smalldata.util.CustomPrinter;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.OpenMapRealVector;
import org.apache.commons.math3.linear.RealVector;
/*
Demonstrating apache common implementation of Matrix
 */
public class ApacheVector {

    private RealVector vectorWithSize;
    private RealVector vectorWithArray;
    private RealVector vectorWithDeepCopy;
    private RealVector vectorWithDefault;
    private RealVector sparseVector;


    public void demo() {
        init();
        gettingAndSetting();
    }

    public void init() {

        // Initializing Vector with defined size
        final int size = 10;
        this.vectorWithSize = new ArrayRealVector(size);

        //Initializing Vector with array
        double[] data = {2.5, 3.5, 1.0, 3.0};
        this.vectorWithArray = new ArrayRealVector(data);

        //Initializing Vector with deep copy of another vector
        this.vectorWithDeepCopy = new ArrayRealVector(vectorWithArray);

        //Initializing Vector with default value
        double defaultValue = 5.0;
        this.vectorWithDefault = new ArrayRealVector(size, defaultValue);

        /*
        Map Storage: When a large vector is almost zeros, it is called sparse. In this case,
        it makes sense to just store non-zero elements (could be implemented using HashMap)
         */
        int sparseVectorSize = 50000;
        this.sparseVector = new OpenMapRealVector(sparseVectorSize);
    }


    public void gettingAndSetting() {
        System.out.println("Printing vector create from array");
        CustomPrinter.print(vectorWithArray);

        vectorWithArray.setEntry(0, 10);
        System.out.println("After setting first element to 10");
        CustomPrinter.print(vectorWithArray);

        System.out.println("Printing vector with size");
        CustomPrinter.print(vectorWithSize);
        vectorWithSize.set(50);
        System.out.println("After setting all elements to 50");
        CustomPrinter.print(vectorWithSize);
    }
}
