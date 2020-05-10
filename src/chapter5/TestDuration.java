package chapter5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

public class TestDuration {

    public static void main(String[] args) {
        System.out.println(Duration.ZERO); // PT0S
        System.out.println(Duration.of(1, ChronoUnit.HALF_DAYS)); //PT12H
        System.out.println(Duration.ofHours(12).plusMinutes(30)); // PT12H30M
        System.out.println(Duration.parse("PT06H45M12S").plus(1, ChronoUnit.NANOS)); // PT6H46M12S
        System.out.println(Duration.ofSeconds(60)); // PT1M
        System.out.println(Duration.ofMinutes(60)); // PT1H

        System.out.println(ChronoUnit.HOURS.between(LocalTime.NOON, LocalTime.MIDNIGHT)); // -12

        System.out.println(LocalDateTime.now().plus(1, ChronoUnit.HALF_DAYS).plus(1, ChronoUnit.MONTHS));

        List<TemporalUnit> units = Period.of(10, 0, 10).getUnits();
        System.out.println(units);
    }
}
