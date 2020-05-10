package chapter7;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestPoolingWithSleep {

    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {}); // Pass it a runnable which does nothing

        new Thread(() -> {
            IntStream.iterate(0, x -> --x).limit(1000).forEach(System.out::println);
        }).start();


        Thread threadIncreaseCounter = new Thread(() -> {
            IntStream.rangeClosed(0,20000).forEach(item -> {
                counter++;
                System.out.println(item);
            });
        });

        threadIncreaseCounter.start();

        while (counter < 10000){
            System.out.println("Not reached Yet - " + Thread.currentThread().getName());
            Thread.sleep(10L); // Sleep while threadIncreaseCounter work on the static variable counter
        }

        TestThread.printThreadInfo(threadIncreaseCounter);
    }
}
