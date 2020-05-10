package chapter4;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ChainingOptional {

    public static void main(String[] args) {
        Map<String, Integer> myMap = new HashMap<>();
        Stream.of(1,10,100,1000).forEach(value ->
                Optional.of(value).filter(s -> s > 99).ifPresent(s -> System.out.println(s))
        );
    }
}
