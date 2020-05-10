package chapter7;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class TestExecutorServiceSubmitMethod {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService service = Executors.newSingleThreadExecutor();

        Runnable runnableObject = () -> IntStream.rangeClosed(1, 4).forEach(System.out::println);
        Callable<Integer> callableObject = () -> IntStream.rangeClosed(1, 4).sum();

        service.submit(() -> System.out.println("Tanto faz se eh runnable ou callable"));
        Future<?> callWithRunnable = service.submit(runnableObject);
        Future<Integer> callWithCallable = service.submit(callableObject);

        service.shutdown();

        printFutureProperties(callWithRunnable);
        printFutureProperties(callWithCallable);

        System.out.println(callWithRunnable.get()); // null, cause runnable doesn't return anything

        // Integer cause it's Callable Generics
        System.out.println("the Sum of 1,2,3 and 4 is: " + callWithCallable.get(150, TimeUnit.MICROSECONDS));
    }

    private static void testAmbiguousLambda(){
        // Supplier doens't throw Any Exception
        // Supplier<?> supplier = () -> {throw new IOException();};

        //Method call does throw Exception, so throwing an IOException here is fine
        Callable<?> callable = () -> {throw new IOException();};

        use(callable);

        //Ambiguous method call match both use methods, Java doesn't know which to pick up
        //use(() -> {throw new IOException();});

    }

    private static void use(Callable<?> callable){ }
    private static void use(Supplier<?> supplier){ }

    private static void printFutureProperties(Future<?> future){
        System.out.println("isCancelled: " + future.isCancelled() + " - " + "IsDone: " + future.isDone());
    }
}
