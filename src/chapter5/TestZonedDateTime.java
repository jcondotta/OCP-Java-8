package chapter5;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestZonedDateTime {

    public static ZoneId madridZone = ZoneId.of("Europe/Madrid");
    public static ZoneId saoPauloZone = ZoneId.of("America/Sao_Paulo");

    public static void main(String[] args) {
        System.out.println("ZonedDateTime.now(): " + ZonedDateTime.now());

        ZoneId.getAvailableZoneIds().stream()
                .filter(s -> s.endsWith("Sao_Paulo"))
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("");

        System.out.println("Get Current DateTime in SaoPaulo");
        System.out.println(ZonedDateTime.now(saoPauloZone));

        System.out.println("Creates a DateTime using Madrid Info in SaoPauloZone");
        System.out.println(ZonedDateTime.of(LocalDate.now(), LocalTime.now(), saoPauloZone));


    }
}
