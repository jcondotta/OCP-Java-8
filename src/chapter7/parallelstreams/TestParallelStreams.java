package chapter7.parallelstreams;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestParallelStreams {

    public static void main(String[] args) {
        TestParallelStreams testParallelStreams = new TestParallelStreams();

        Stream<Integer> stream = Stream.of(1,2,3,4,5,6);
        stream.parallel(); // Intermediate op, it does nothing

        System.out.print("This is a serial stream: ");
        Stream.of(1,2,3,4,5,6).forEach(System.out::print);

        System.out.println("\n");

        // prints all numbers in random other
        System.out.print("This is a parallel stream: ");
        Stream.of(1,2,3,4,5,6).parallel().forEach(System.out::print);

        System.out.println("\n");

        // prints all numbers in normal other, cause it uses forEachOrdered
        System.out.print("This is a ordered parallel stream : ");
        Stream.of(1,2,3,4,5,6).parallel().forEachOrdered(System.out::print);

        testParallelStreams.testParallelPerformance();

        // it prints with no order due the parallel stream
        Stream.of("jackal", "kangaroo", "lemur", "animal", "money")
                .parallel()
                .map(s -> {System.out.println(s); return s.toUpperCase(); })
                .forEach(System.out::println);

    }

    public void testParallelPerformance(){
        List<Integer> data = new ArrayList<>();
        Stream.iterate(0, x -> x + 1).limit(10).forEach(x -> data.add(x));

        Instant start = Instant.now();

        processAllItems(data);

        System.out.println("\n");

        Duration time = Duration.between(start, Instant.now());
        System.out.println(time);
    }

    public void processAllItems(List<Integer> data){
        data.stream().parallel().forEach(item -> { // takes 0.2 seconds
//        data.stream().forEach(item -> { // takes 1.02 seconds.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
