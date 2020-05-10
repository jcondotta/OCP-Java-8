package chapter7;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class TestExecutorServiceInvokeMethod {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        try{
            testExecutorServiceInvokeAll(service);
            testExecutorServiceInvokeAny(service);
        }
        finally {
            service.shutdown();
        }
    }

    private static void testExecutorServiceInvokeAll(ExecutorService service){
        Callable<Integer> callableSum = () -> IntStream.rangeClosed(1, 5).sum();
        Callable<Integer> callableSum2 = () -> IntStream.rangeClosed(1, 2).sum();
        List<Callable<Integer>> callables = Arrays.asList(callableSum, callableSum2);

        try {
            List<Future<Integer>> futures = service.invokeAll(callables, 2, TimeUnit.SECONDS);
            for(Future<Integer> future : futures){
                System.out.println(future.get());
            }
        }
        catch (InterruptedException | ExecutionException e){
            System.out.println(e);
        }
    }

    private static void testExecutorServiceInvokeAny(ExecutorService service){
        Callable<Integer> callableSum = () -> IntStream.rangeClosed(1, 10).peek(value -> System.out.println("callableSum")).sum();
        Callable<Integer> callableSum2 = () -> IntStream.rangeClosed(1, 3).peek(value -> System.out.println("callableSum2")).sum();
        List<Callable<Integer>> callables = Arrays.asList(callableSum, callableSum2);

        try {
            Integer futureOutcome = service.invokeAny(callables, 2, TimeUnit.SECONDS);
            System.out.println(futureOutcome);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
