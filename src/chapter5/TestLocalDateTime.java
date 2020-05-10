package chapter5;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class TestLocalDateTime {

    public static void main(String[] args) {
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, Month.JUNE, 20, 22, 45, 05, 999_999_999);

        LocalDateTime now = LocalDateTime.now();
        System.out.println("LocalDateTime.now(): " + now);

        System.out.print("+ 2 meio dia : ");
        System.out.println(now = now.plus(2, ChronoUnit.HALF_DAYS));
    }
}
