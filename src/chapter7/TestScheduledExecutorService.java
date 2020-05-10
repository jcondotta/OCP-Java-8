package chapter7;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class TestScheduledExecutorService {

    ScheduledExecutorService scheduledService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        TestScheduledExecutorService test = new TestScheduledExecutorService();

        try{
            test.testScheduleRunnable();
            System.out.println("");
            test.testScheduleCallable();
            System.out.println("");
            test.testScheduleAtFixedRate();
            System.out.println("");
            test.testScheduleAtFixedDelay();
            System.out.println("");
        }
        catch (ExecutionException | InterruptedException e){
            System.err.println(e.getMessage());
        }
        finally {
            test.scheduledService.shutdown();
        }
    }


    private void testScheduleRunnable() throws ExecutionException, InterruptedException {
        Runnable runnable = () -> IntStream.rangeClosed(1,5).forEach(System.out::print);
        ScheduledFuture<?> scheduledFuture = scheduledService.schedule(runnable, 2, TimeUnit.SECONDS);
        System.out.println("ScheduleRunnable isDone: " + scheduledFuture.isDone());
        System.out.println(scheduledFuture.get());
        System.out.println("ScheduleRunnable isDone: " + scheduledFuture.isDone());
        System.out.println(scheduledFuture.getDelay(TimeUnit.SECONDS));
    }

    private void testScheduleCallable() throws ExecutionException, InterruptedException {
        try{
            Callable<?> callable = () -> {throw new IOException();};
            ScheduledFuture<?> scheduledFuture = scheduledService.schedule(callable, 1, TimeUnit.SECONDS);
            System.out.println(" \nFuture Sum Result: " + scheduledFuture.get()); // throws an IOException
            System.out.println("ScheduleCallable isDone: " + scheduledFuture.isDone()); // Not Printed out
            System.out.println(scheduledFuture.getDelay(TimeUnit.SECONDS));
        }
        catch (ExecutionException | InterruptedException e){
            System.err.println("ScheduleCallable Exception: " + e.getMessage());
        }
    }

    private void testScheduleAtFixedRate() throws ExecutionException, InterruptedException {
        AtomicInteger executingCount = new AtomicInteger(1);
        Runnable runnable = () -> {
            System.out.println("\nScheduleAtFixedRate ran " + executingCount.get() + "a time at: " + LocalTime.now());
            IntStream.rangeClosed(1,2000000000).filter(value -> value % 100000000 == 0).forEach(System.out::print);
            executingCount.getAndIncrement();
            System.out.println("\nFinished at: " + LocalTime.now());
        };

        ScheduledFuture<?> scheduledFuture = scheduledService.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
        while(true){
            Thread.sleep(1000);
            if(executingCount.get() == 3){
                System.out.println("\nTime to turn the scheduler off");
                scheduledFuture.cancel(true);
                break;
            }
        }
    }

    private void testScheduleAtFixedDelay() throws InterruptedException {
        AtomicInteger executingCount = new AtomicInteger(1);
        Runnable runnable = () -> {
            System.out.println("ScheduleAtFixedDelay ran " + executingCount.get() + "a time at: " + LocalTime.now());
            IntStream.rangeClosed(1,2000000000).filter(value -> value % 100000000 == 0).forEach(System.out::print);
            executingCount.getAndIncrement();
            System.out.println("\nFinished at: " + LocalTime.now());
        };

        ScheduledFuture<?> scheduledFuture = scheduledService.scheduleWithFixedDelay(runnable, 0, 1, TimeUnit.SECONDS);
        while(true){
            Thread.sleep(1000);
            if(executingCount.get() == 3){
                System.out.println("\nTime to turn the scheduler off");
                scheduledFuture.cancel(true);
                break;
            }
        }

    }
}
