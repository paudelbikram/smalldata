package com.bik.smalldata.filereader;

import com.bik.smalldata.model.Record;
import com.bik.smalldata.model.RecordJson;
import com.bik.smalldata.util.RecordUtil;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.csv.CSVParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JSONToClassObjectReader implements CustomFileReader
{

    private final String inputPath;

    public JSONToClassObjectReader(final String inputPath)
    {
        this.inputPath = inputPath;
    }

    /**
     * We are reading file that has JSON in each line. But the whole file is not JSON.
     * @return
     */
    @Override
    public List<RecordJson> readFile() {
        List<RecordJson> recordList = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath)))
        {
            String line;
            final Gson gson = new Gson();
            while ((line = br.readLine()) != null )
            {
                try
                {
                    RecordJson record = gson.fromJson(line, RecordJson.class);
                    recordList.add(record);
                }
                catch (JsonSyntaxException e)
                {
                    System.err.println("Failed to cast "+ line + " to json");
                    System.err.println(e);
                }
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
