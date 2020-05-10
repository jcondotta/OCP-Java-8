package chapter5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class TestPeriod {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();

        Period period = Period.of(1, 1, 1).plusDays(3); // Somente o ultimo eh adicionado.
        System.out.println(date.plus(period));

        System.out.println(Period.parse("P2Y").plusDays(2).plusDays(2));
        System.out.println(Period.ZERO);
        System.out.println(Period.of(1, 0,3));
        System.out.println(Period.ofWeeks(3));
        System.out.println(Period.ofMonths(2).ofMonths(3)); // Method "of" can't be chained.

        //LocalTime.now().plus(Period.ofDays(1)); // LocalTime can't use Period

        System.out.println(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(3))); // 91
    }
}
