package com.bik.smalldata.filewriter;

import com.bik.smalldata.model.Record;
import com.bik.smalldata.util.RecordUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TextFileWriter implements CustomFileWriter
{
    private final String outputPath;

    public TextFileWriter(final String outputPath)
    {
        this.outputPath = outputPath;
    }

    @Override
    public void write() {
        List<String> stringRepresentationOfRecordObjects = new ArrayList<>();
        final Record.RecordBuilder recordBuilder1 = new Record.RecordBuilder("Ram Krishna", "Dhakal").id(1L)
                .dateOfBirth(RecordUtil.parseDate("2001-01-01T10:15:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)).street("123 bato").city("Pokhara").state("2").zip(36987L);
        final Record recordObject1 = recordBuilder1.build();
        stringRepresentationOfRecordObjects.add(recordObject1.toString());

        final Record.RecordBuilder recordBuilder2 = new Record.RecordBuilder("Narayan", "Gopal").id(1L)
                .dateOfBirth(RecordUtil.parseDate("1966-01-01T10:15:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)).street("123 bato").city("Pokhara").state("2").zip(36987L);
        final Record recordObject2 = recordBuilder2.build();
        stringRepresentationOfRecordObjects.add(recordObject2.toString());

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.outputPath))) // pass true to FileWriter for appending
        {
            for(String s: stringRepresentationOfRecordObjects)
            {
                bw.write(s);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
