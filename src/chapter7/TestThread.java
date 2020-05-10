package chapter7;

import java.util.stream.Stream;

public class TestThread {


    public static void main(String[] args) {
        Runnable runnablePrintValues = () -> {
            for(int i = 0; i < 1000; i++){
                System.out.println(i);
            }
        };

        Runnable runnablePrintNegativeValues = () -> {
            for(int i = 0; i > -1000; i--){
                System.out.println(i);
            }
        };

        Runnable runnable = () -> Stream.iterate(0, integer -> integer + 1).limit(13).forEach(System.out::println);
        runnable.run();
        Thread thread1 = new Thread(runnablePrintValues);
        Thread thread2 = new Thread(runnablePrintNegativeValues);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread1.start(); // Run all items in parallel with next line
        thread2.run();
        MyThread myThread = new MyThread();
        myThread.run();

        printThreadInfo(thread1);
        printThreadInfo(thread2);
        printThreadInfo(myThread);
    }

    public static void printThreadInfo(Thread thread){
        StringBuilder sb = new StringBuilder();
        sb.append(thread.getName()).append(" - ")
            .append("isAlive: " + thread.isAlive()).append(" - ")
            .append("isDaemon: " + thread.isDaemon()).append(" - ")
            .append("Priority: " + thread.getPriority()).append(" - ")
            .append("State: " + thread.getState()).append(" - ")
            .append("isInterrupted: " + thread.isInterrupted()).append(" - ");

        System.out.println(sb.toString());

    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("I'm the new MyThread.");
        }
    }
}
