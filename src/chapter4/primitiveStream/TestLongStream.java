package chapter4.primitiveStream;

import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.OptionalLong;
import java.util.function.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TestLongStream {

    public void testLongStreamMethods(){
        // (1 - 0, 2 - 10, 3 - 20)
        LongStream.iterate(0, d -> d + 10).limit(3).average().ifPresent(System.out::println);

        LongStream.iterate(0, d -> d + 10).limit(3).min().ifPresent(System.out::println); // 0
        LongStream.iterate(0, d -> d + 10).limit(3).max().ifPresent(System.out::println); // 20
        System.out.println(LongStream.iterate(0, d -> d + 10).limit(3).count()); // (3) - 0, 10, 20
        System.out.println(LongStream.iterate(0, d -> d + 10).limit(3).sum()); // 30

        System.out.println("");
        LongStream.range(1, 4).forEach(System.out::println);
        System.out.println("");
        LongStream.rangeClosed(1, 3).max().ifPresent(System.out::println);

        System.out.println("");
    }

    public void testLongFunctionMapLongToString(){
        LongStream longStream = LongStream.of(1, 5, 10, 100);
        LongFunction<String> longFunction = l -> String.valueOf(l);
        longStream.mapToObj(longFunction).forEach(s -> System.out.println("My String is: "+ s));
    }

    public void testLongToIntFunctionMapLongToInt(){
        LongStream longStream = LongStream.of(1, 5, 10, 100);
        LongToIntFunction longToIntFunction = v -> (int) v;
        longStream.mapToInt(longToIntFunction).forEach(s -> System.out.println("My Integer is: "+ s));
    }

    public void testLongUnaryOperatorMapLongToLong(){
        LongStream longStream = LongStream.of(1, 5, 10, 100);
        LongUnaryOperator longUnaryOperator = v -> v;
        longStream.map(longUnaryOperator).forEach(s -> System.out.println("My Long is: "+ s));
    }

    public void testLongToDoubleFunctionMapLongToDouble(){
        LongStream longStream = LongStream.of(1, 5, 10, 100);
        LongToDoubleFunction longToDoubleFunction = (p ->  Math.sqrt(p));
        longStream.mapToDouble(longToDoubleFunction).forEach(s -> System.out.println("My Double is: "+ s));
    }

    public void testLongBinaryOperatorReduce(){
        LongStream longStream = LongStream.of(1, 5, 10, 100);
        LongBinaryOperator longBinaryOperator = (v1, v2) -> v1 + v2;
        OptionalLong reducedValue = longStream.reduce(longBinaryOperator);
        reducedValue.ifPresent(value -> System.out.println("LongBinaryOperator value: " + value));
    }

    public void testToLongFunction(){
        Stream<String> stream = Stream.of("1", "5");
        ToLongFunction<String> toLongFunction = (value -> Long.valueOf(value));
        LongStream longStream = stream.mapToLong(toLongFunction);
        longStream.forEach(value -> System.out.println("Stream<String> ToLongFunction value: " + value));
    }

    public void testToLongBiFunction(){
        ToLongBiFunction<Long, Long> sumLong = (v1, v2) -> (v1 + v2);
        System.out.println("testToLongBiFunction value: " + sumLong.applyAsLong(1000L, 10L));
    }

    public void testObjLongConsumerFromStringList(){
        List<String> values = Arrays.asList("1", "2", "3");

        ObjLongConsumer<List<String>> objLongConsumer = (list, num) -> {
            list.stream().forEach(value ->
                    System.out.println("List<String> ObjLongConsumer value * 2: " + Long.valueOf(value) * num));
        };

        objLongConsumer.accept(values, 2);
    }

    public void testFlatMapLong(){
        Stream<Long> stream = Stream.of(1L, 5L, 10L, 100L);
        LongStream doubleStream = stream.flatMapToLong(x -> LongStream.of(x));
        doubleStream.forEach(value -> System.out.println("Stream<Long> to LongStream value: " + value));
    }

    public void testOptionalLong(){
        OptionalLong optionalLongMin = LongStream.empty().min();
        // optionalInt.getAsLong(); // java.util.NoSuchElementException: No value present

        long minValue = optionalLongMin.orElse(0L);
        LongSupplier longSupplierValue = () -> 100L;
        minValue = optionalLongMin.orElseGet(longSupplierValue);
        // minValue = optionalLongMin.orElseThrow(() -> new UnsupportedOperationException());
        System.out.println("Optional Else Min Long Value: " + minValue);

        OptionalLong optionalLong = LongStream.of(1,2).max();
        long maxValue = optionalLong.orElse(10); // else nao eh utilizado
        System.out.println("Optional Max Long Value: " + maxValue);
    }

    public void testIntSummaryStatistics(){
        LongStream longStream = LongStream.of(9, -4, 2);
        LongSummaryStatistics longSummaryStatistics = longStream.summaryStatistics();
        if(longSummaryStatistics.getCount() != 0){
            System.out.println("Sum: " + longSummaryStatistics.getSum());
            System.out.println("Avg: " + longSummaryStatistics.getAverage());
            System.out.println("Min: " + longSummaryStatistics.getMin());
            System.out.println("Max: " + longSummaryStatistics.getMax());
        }
    }

    public void testLongSupplier(){
        LongSupplier longSupplier1 = () -> Long.MAX_VALUE;
        LongSupplier longSupplier2 = () -> Math.negateExact(longSupplier1.getAsLong());
        System.out.println("Long Supplier1: " + longSupplier1.getAsLong());
        System.out.println("Long Supplier2: " + longSupplier2.getAsLong());
    }

    public void testLongConsumer(){
        LongStream consumerStream = LongStream.of(1,2,3);
        LongConsumer longConsumer = value -> System.out.println("Long Consumer print value: " + value);

        consumerStream.forEach(longConsumer);
    }

    public static void main(String[] args) {
        TestLongStream testLongStream = new TestLongStream();
        testLongStream.testLongStreamMethods();
        testLongStream.testLongFunctionMapLongToString();
        testLongStream.testLongToIntFunctionMapLongToInt();
        testLongStream.testLongUnaryOperatorMapLongToLong();
        testLongStream.testLongToDoubleFunctionMapLongToDouble();
        testLongStream.testLongBinaryOperatorReduce();
        testLongStream.testToLongFunction();
        testLongStream.testToLongBiFunction();
        testLongStream.testObjLongConsumerFromStringList();
        testLongStream.testFlatMapLong();
        testLongStream.testOptionalLong();
        testLongStream.testIntSummaryStatistics();
        testLongStream.testLongSupplier();
        testLongStream.testLongConsumer();
    }

}
