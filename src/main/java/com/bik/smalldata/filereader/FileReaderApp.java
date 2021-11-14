package com.bik.smalldata.filereader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileReaderApp implements Runnable{

    private static final String DATA_FOLDER = "./data";
    private static final String CSV_FILE =  "text-data/student-data.txt";
    private static final String JSON_FILE = "json-data/student-data.txt";
    private static final String IMG_FILE = "image-data/House_In_Nepal.jpeg";

    @Override
    public void run() {
        List<CustomFileReader> readers = new ArrayList<>();
        final String csvFile = DATA_FOLDER + File.separator + CSV_FILE;
        final String jsonFile = DATA_FOLDER + File.separator + JSON_FILE;
        final String imgFile = DATA_FOLDER + File.separator + IMG_FILE;
        CustomFileReader univariateArrayReader = new UnivariateArrayReader(csvFile);
        CustomFileReader multivariateArrayReader = new MultivariateArrayReader(csvFile);
        CSVToClassObjectReader csvToClassObjectReader = new CSVToClassObjectReader(csvFile);
        JSONToClassObjectReader jsonToClassObjectReader = new JSONToClassObjectReader(jsonFile);
        ImageFileReader imageFileReader = new ImageFileReader(imgFile);
        readers.add(univariateArrayReader);
        readers.add(multivariateArrayReader);
        readers.add(csvToClassObjectReader);
        readers.add(jsonToClassObjectReader);
        readers.add(imageFileReader);
        for (CustomFileReader reader : readers)
        {
            final String className = reader.getClass().getSimpleName();
            System.out.println("Reading file using "+ className + "class");
            final List<?> recordList = reader.readFile();
            System.out.println("RecordList ready by "+ className + " class is: ");
            printList(recordList);
            System.out.println("___________________________________________________");
        }
    }


    public static void printList(List<?> items)
    {
        System.out.println("[");
        for (Object item : items)
        {
            System.out.println(item.toString());
        }
        System.out.println("]");
    }
}
