package chapter5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class TestFormattingDateTime {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("---------------- Formatting --------------------");

        System.out.println("ISO_LOCAL_DATE x Date: " + localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        //System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_TIME)); // UnsupportedTemporalTypeException: Unsupported field: HourOfDay
        //System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // UnsupportedTemporalTypeException: Unsupported field: HourOfDay


        System.out.println("ISO_LOCAL_TIME x Time: " + localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        //System.out.println(localTime.format(DateTimeFormatter.ISO_LOCAL_DATE)); // UnsupportedTemporalTypeException: Unsupported field: Year
        //System.out.println(localTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); //UnsupportedTemporalTypeException: Unsupported field: Year


        System.out.println("ISO_LOCAL_DATE_TIME x DateTime: " + localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println("ISO_LOCAL_DATE x DateTime: " + localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("ISO_LOCAL_TIME x DateTime: " + localDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME));

        System.out.println("");

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
            DateTimeFormatter mediumDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
            DateTimeFormatter mediumTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
            DateTimeFormatter mediumDateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
//
            System.out.println("\n Formatting LocalDate ");
            System.out.println(localDate.format(dateFormatter));
            System.out.println(localDate.format(mediumDateFormatter));
//
            System.out.println("\n Formatting LocalTime ");
            System.out.println(localTime.format(timeFormatter));
            System.out.println(localTime.format(mediumTimeFormatter));
//
            System.out.println("\n Formatting LocalDatetime ");
            System.out.println(localDateTime.format(dateTimeFormatter));
            System.out.println(mediumDateTimeFormatter.format(localDateTime));
    }
}
