package chapter4.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTerminalOperations {

    private Random random;
    private List<String> stringList;
    private List<Integer> integerList;

    public StreamTerminalOperations() {
        random = new Random();
        stringList = Arrays.asList("Jefferson", "Jonathan", "Aldersen", "Anderson", "Gerson");
        integerList = Arrays.asList(5,2,3,1,4);
    }

    private void testCount(){
        /* Count necessita contar todos items no stream, desde que esse stream e infinito,
            se torna impossivel retornar um valor, (loop infinito)
         */
//        Stream<Integer> stream = Stream.generate(() -> random.nextInt());
//        System.out.println(stream.count());

        Stream<Integer> stream = integerList.stream();
        System.out.println("Total count items is: " + stream.count());
        System.out.println("");
    }

    private void testMinAndMax(){
        Stream<Integer> emptyStream = Stream.of(17, 20, 988, -4);
        Optional<Integer> min = emptyStream.min((v1, v2) -> v1 - v2);
        min.ifPresent(s -> System.out.println("Min value is: " +s));

        Stream<Integer> stream = integerList.stream();
        Optional<Integer> max = stream.max(Comparator.naturalOrder());
        max.ifPresent(s -> System.out.println("Max value is: " +s));
        System.out.println("");
    }

    private void testFindAnyFindFirst(){
        Stream<String> stream = stringList.stream();
        stream.findAny().ifPresent(s -> System.out.println(s));

        stream = stringList.stream();
        stream.findFirst().ifPresent(s -> System.out.println(s));
        System.out.println("");
    }

    private void testAllAnyNoneMatch(){
        Stream<String> stream = stringList.stream();
        System.out.println("All Ends With 'n': " + stream.allMatch(s -> s.endsWith("n")));

        stream = stringList.stream();
        System.out.println("All Ends With 'n': " + stream.allMatch(s -> s.endsWith("son")));

        stream = stringList.stream();
        System.out.println("Any Starts With 'J': " + stream.anyMatch(s -> s.startsWith("J")));

        stream = stringList.stream();
        System.out.println("Any Starts With 'L': " + stream.anyMatch(s -> s.startsWith("L")));

        stream = stringList.stream();
        System.out.println("None Contains With 'z': " + stream.anyMatch(s -> s.contains("z")));
        System.out.println("");
    }

    private void testReduce(){
        Stream<String> stringStream = stringList.stream();
        String total1 = stringStream.reduce("", String::concat);
        System.out.println(total1);

        stringStream = stringList.stream();
        Optional<String> concatened = stringStream.reduce((v1, v2) -> v1 + v2);
        concatened.ifPresent(s -> System.out.println(s));

        Stream<Integer> integerStream = integerList.stream();
        Integer total3 = integerStream.reduce(0, (v1, v2) -> v1 + v2);
        System.out.println(total3);

        integerStream = integerList.stream();
        Optional<Integer> total4 = integerStream.reduce(Integer::sum);
        total4.ifPresent(integer -> System.out.println(integer));

        integerStream = integerList.stream();
        System.out.println(integerStream.reduce(1, (a,b) -> a * b));
    }

    private void testCollect(){
        Stream<String> stringStream = stringList.stream();
        StringBuilder collect1 = stringStream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println(collect1);

        Stream<Integer> integerStream = integerList.stream();
        List<Integer> total4 = integerStream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(total4);

        integerStream = integerList.stream();
        TreeSet<Integer> total5 = integerStream.collect(Collectors.toCollection(TreeSet::new)); // Predefined Collector
        System.out.println(total5);

        integerStream = integerList.stream();
        Set<Integer> total6 = integerStream.collect(Collectors.toSet()); // Predefined Collector
        System.out.println(total6);
    }

    public static void main(String[] args) {
        StreamTerminalOperations stream = new StreamTerminalOperations();
        stream.testCount();
        stream.testMinAndMax();
        stream.testFindAnyFindFirst();
        stream.testAllAnyNoneMatch();
        stream.testReduce();
        stream.testCollect();
    }
}
