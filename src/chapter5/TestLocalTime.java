package chapter5;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TestLocalTime {

    public static void main(String[] args) {
        LocalTime localTime1 = LocalTime.of(20, 57, 20, 999_999_999);

        LocalTime now = LocalTime.now();
        System.out.println("LocalTime.now(): " + now);

        System.out.print("+ 1 hora : ");
        System.out.println(now = now.plusHours(1));

        System.out.print("+ 1 minuto : ");
        System.out.println(now = now.plusMinutes(1));

        System.out.print("- 30 segundo : ");
        System.out.println(now = now.minusSeconds(30));

        System.out.print("- 1000 nano : ");
        System.out.println(now = now.minus(1000, ChronoUnit.NANOS));

        System.out.print("+ meio dia : ");
        System.out.println(now = now.plus(1, ChronoUnit.HALF_DAYS));
    }
}
