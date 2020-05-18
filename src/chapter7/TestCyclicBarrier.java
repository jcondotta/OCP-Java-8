package chapter7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class TestCyclicBarrier {

    private void removeAnimals(){
        System.out.println("Removing Animals");
    }

    private void cleanPen(){
        System.out.println("Cleaning the Pen");
    }

    private void addAnimals(){
        System.out.println("Adding Animals");
    }

    public void performTask(){
        removeAnimals();
        cleanPen();
        addAnimals();
    }

    public void performTask(CyclicBarrier cyclicBarrierRemoveAnimals, CyclicBarrier cyclicBarrierCleanPen)
            throws BrokenBarrierException, InterruptedException {
        removeAnimals();
        cyclicBarrierRemoveAnimals.await();
        cleanPen();
        cyclicBarrierCleanPen.await();
        addAnimals();
    }

    /* Prints all tasks unordered cause the threads are executing with no orchestration */
    public void allAboard(){
        ExecutorService service = Executors.newFixedThreadPool(4);
        Stream.iterate(0, x -> ++x).limit(4)
                .forEach(item -> service.execute(() -> performTask()));
        service.shutdown();
    }

    /* Prints all tasks have to wait other threads on each to start next phase all together */
    public void allAboardCyclicBarrier(CyclicBarrier cyclicBarrierRemoveAnimals, CyclicBarrier cyclicBarrierCleanPen){
        ExecutorService service = Executors.newFixedThreadPool(4);

        Stream.iterate(0, x -> ++x).limit(4)
            .forEach(item ->
                service.execute(() -> {
                    try {
                        performTask(cyclicBarrierRemoveAnimals, cyclicBarrierCleanPen);
                    } catch (BrokenBarrierException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
        service.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        TestCyclicBarrier testCyclicBarrier = new TestCyclicBarrier();
//        testCyclicBarrier.allAboard();
//        System.out.println("\n");

        CyclicBarrier cyclicBarrierRemoveAnimals = new CyclicBarrier(4,
                () -> System.out.println("******* ALL Animals have been removed *******"));
        CyclicBarrier cyclicBarrierCleanPen = new CyclicBarrier(4);

        testCyclicBarrier.allAboardCyclicBarrier(cyclicBarrierRemoveAnimals, cyclicBarrierCleanPen);

        Thread.sleep(1500);
        //Podem ser reutilizados
        testCyclicBarrier.allAboardCyclicBarrier(cyclicBarrierRemoveAnimals, cyclicBarrierCleanPen);
    }

}
