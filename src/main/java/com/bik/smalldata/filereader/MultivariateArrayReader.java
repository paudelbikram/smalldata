package com.bik.smalldata.filereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>DO NOT DO THIS IN REAL LIFE. USING CLASS OBJECT IS MUCH BETTER</b>
 */
public class MultivariateArrayReader implements CustomFileReader
{
    private final String inputPath;

    public MultivariateArrayReader(final String inputPath)
    {
        this.inputPath = inputPath;
    }


    /**
     * Here we will be reading file and loading its data to univariate array.
     * <b>Cons</b>
     * <p>
     *     It requires the data point to be of same type which is almost
     *     impossible in real world
     *     Passing around multidimensional array is very painful and it is difficult to apply OO on them
     *     as compare to Class Object
     * </p>
     * <b>Pros</b>
     * <p>
     *     Good for small dataset which are of same type
     *     It is easier to apply statistical and learning algorithm to multidimensional array
     * </p>
     * @return Returns arraylist of array of integer.
     */
    @Override
    public List<Integer[]> readFile()
    {
        List<Integer []> recordList = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath)))
        {
            String columnNames = br.readLine();
            String[] columns = columnNames.split(",");
            System.out.println("File Columns: "+ Arrays.toString(columns));
            //Arrays are fixed width and can not be extended once initialized
            //Can not store first name, last name, street, city and state because we are planning on
            //returning an array. So, we are only reading id and zip code.
            String line;
            while ((line = br.readLine()) != null )
            {
                Integer[] record = new Integer[2];
                System.out.println("Reading line: "+line);
                final String[] s = line.split(",");
                record[0] = Integer.parseInt(s[0]);
                record[1] = Integer.parseInt(s[7]);
                recordList.add(record);
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return recordList;
    }
}
