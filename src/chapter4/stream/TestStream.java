package chapter4.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TestStream {

    public static void main(String[] args) {
        Stream<String> emptyStream = Stream.empty();
        Stream<Long> longStream = Stream.of(1L);
        Stream<Integer> integerStream = Stream.of(1,2,3);

        System.out.println(longStream.count());
        System.out.println(integerStream.count());

        List<String> stringList = Arrays.asList("1","2","3","4","5");
        System.out.println(stringList.stream().count());

        Stream.generate(Math::random).limit(10).forEach(value -> System.out.println(value));

        Stream.iterate(0, index -> index + 2).limit(15).forEach(System.out::println);

        Predicate<? super String> predicate = s -> s.length() > 3;
        //Stream.iterate("-", s -> s + s).forEach(System.out::println); // ------------

        Stream.iterate("", s -> s + "1").limit(2).forEach(System.out::println);
    }

}
