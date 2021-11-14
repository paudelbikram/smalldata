package com.bik.smalldata.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class RecordUtil
{
    //Sometimes there is not very easy way to represent absence of data. So, following terms might be used.
    private static final List<String> NULL_VALUES  = Arrays.asList("NULL", "NA","N/A", "EMPTY", "");
    private static final List<String> INVALID_CITIES = Arrays.asList("NORMAN");
    private static final OffsetDateTime MIN_DATE_OF_BIRTH = OffsetDateTime.parse("1990-01-01T00:00:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    private static final OffsetDateTime MAX_DATE_OF_BIRTH = OffsetDateTime.parse("2000-01-01T00:00:00+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    private static final boolean minLongInclusive = true;
    private static final boolean maxLongInclusive = false;
    private static final long maxLongValue = 1000L;
    private static final long minLongValue = 0L;


    public static boolean isNull(final String s)
    {
        return s == null || NULL_VALUES.contains(s.toUpperCase().trim());
    }

    private static boolean isBlank(final String s)
    {
        return s == null || s.isBlank();
    }

    //When casting string to other type, always use Wrapper class because often times these wrapper class
    //always comes with the function that will parse string.
    public static Long parseLong(final String s)
    {
        try
        {
            return Long.parseLong(s);
        }
        catch(NumberFormatException e)
        {
            System.err.println("Failed to cast "+ s + " to long");
            System.err.println(e);
        }
        //can not set null to long value, and it has default value of 1
        return null;
    }

    /**
     * This method works for any SimpleDateFormat and gives you date object back.
     * It is useful when there is no Offset in your date string.
     * @param d
     * @param format
     * @return
     */
    public static Date parseDate(final String d, final SimpleDateFormat format)
    {
        try
        {
            Date date = format.parse(d);
            return date;
        }
        catch (ParseException e)
        {
            System.err.println("Failed to cast "+ d + " to Date");
            System.err.println(e);
        }
        return null;
    }

    public static OffsetDateTime parseDate(final String s, final DateTimeFormatter dateTimeFormatter)
    {
        try
        {
            OffsetDateTime date = OffsetDateTime.parse(s, dateTimeFormatter);
            return date;
        }
        catch (DateTimeParseException e)
        {
            System.err.println("Failed to cast "+ s + " to OffsetDateTime");
            System.err.println(e);
        }
        return null; //can set null to OffsetDateTime
    }

    //outliers
    public static boolean isIdValid(final long l)
    {
        return (minLongInclusive ? l >= minLongValue : l > minLongValue)
                && (maxLongInclusive ? l <= maxLongValue : l < maxLongValue);
    }

    public static boolean isCityValid(final String city)
    {
        return !INVALID_CITIES.contains(city.trim().toUpperCase());
    }

    //isAfter and isBefore are exclusive
    public static boolean isDateOfBirthValid(final OffsetDateTime dateOfBirth)
    {
        return dateOfBirth.isAfter(MIN_DATE_OF_BIRTH) && dateOfBirth.isBefore(MAX_DATE_OF_BIRTH);
    }

}
