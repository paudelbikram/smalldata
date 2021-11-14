package com.bik.smalldata.filereader;

import com.bik.smalldata.model.Record;
import com.bik.smalldata.util.RecordUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVToClassObjectReader implements CustomFileReader
{
    private final String inputPath;

    public CSVToClassObjectReader(final String inputPath)
    {
        this.inputPath = inputPath;
    }

    /**
     * Here we will also be using Apache Commons CSV parser because sometimes we will run into cases
     * where field contains commas. For exp: city and state mixed, some input from user. To parse these
     * we need regex. So, it will be better to use well adopted library. On top of that, Apache Commons CSV
     * Library has few other formats like EXCEL and MYSQL.
     * You can also return map instead of list if you have to do some kind of quick look up.
     * @return List of {@link com.bik.smalldata.model.Record}.
     */
    @Override
    public List<Record> readFile() {
        List<Record> recordList = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath)))
        {
            br.readLine(); //Skip the first line because it is header
            String line;
            while ((line = br.readLine()) != null )
            {
                System.out.println("Reading line: "+line);
                final CSVParser parser = CSVParser.parse(line, CSVFormat.DEFAULT);
                for (CSVRecord record: parser)
                {
                    Long id = RecordUtil.parseLong(record.get(0));
                    String firstName = record.get(1);
                    String lastName = record.get(2);
                    OffsetDateTime dateOfBirth = RecordUtil.parseDate(record.get(3),
                            DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    String street = record.get(4);
                    String city = record.get(5);
                    String state = record.get(6);
                    Long zip = RecordUtil.parseLong(record.get(7));
                    if (dateOfBirth != null && RecordUtil.isDateOfBirthValid(dateOfBirth) && RecordUtil.isCityValid(city))
                    {
                        final Record.RecordBuilder recordBuilder = new Record.RecordBuilder(firstName, lastName).id(id)
                                .dateOfBirth(dateOfBirth).street(street).city(city).state(state).zip(zip);
                        final Record recordObject = recordBuilder.build();
                        recordList.add(recordObject);
                    }
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
