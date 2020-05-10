package chapter7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class TestSynchronizing {

    public static int count = 0;

    public static void printStaticCounter(){
        synchronized (TestSynchronizing.class){
            System.out.println(Thread.currentThread().getName() + " - " + (++count));
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestSynchronizing testSynchronizing = new TestSynchronizing();
        ExecutorService service = Executors.newFixedThreadPool(20);

        NotSynchronizedManager notSynchronizedManager = testSynchronizing.new NotSynchronizedManager();
//        notSynchronizedManager.printNumber(service); // does not guarantee it will print ordered
//
//        SynchronizedBlockManager synchronizedBlockManager = testSynchronizing.new SynchronizedBlockManager();
//        synchronizedBlockManager.printNumber(service);
//
//        SynchronizedMethodManager synchronizedMethodManager = testSynchronizing.new SynchronizedMethodManager();
//        synchronizedMethodManager.printNumber(service);

//        for(int i = 0; i < 10; i++)
//                service.submit(() -> printStaticCounter());

        synchronized (testSynchronizing){
            Future<?> fi = service.submit(() -> notSynchronizedManager.printNumber(service));
            fi.get();
        }

        service.shutdown();
    }

    interface Manager {
        void incrementAndReport();

        default void printNumber(ExecutorService service){
            System.out.println("");
            for(int i = 0; i < 10; i++)
                service.submit(() -> incrementAndReport());

            System.out.println(Thread.currentThread().getName() + " Fim \n");
        }
    }

    class SynchronizedMethodManager implements Manager{

        public AtomicInteger count = new AtomicInteger(0);

        public synchronized void incrementAndReport(){
            System.out.println(Thread.currentThread().getName() + " - " + (count.incrementAndGet()));
        }
    }

    class SynchronizedBlockManager implements Manager{

        public AtomicInteger count = new AtomicInteger(0);

        public void incrementAndReport(){
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + " - " + (count.incrementAndGet()));
            }
        }
    }

    class NotSynchronizedManager implements Manager{
        public AtomicInteger count = new AtomicInteger(0);

        public void incrementAndReport(){
            System.out.println(Thread.currentThread().getName() + " - " + (count.incrementAndGet()));
        }
    }
}