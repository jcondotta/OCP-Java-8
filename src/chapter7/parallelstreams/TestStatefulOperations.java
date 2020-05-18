package chapter7.parallelstreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class TestStatefulOperations {

    int [] numbers = new int []{1,2,3,4,5,6,7,8,9,0};

    public void testConcurrentCollectionsWithParallelStream(){
//        List<Integer> data = new ArrayList<>(); // 1A
        List<Integer> data = Collections.synchronizedList(new ArrayList<>()); // 1B
        Stream.of(1,2,3,4,5,6,7,8,9,0).parallel()
                .map(value -> {data.add(value); return value;})
//                .forEach(System.out::print); // 2A
                .forEachOrdered(System.out::print); // 2B

        // 1A + 2A = 2 unpredictable results
        // 1A + 2B = 1 list ordered, 2 list unpredictable (losing items)
        // 1B + 2A = 2 unpredictable results
        // 1B + 2B = 1 list ordered, 1 list unpredictable (not losing items)

        System.out.println("");

        data.forEach(s -> System.out.print(s + ""));

        System.out.println("\n");
    }

    public void testParallelReductions(){
        // Find ANY Operation

        // unpredictable
        System.out.println("Prints Any Number: " + Arrays.stream(numbers).parallel().findAny().getAsInt());

        // unpredictable (9 or 0)
        System.out.println("Prints Any Number: " + Arrays.stream(numbers).parallel().skip(8).findAny().getAsInt());

        // Find FIRST Operation

        // Print 1, cause it uses order to get, which slow down a bit the process
        System.out.println("Prints Always ONE: " + Arrays.stream(numbers).parallel().findFirst().getAsInt());

        // Print 6, cause it's ordered so it will always skip first 5 items
        System.out.println("Prints Always 6: " + Arrays.stream(numbers).parallel().skip(5).findFirst().getAsInt());

        System.out.println("\n");
    }

    public void testUnorderedStream(){
        System.out.print("No Effect on Serial Streams: ");
        Arrays.stream(numbers).unordered().skip(2).forEach(System.out::print);

        System.out.println("");

        System.out.print("Non Unordered: ");
        Arrays.stream(numbers).unordered().skip(2).forEach(System.out::print);

        System.out.println("");

        System.out.print("Parallel: ");
        Arrays.stream(numbers).parallel().skip(2).forEach(System.out::print);

        System.out.println("");

        System.out.print("Parallel + Non Unordered: ");
        Arrays.stream(numbers).unordered().parallel().skip(2).forEach(System.out::print);
    }

    public void testCombiningResultsWithReduce(){

    }

    public static void main(String[] args) {
        TestStatefulOperations testStatefulOperations = new TestStatefulOperations();
        testStatefulOperations.testConcurrentCollectionsWithParallelStream();
        testStatefulOperations.testParallelReductions();
        testStatefulOperations.testUnorderedStream();
        testStatefulOperations.testCombiningResultsWithReduce();
    }
}
