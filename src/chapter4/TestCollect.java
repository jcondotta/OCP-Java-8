package chapter4;

import chapter3.Person;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCollect {

    List<String> stringList = Person.getDefaultPersonNameList();
    List<Integer> integerList = Arrays.asList(1,10,100);
    List<Double> doubleList = Arrays.asList(1.0,10.0,100.0);
    List<Long> longList = Arrays.asList(1L,10L,100L);

    public void testAveragingDouble(){
        System.out.println("testAveragingDouble");
        ToDoubleFunction<String> toDoubleFunction = (value -> value.length());
        System.out.println(stringList.stream().collect(Collectors.averagingDouble(toDoubleFunction)));
    }

    public void testAveragingInt(){
        System.out.println("testAveragingInt");
        ToIntFunction<String> toIntFunction = String::length;
        System.out.println(stringList.stream().collect(Collectors.averagingInt(toIntFunction)));
    }

    public void testAveragingLong(){
        System.out.println("testAveragingLong");
        ToLongFunction<String> toLongFunction = String::length;
        System.out.println(stringList.stream().collect(Collectors.averagingLong(toLongFunction)));
    }

    public void testCounting(){
        System.out.println("testCounting");
        System.out.println(stringList.stream().collect(Collectors.counting()));
    }

    public void testGrouping(){
        System.out.println("testGrouping");
        Function<Person, String> function = (p -> p.getNationality());
        Map<String, List<Person>> map = Person.buildDefaultPersonList().stream().collect(Collectors.groupingBy(function));
        System.out.println(map);
    }

    public void testGroupingWithCollector(){
        System.out.println("testGroupingWithCollector");
        Function<String, Integer> function = String::length;
        Map<Integer, Set<String>> map = stringList.stream().collect(Collectors.groupingBy(function, Collectors.toSet()));
        System.out.println(map);
    }

    public void testGroupingWithCollectorAndSupplier(){
        System.out.println("testGroupingWithCollectorAndSupplier");
        Function<String, Integer> function = (s -> s.length());
        TreeMap<Integer, Set<String>> map = stringList.stream().collect(Collectors.groupingBy(function, TreeMap::new, Collectors.toSet()));
        System.out.println(map);
    }

    public void testJoining(){
        System.out.println("testJoining");
        System.out.println(stringList.stream().collect(Collectors.joining()));
    }

    public void testJoiningWithParam(){
        System.out.println("testJoiningWithParam");
        System.out.println(stringList.stream().collect(Collectors.joining(", ")));
    }

    public void testMaxBy(){
        System.out.println("testMaxBy");
        Optional<String> optional = stringList.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
        optional.ifPresent(System.out::println);
    }

    public void testMinBy(){
        System.out.println("testMinBy");
        Optional<String> optional = stringList.stream().collect(Collectors.minBy(Comparator.naturalOrder()));
        optional.ifPresent(System.out::println);
    }

    public void testMapping(){
        System.out.println("testMapping");
//        Stream<String> stringStream = Person.getDefaultPersonNameList().stream();

//        Map<Integer, Optional<Character>> collect = stringStream.collect(
//                Collectors.groupingBy(String::length,
//                        Collectors.mapping(s -> s.charAt(0), Collectors.minBy(Comparator.naturalOrder()))));
//        System.out.println(collect);
    }

    public void testPartitioning(){
        System.out.println("testPartitioning");
        Predicate<Person> predicate = (p -> p.getNationality().equals("Brazilian"));
        Map<Boolean, List<Person>> mapAreBrazilians = Person.buildDefaultPersonList().stream().collect(Collectors.partitioningBy(predicate));
        System.out.println(mapAreBrazilians);
    }

    public void testPartitioningWithCollector(){
        System.out.println("testPartitioningWithCollector");
        Predicate<Integer> predicate = (i -> i > 99);
        Map<Boolean, Set<Integer>> mapGreaterThan100 = integerList.stream().collect(Collectors.partitioningBy(predicate, Collectors.toSet()));
        System.out.println(mapGreaterThan100);
    }

    public void testSummarizingDouble(){
        System.out.println("testSummarizingDouble");
        ToDoubleFunction<String> toDoubleFunction = s -> Double.valueOf(s);
        DoubleSummaryStatistics collect = Stream.of("1", "10", "100").collect(Collectors.summarizingDouble(toDoubleFunction));
        System.out.println(collect.getAverage());
    }

    public void testSummarizingInt(){
        System.out.println("testSummarizingInt");
        ToIntFunction<String> toIntFunction = s -> Integer.valueOf(s);
        IntSummaryStatistics collect = Stream.of("1", "10", "100").collect(Collectors.summarizingInt(toIntFunction));
        System.out.println(collect.getMax());
    }

    public void testSummarizingLong(){
        System.out.println("testSummarizingLong");
        ToLongFunction<String> toLongFunction = s -> Long.valueOf(s);
        LongSummaryStatistics collect = Stream.of("1", "10", "100").collect(Collectors.summarizingLong(toLongFunction));
        System.out.println(collect.getSum());
    }

    public void testSummingDouble(){
        System.out.println("testSummingDouble");
        ToDoubleFunction<String> toDoubleFunction = s -> Double.valueOf(s);
        Double doubleSum = Stream.of("1", "10", "100").collect(Collectors.summingDouble(toDoubleFunction));
        System.out.println(doubleSum);
    }

    public void testSummingInt(){
        System.out.println("testSummingInt");
        ToIntFunction<String> toIntFunction = s -> Integer.valueOf(s);
        Integer integerSum = Stream.of("1", "10", "100").collect(Collectors.summingInt(toIntFunction));
        System.out.println(integerSum);
    }

    public void testSummingLong(){
        System.out.println("testSummingLong");
        ToLongFunction<String> toLongFunction = s -> Long.valueOf(s);
        Long longSum = Stream.of("1", "10", "100").collect(Collectors.summingLong(toLongFunction));
        System.out.println(longSum);
    }

    public void testToList(){
        System.out.println("testToList");
        List<String> stringAnyList = stringList.stream().collect(Collectors.toList());
        System.out.println(stringAnyList);
    }

    public void testToSet(){
        System.out.println("testToSet");
        Set<String> set = stringList.stream().collect(Collectors.toSet());
        System.out.println(set);
    }

    public void testToCollection(){
        System.out.println("testToCollection");
        LinkedList<String> stringLinkedList = stringList.stream().collect(Collectors.toCollection(LinkedList::new));
        TreeSet<String> stringTreeSet = stringList.stream().collect(Collectors.toCollection(TreeSet::new));

        System.out.println(stringLinkedList);
        System.out.println(stringTreeSet);
    }

    public void testToMap(){
        System.out.println("testToMap");
        Stream<Person> personStream = Person.buildDefaultPersonList().stream();

        Function<Person, String> keyfunction = (p -> p.getName());
        Function<Person, String> valuefunction = (p -> p.getNationality());
        Map<String, String> map = personStream.collect(Collectors.toMap(keyfunction, valuefunction));
        System.out.println(map);
    }

    public void testToMapWithBinaryOperator(){
        System.out.println("testToMapWithBinaryOperator");
        Stream<Person> personStream = Person.buildDefaultPersonList().stream();

        Function<Person, String> keyfunction = p -> p.getNationality();
        Function<Person, String> valuefunction = p -> p.getName();
        BinaryOperator<String> mergeOperator = (v1, v2) -> v1 + ", " + v2;

        Map<String, String> map = personStream.collect(Collectors.toMap(keyfunction, valuefunction, mergeOperator));
        System.out.println(map);
    }

    public void testToMapWithBinaryOperatorAndSupplier(){
        System.out.println("testToMapWithBinaryOperatorAndSupplier");
        Stream<Person> personStream = Person.buildDefaultPersonList().stream();

        BinaryOperator<String> mergeOperator = (v1, v2) -> v1 + ", " + v2;

        TreeMap<String, String> map = personStream.collect(Collectors.toMap(Person::getNationality, Person::getName, mergeOperator, TreeMap::new));
        System.out.println(map);
    }

    public static void main(String[] args) {
        TestCollect testCollect = new TestCollect();
        testCollect.testAveragingDouble();
        testCollect.testAveragingInt();
        testCollect.testAveragingLong();
        testCollect.testCounting();
        testCollect.testGrouping();
        testCollect.testGroupingWithCollector();
        testCollect.testGroupingWithCollectorAndSupplier();
        testCollect.testJoining();
        testCollect.testJoiningWithParam();
        testCollect.testMaxBy();
        testCollect.testMinBy();
        testCollect.testMapping();
        testCollect.testPartitioning();
        testCollect.testPartitioningWithCollector();
        testCollect.testSummarizingDouble();
        testCollect.testSummarizingInt();
        testCollect.testSummarizingLong();
        testCollect.testSummingDouble();
        testCollect.testSummingInt();
        testCollect.testSummingLong();
        testCollect.testToList();
        testCollect.testToSet();
        testCollect.testToCollection();
        testCollect.testToMap();
        testCollect.testToMapWithBinaryOperator();
        testCollect.testToMapWithBinaryOperatorAndSupplier();
    }
}
