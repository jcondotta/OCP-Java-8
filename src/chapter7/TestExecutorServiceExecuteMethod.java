package chapter7;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class TestExecutorServiceExecuteMethod {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread Begin");
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> System.out.println("Service Runnable 1"));
        service.execute(() -> Stream.of(1,2,3).forEach(System.out::println));
        service.execute(() -> System.out.println("Service Runnable 3"));
        System.out.println("Main thread End");

        System.out.println("Service isShutdown: " + service.isShutdown());
        service.shutdown();
        System.out.println("Service isShutdown: " + service.isShutdown());

        while (!service.isTerminated()){
            System.out.println(Thread.currentThread().getName() + " - Service isTerminated: " + service.isTerminated());
            Thread.sleep(1);
        }

        System.out.println("Service done \n");
        // depois do shutdown, nenhuma task pode ser associada ao service, if so RejectedExecutionException is thrown
        //service.execute(() -> System.out.println("Service Runnable 4"));

        Runnable zeroAteNove = () -> Stream.iterate(0, x -> ++x).limit(10).forEach(System.out::println);
        Runnable zeroAteMenosNove = () -> Stream.iterate(0, x -> --x).limit(10).forEach(System.out::println);


        System.out.println(zeroAteMenosNove);
        System.out.println(zeroAteNove);

        service = Executors.newSingleThreadExecutor();
        service.execute(zeroAteMenosNove); // this task is executed
        service.execute(zeroAteNove); // this one doesn't have time to start cause main thread, has shutdown the service
        List<Runnable> tasksNotStarted = service.shutdownNow();
        System.out.println(tasksNotStarted); // zeroAteNove
    }
}
