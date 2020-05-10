package chapter4.primitiveStream;

import chapter3.Person;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestIntStream {

    public void testIntStreamMethods(){
        // (1 - 0, 2 - 10, 3 - 20)
        IntStream.iterate(0, d -> d + 10).limit(3).average().ifPresent(System.out::println);

        IntStream.iterate(0, d -> d + 10).limit(3).min().ifPresent(System.out::println); // 0
        IntStream.iterate(0, d -> d + 10).limit(3).max().ifPresent(System.out::println); // 20
        System.out.println(IntStream.iterate(0, d -> d + 10).limit(3).count()); // (3) - 0, 10, 20
        System.out.println(IntStream.iterate(0, d -> d + 10).limit(3).sum()); // 30

        System.out.println("");
        IntStream.range(1, 4).forEach(System.out::println);
        System.out.println("");
        IntStream.rangeClosed(1, 3).max().ifPresent(System.out::println);
    }

    public void testIntFunctionMapIntToString(){
        IntStream intStream = IntStream.of(1, 5, 10, 100);
        IntFunction<String> intFunction = l -> String.valueOf(l);
        intStream.mapToObj(intFunction).forEach(s -> System.out.println("My String is: "+ s));
    }

    public void testIntUnaryOperatorMapIntToInt(){
        IntStream intStream = IntStream.of(1, 5, 10, 100);
        IntUnaryOperator intUnaryOperator = v -> v;
        intStream.map(intUnaryOperator).forEach(s -> System.out.println("My Integer is: "+ s));
    }

    public void testIntoToLongFunctionMapIntToLong(){
        IntStream intStream = IntStream.of(1, 5, 10, 100);
        IntToLongFunction intToLongFunction = v -> v;
        intStream.mapToLong(intToLongFunction).forEach(s -> System.out.println("My Long is: "+ s));
    }

    public void testIntToDoubleFunctionMapIntToDouble(){
        IntStream intStream = IntStream.of(1, 5, 10, 100);
        IntToDoubleFunction intToDoubleFunction = (p ->  Math.sqrt(p));
        intStream.mapToDouble(intToDoubleFunction).forEach(s -> System.out.println("My Double is: "+ s));
    }

    public void testIntBinaryOperatorReduce(){
        IntStream intStream = IntStream.of(1, 5, 10, 100);
        IntBinaryOperator intBinaryOperator = (v1, v2) -> v1 + v2;
        OptionalInt reducedValue = intStream.reduce(intBinaryOperator);
        reducedValue.ifPresent(value -> System.out.println("IntBinaryOperator value: " + value));
    }

    public void testToIntFunction(){
        Stream<String> stream = Stream.of("1", "5");
        ToIntFunction<String> toIntFunction = (value -> Integer.valueOf(value));
        IntStream intStream = stream.mapToInt(toIntFunction);
        intStream.forEach(value -> System.out.println("Stream<String> ToIntFunction value: " + value));
    }

    public void testToIntBiFunction(){
        ToIntBiFunction<Integer, Integer> sumInteger = (v1, v2) -> (v1 + v2);
        System.out.println("testToIntBiFunction value: " + sumInteger.applyAsInt(1000, 10));
    }

    public void testObjIntConsumerFromStringList(){
        List<String> values = Arrays.asList("1", "2", "3");

        ObjIntConsumer<List<String>> objIntConsumer = (list, num) -> {
            list.stream().forEach(value ->
                    System.out.println("List<String> ObjIntConsumer value * 2: " + Integer.valueOf(value) * num));
        };

        objIntConsumer.accept(values, 2);
    }

    public void testFlatMapInt(){
        Stream<Integer> stream = Stream.of(1, 5, 10, 100);
        IntStream doubleStream = stream.flatMapToInt(x -> IntStream.of(x));
        doubleStream.forEach(value -> System.out.println("Stream<Integer> to IntStream value: " + value));
    }

    public void testOptionalInt(){
        OptionalInt optionaIntMin = IntStream.empty().min();
        // optionalInt.getAsInt(); // java.util.NoSuchElementException: No value present

        int minValue = optionaIntMin.orElse(0);
        IntSupplier intSupplierValue = () -> 10;
        minValue = optionaIntMin.orElseGet(intSupplierValue);
        // minValue = optionalInt.orElseThrow(() -> new UnsupportedOperationException());
        System.out.println("Optional Else Min Int Value: " + minValue);

        OptionalInt optionaIntMax = IntStream.of(1,2).max();
        int maxValue = optionaIntMax.orElse(10); // else nao eh utilizado
        System.out.println("Optional Max Int Value: " + maxValue);
    }

    public void testIntSummaryStatistics(){
        IntStream intStream = IntStream.of(9, -4, 2);
        IntSummaryStatistics intSummaryStatistics = intStream.summaryStatistics();
        if(intSummaryStatistics.getCount() != 0){
            System.out.println("Sum: " + intSummaryStatistics.getSum());
            System.out.println("Avg: " + intSummaryStatistics.getAverage());
            System.out.println("Min: " + intSummaryStatistics.getMin());
            System.out.println("Max: " + intSummaryStatistics.getMax());
        }
    }

    public void testIntSupplier(){
        IntSupplier intSupplier1 = () -> Integer.MAX_VALUE;
        IntSupplier intSupplier2 = () -> Math.negateExact(intSupplier1.getAsInt());
        System.out.println("Int Supplier1: " + intSupplier1.getAsInt());
        System.out.println("Int Supplier2: " + intSupplier2.getAsInt());
    }

    public void testIntConsumer(){
        IntStream consumerStream = IntStream.of(1,2,3);
        IntConsumer longConsumer = value -> System.out.println("Int Consumer print value: " + value);

        consumerStream.forEach(longConsumer);
    }

    public void testIntPredicate(){
        IntStream predicateStream = IntStream.of(1,2,3);
        IntPredicate intPredicate = (value -> value % 2 == 0);

        predicateStream.filter(intPredicate)
                .forEach(value -> System.out.println("Int Predicate, this is a even value: " + value));
    }

    public static void main(String[] args) {
        TestIntStream testIntStream = new TestIntStream();
        testIntStream.testIntStreamMethods();
        testIntStream.testIntFunctionMapIntToString();
        testIntStream.testIntUnaryOperatorMapIntToInt();
        testIntStream.testIntoToLongFunctionMapIntToLong();
        testIntStream.testIntToDoubleFunctionMapIntToDouble();
        testIntStream.testIntBinaryOperatorReduce();
        testIntStream.testToIntFunction();
        testIntStream.testObjIntConsumerFromStringList();
        testIntStream.testFlatMapInt();
        testIntStream.testOptionalInt();
        testIntStream.testIntSummaryStatistics();
        testIntStream.testIntSupplier();
        testIntStream.testIntConsumer();
        testIntStream.testIntPredicate();
    }
}
