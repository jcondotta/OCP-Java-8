package chapter5;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class TestLocalDate {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println("LocalDate.now(): " + now);
        System.out.print("+ 1 dia : ");
        System.out.println(now = now.plusDays(1));

        System.out.print("+ 1 semana : ");
        System.out.println(now = now.plusWeeks(1));

        System.out.print("+ 1 mes : ");
        System.out.println(now = now.plusMonths(1));

        System.out.print("+ 1 ano : ");
        System.out.println(now = now.plusYears(1));

        System.out.print("- 2 anos : ");
        System.out.println(now = now.minusYears(2));

        System.out.print("- 1 decada : ");
        //System.out.println(now = now.minus(1, ChronoUnit.HALF_DAYS)); Not Allowed
        System.out.println(now = now.minus(1, ChronoUnit.DECADES));

        LocalDate localDate1 = LocalDate.of(2020, Month.JUNE, 24);
        LocalDate localDate2 = LocalDate.of(2020, 6, 24);


    }
}
