package chapter5;

import java.time.*;

public class TestDaylightSaving {

    public static void main(String[] args) {
        ZoneId usEasternZone = ZoneId.of("US/Eastern");
        LocalDate date = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime time = LocalTime.of(1, 30);


        ZonedDateTime zonedDateTime = ZonedDateTime.of(date, time, usEasternZone);
        ZonedDateTime zonedDateTime2 = zonedDateTime.plusHours(1);
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime2);

        System.out.println(Duration.between(zonedDateTime, zonedDateTime2));

        System.out.println("");

        date = LocalDate.of(2016, Month.NOVEMBER, 6);
        time = LocalTime.of(1, 30);

        zonedDateTime = ZonedDateTime.of(date, time, usEasternZone);
        zonedDateTime2 = zonedDateTime.plusHours(1);

        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime2);

        System.out.println(Duration.between(zonedDateTime, zonedDateTime2));
    }


}
