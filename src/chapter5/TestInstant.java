package chapter5;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class TestInstant {

    public static void main(String[] args) throws InterruptedException {
        ZonedDateTime zonedDateTimeSP = ZonedDateTime.now(ZoneId.of("Europe/Madrid"));

        System.out.println(zonedDateTimeSP);
        System.out.println(zonedDateTimeSP.toInstant()); // GMT Time, sem timezone

        Long epochSeconds = ZonedDateTime.now().toInstant().getEpochSecond();
        Instant start = Instant.ofEpochSecond(epochSeconds);
        Thread.sleep(1000);
        Instant finished = Instant.now();

        Duration duration = Duration.between(start, finished);
        System.out.println(duration);

        // you can math data with day unit or smaller
        System.out.println(finished.plus(Period.ofDays(365)));
        System.out.println(finished.plus(Period.ofWeeks(3)));
        //System.out.println(finished.plus(Period.ofMonths(1))); UnsupportedTemporalTypeException
        //System.out.println(finished.plus(Period.ofYears(1))); UnsupportedTemporalTypeException

        System.out.println(finished.plus(Duration.ofDays(365)));
        System.out.println(finished.plus(Duration.ofHours(504)));

        //System.out.println(finished.plus(2, ChronoUnit.WEEKS)); UnsupportedTemporalTypeException

    }
}
