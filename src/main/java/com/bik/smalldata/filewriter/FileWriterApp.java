package com.bik.smalldata.filewriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileWriterApp implements Runnable
{
    private static final String DATA_FOLDER = "./data";
    private static final String TXT_FILE = "text-data/Text_OutPut.txt";
    private static final String DEST_IMG_FILE = "image-data/House_in_Nepal_OutPut.png";
    private static final String SOURCE_IMG_FILE = "image-data/House_in_Nepal.jpeg";

    @Override
    public void run()
    {
        List<CustomFileWriter> writers = new ArrayList<>();
        final String txtFile = DATA_FOLDER + File.separator + TXT_FILE;
        final String imgFile = DATA_FOLDER + File.separator + DEST_IMG_FILE;
        final String sourceImgFile = DATA_FOLDER + File.separator + SOURCE_IMG_FILE;
        CustomFileWriter textFileWriter = new TextFileWriter(txtFile);
        CustomFileWriter imgFileWriter = new ImageFileWriter(imgFile, sourceImgFile);
        writers.add(textFileWriter);
        writers.add(imgFileWriter);
        for (CustomFileWriter writer : writers)
        {
            final String className = writer.getClass().getSimpleName();
            System.out.println("Writing file using "+ className + "class");
            writer.write();
            System.out.println("___________________________________________________");
        }
    }
}
