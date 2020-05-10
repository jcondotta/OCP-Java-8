package chapter4.stream;

import chapter3.Person;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamIntermediateOperations {

    private List<String> stringList;
    private List<Integer> integerList;

    public StreamIntermediateOperations() {
        stringList = Arrays.asList("Jefferson", "Jonathan", "Aldersen", "Anderson", "Gerson");
        integerList = Arrays.asList(5,2,3,1,4,6,5,9,8,8);
    }

    private void testFilter() {
        System.out.println("testFilter");
        Stream<Integer> integerStream = integerList.stream();
        integerStream.filter(integer -> integer >= 4)
                .sorted(Comparator.naturalOrder())
                .forEach(integer -> System.out.println(integer));

        System.out.println("");
    }

    private void testDistinct() {
        System.out.println("testDistinct");
        Stream<Integer> integerStream = integerList.stream();
        integerStream.distinct()
                .sorted(Comparator.reverseOrder())
                .forEach(integer -> System.out.println(integer));

        System.out.println("");
    }

    private void testLimitSkip() {
        System.out.println("testLimitSkip");
        // integerList.stream(); // Arrays.asList(5,2,3,1,4,6,5,9,8,8);
        Stream<Integer> integerStream = integerList.stream();
        integerStream.limit(5).skip(1)
                .forEach(integer -> System.out.println(integer)); // 2,3,1,4

        System.out.println("");
        integerStream = integerList.stream();
        integerStream.skip(1).limit(5)
                .forEach(integer -> System.out.println(integer)); // 2,3,1,4,6

        System.out.println("");
        Stream.iterate(0, index -> index + 1).limit(3).forEach(System.out::println);
        System.out.println("");
    }

    private void testMap() {
        System.out.println("testMap");
        Stream<String> stringStream = stringList.stream();
        Function<String, Integer> mapLengthFunction = (value -> value.length());
        stringStream.map(mapLengthFunction).forEach(System.out::println);

        stringStream = stringList.stream();
        Function<String, Person> mapPersonFunction = (value -> new Person(value));
        stringStream.map(mapPersonFunction).forEach(System.out::println);

        System.out.println("");
    }

    private void testFlatMap() {
        System.out.println("testFlatMap");
        List<String> objects = Arrays.asList();
        //List<String> cars = Arrays.asList(null); // List with null value isn't accepted
        List<String> animals = Arrays.asList("Dog", "Cat", "Lion");
        Stream<List<String>> stringStream = Stream.of(stringList, animals, objects);
        stringStream.flatMap(l -> l.stream()).forEach(strings -> System.out.println(strings));


        System.out.println("");
    }

    private void testPeek() {
        System.out.println("testPeek");
        List<Integer> integers = new ArrayList<>();
        Stream<Integer> integerStream = integerList.stream();
        long count = integerStream
                .filter(i -> i >= 8)
                .peek(integer -> integers.add(integer)).count();
        System.out.println(integers);
        System.out.println(count + "\n");


        integerStream = Stream.of(1,5,9);
        integerStream.peek(i -> System.out.println(i)) // prints all items
                .filter(i -> i > 8) // keep only those greater than 8
                .forEach(i -> System.out.println(i)); // print those greater than 8

        System.out.println("");
        integerStream = Stream.of(1,5,9);
        integerStream
                .filter(i -> i > 8)
                .peek(i -> System.out.println(i)) // print those greater than 8
                .forEach(i -> System.out.println(i)); // print those greater than 8

        System.out.println("");
        Stream.iterate(1, x -> x + 1)
                .peek(i -> System.out.println(i))
                .limit(5)
                .filter(x -> x % 2 == 1)
                .forEach(integer -> System.out.println(integer));

        System.out.println("");
    }

    public static void main(String[] args) {
        StreamIntermediateOperations test = new StreamIntermediateOperations();
        test.testFilter();
        test.testDistinct();
        test.testLimitSkip();
        test.testMap();
        test.testFlatMap();
        test.testPeek();
    }

}
