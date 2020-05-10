package chapter4.primitiveStream;

import chapter3.Person;

import java.util.*;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class TestDoubleStream {

    public static List<Double> doubleList = Arrays.asList(1.0, 5.0, 10.0);

    public void testDoubleStreamMethods(){
        // (1 - 0.0, 2 - 10.0, 3 - 20.0)
        DoubleStream.iterate(0, d -> d + 10).limit(3).average().ifPresent(System.out::println);

        DoubleStream.iterate(0, d -> d + 10).limit(3).min().ifPresent(System.out::println); // 0.0
        DoubleStream.iterate(0, d -> d + 10).limit(3).max().ifPresent(System.out::println); // 20.0
        System.out.println(DoubleStream.iterate(0, d -> d + 10).limit(3).count()); // (3) - 0.0, 10.0, 20.0
        System.out.println(DoubleStream.iterate(0, d -> d + 10).limit(3).sum()); // 30.0

        // DoubleStream.range Nao existe
    }

    public void testDoubleFunctionMapDoubleToString(){
        DoubleStream doubleStream = DoubleStream.of(1.1, 5.5, 10.10, 100.00);
        DoubleFunction<String> doubleToStringFunction = d -> String.valueOf(d);
        doubleStream.mapToObj(doubleToStringFunction).forEach(s -> System.out.println("My String is: "+ s));
    }

    public void testDoubleToIntFunctionMapDoubleToInt(){
        DoubleStream doubleStream = DoubleStream.of(1.1, 5.5, 10.10, 100.00);
        DoubleToIntFunction doubleToIntFunction = v -> (int) Math.round(v);
        doubleStream.mapToInt(doubleToIntFunction).forEach(s -> System.out.println("My Integer is: "+ s));
    }

    public void testDoubleToLongFunctionMapDoubleToLong(){
        DoubleStream doubleStream = DoubleStream.of(1.1, 5.5, 10.10, 100.00);
        DoubleToLongFunction doubleToLongFunction = v -> Math.round(v);
        doubleStream.mapToLong(doubleToLongFunction).forEach(s -> System.out.println("My Long is: "+ s));
    }

    public void testDoubleUnaryOperatorMapDoubleToDouble(){
        DoubleStream doubleStream = DoubleStream.of(1.1, 5.5, 10.10, 100.00);
        DoubleUnaryOperator doubleUnaryOperator = (p ->  Math.sqrt(p));
        doubleStream.map(doubleUnaryOperator).forEach(s -> System.out.println("My Double is: "+ s));
    }

    public void testDoubleBinaryOperatorReduce(){
        DoubleStream doubleStream = DoubleStream.of(1.1, 5.5, 10.10, 100.00);
        DoubleBinaryOperator doubleBinaryOperator = (v1, v2) -> v1 + v2;
        OptionalDouble reducedValue = doubleStream.reduce(doubleBinaryOperator);
        reducedValue.ifPresent(value -> System.out.println("DoubleBinaryOperator value: " + value));
    }

    public void testToDoubleFunction(){
        Stream<String> stream = Stream.of("1", "5");
        ToDoubleFunction<String> toDoubleFunction = (value -> Double.valueOf(value));
        DoubleStream doubleStream = stream.mapToDouble(toDoubleFunction);
        doubleStream.forEach(value -> System.out.println("Stream<String> ToDoubleFunction value: " + value));
    }

    public void testToDoubleBiFunction(){
        ToDoubleBiFunction<Double, Double> sumDouble = (v1, v2) -> (v1 + v2);
        System.out.println("testToDoubleBiFunction value: " + sumDouble.applyAsDouble(1000.10, 10.2));
    }

    public void testObjDoubleConsumerFromStringList(){
        List<String> values = Arrays.asList("1", "2", "3");

        ObjDoubleConsumer<List<String>> objDoubleConsumer = (list, num) -> {
            list.stream().forEach(value ->
                    System.out.println("List<String> ObjDoubleConsumer value * 2: " + Double.valueOf(value) * num));
        };

        objDoubleConsumer.accept(values, 2.0);
    }

    public void testFlatMapDouble(){
        Stream<Double> stream = Stream.of(1.1, 5.5, 10.10, 100.00);
        DoubleStream doubleStream = stream.flatMapToDouble(x -> DoubleStream.of(x));
        doubleStream.forEach(value -> System.out.println("Stream<Double> to DoubleStream value: " + value));
    }

    public void testOptionalDouble(){
        OptionalDouble optionalAverage = DoubleStream.empty().average();
        // optionalInt.getAsDouble(); // java.util.NoSuchElementException: No value present

        double average = optionalAverage.orElse(Double.NaN);
        System.out.println("Optional Empty Average Value: " + average);

        optionalAverage = DoubleStream.of(1,2,3).average();
        average = optionalAverage.orElse(Double.NaN);
        System.out.println("Optional Average Value: " + average); //2
    }

    public void testDoubleSummaryStatistics(){
        DoubleStream doubleStream = DoubleStream.of(9.9, -4.4, 2);
        DoubleSummaryStatistics doubleSummaryStatistics = doubleStream.summaryStatistics();
        if(doubleSummaryStatistics.getCount() != 0){
            System.out.println("Sum: " + doubleSummaryStatistics.getSum());
            System.out.println("Avg: " + doubleSummaryStatistics.getAverage());
            System.out.println("Min: " + doubleSummaryStatistics.getMin());
            System.out.println("Max: " + doubleSummaryStatistics.getMax());
        }
    }

    public void testDoubleSupplier(){
        DoubleSupplier doubleSupplier1 = () -> Double.NaN;
        DoubleSupplier doubleSupplier2 = () -> Math.random();
        System.out.println("Double Supplier1: " + doubleSupplier1.getAsDouble());
        System.out.println("Double Supplier2: " + doubleSupplier2.getAsDouble());
    }

    public void testDoubleConsumer(){
        DoubleStream consumerStream = DoubleStream.of(1,2,3);
        DoubleConsumer longConsumer = value -> System.out.println("Double Consumer print value: " + value);

        consumerStream.forEach(longConsumer);
    }

    public static void main(String[] args) {
        TestDoubleStream testDoubleStream = new TestDoubleStream();
        testDoubleStream.testDoubleStreamMethods();
        testDoubleStream.testDoubleFunctionMapDoubleToString();
        testDoubleStream.testDoubleToIntFunctionMapDoubleToInt();
        testDoubleStream.testDoubleToLongFunctionMapDoubleToLong();
        testDoubleStream.testDoubleUnaryOperatorMapDoubleToDouble();
        testDoubleStream.testDoubleBinaryOperatorReduce();
        testDoubleStream.testToDoubleFunction();
        testDoubleStream.testToDoubleBiFunction();
        testDoubleStream.testObjDoubleConsumerFromStringList();
        testDoubleStream.testFlatMapDouble();
        testDoubleStream.testOptionalDouble();
        testDoubleStream.testDoubleSummaryStatistics();
        testDoubleStream.testDoubleSupplier();
        testDoubleStream.testDoubleConsumer();
    }
}
