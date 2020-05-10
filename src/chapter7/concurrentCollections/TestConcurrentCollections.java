package chapter7.concurrentCollections;

import java.util.*;
import java.util.concurrent.*;

public class TestConcurrentCollections {

    ExecutorService service = Executors.newFixedThreadPool(20);

    Map<Integer, Integer> defaultMap;

    public void testModifyingHashMap(){
        System.out.println("** testModifyingHashMap **");
        Map<Integer, Integer> map = new HashMap<>();
        try{
            restoreDataIntegerStringMap(map, 3);
            System.out.println(map);
            for(Integer key : map.keySet()){
//                map.remove(key); //Altera o numero de objetos do mapa
//                map.put(key * 2, key); //Altera o numero de objetos do mapa
                map.put(key, map.get(key) + 1); // Nao Altera o numero de objetos, mas altera o valor do entryValue
            }
            System.out.println(map);

            System.out.println("");
        }
        catch (ConcurrentModificationException e){
            System.out.println(e); // Tentando alterar o numero de objetos dentro do map
        }
    }

    public void testConcurrentHashMap(){
        System.out.println("** testConcurrentHashMap **");
//        Map<String, String> map = new HashMap<>(); // ConcurrentModificationException
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        restoreDataIntegerStringMap(map, 3);
        System.out.println(map);
        for(Integer key : map.keySet()){
            map.put(key * 10, key); //Altera o numero de objetos do mapa
            //Porque ele so roda os novos items que foram inserido na iteracao dos primeiros items.
        }

        System.out.println(map + "\n");

    }



    public void testCopyOnWriteArrayList(){
        System.out.println("** testCopyOnWriteArrayList **");
//        Map<String, String> map = new HashMap<>(); // ConcurrentModificationException
        List<Integer> copyOnWrite = new CopyOnWriteArrayList<>();
        copyOnWrite.add(1); copyOnWrite.add(2); copyOnWrite.add(3);

        System.out.println(copyOnWrite);
        for(Integer value : copyOnWrite){
            copyOnWrite.add(value * 10); // Isso nao afeta os valores reservados para o loop
        }

        System.out.println(copyOnWrite + "\n");
    }

    public void testConcurrentLinkedQueue(){
        System.out.println("** testConcurrentLinkedQueue **");
        Queue<Integer> deque = new ConcurrentLinkedQueue<>();
        deque.offer(32);
        deque.offer(31);
        System.out.println("Peek: " + deque.peek());
        System.out.println("Poll: " + deque.poll());
        System.out.println(deque + "\n");
    }

    public void testConcurrentLinkedDeque(){
        System.out.println("** testConcurrentLinkedDeque **");
        Deque<Integer> deque = new ConcurrentLinkedDeque<>();
        deque.offer(32);
        deque.push(31);
        System.out.println("Peek: " + deque.peek());
        System.out.println("Poll: " + deque.poll());
        System.out.println(deque);

        System.out.println("");
    }

    public void testConcurrentSkipListMap(){
        System.out.println("** testConcurrentSkipListMap **");
        ConcurrentNavigableMap<Integer, String> concurrentNavigableMap = new ConcurrentSkipListMap<>();
        concurrentNavigableMap.put(1, "1");
        concurrentNavigableMap.put(2, "2");

        System.out.println("Peek: " + concurrentNavigableMap.firstEntry());
        System.out.println("Poll: " + concurrentNavigableMap.pollLastEntry());
        System.out.println("Poll: " + concurrentNavigableMap.pollLastEntry());
        System.out.println(concurrentNavigableMap);

        System.out.println("");
    }

    public void testConcurrentSkipListSet(){
        System.out.println("** testConcurrentSkipListSet **");
        NavigableSet<Integer> concurrentSkipListSet = new ConcurrentSkipListSet<>();
        concurrentSkipListSet.add(10);
        concurrentSkipListSet.add(12);

        System.out.println("Peek: " + concurrentSkipListSet.pollFirst());
        System.out.println("Poll: " + concurrentSkipListSet.pollFirst());
        System.out.println(concurrentSkipListSet);

        System.out.println("");
    }


    public void testLinkedBlockedQueue() throws InterruptedException {
        System.out.println("** testLinkedBlockedQueue **");
        BlockingQueue<Integer> deque = new LinkedBlockingQueue<>();

        deque.offer(20, 10, TimeUnit.MINUTES);
        System.out.println("Peek: " + deque.peek());
        System.out.println("Poll: " + deque.poll(2, TimeUnit.MICROSECONDS));
        System.out.println(deque);

        System.out.println("");
    }

    public void testLinkedBlockedDeque() throws InterruptedException {
        System.out.println("** testLinkedBlockedDeque **");
        BlockingDeque<Integer> deque = new LinkedBlockingDeque<>();

        deque.offer(20, 10, TimeUnit.MINUTES);
        deque.offerFirst(21, 10, TimeUnit.MINUTES);
        deque.offerLast(22, 10, TimeUnit.MINUTES);
        System.out.println("Peek: " + deque.peek());
        System.out.println("Poll: " + deque.poll(2, TimeUnit.MICROSECONDS));
        System.out.println("Poll: " + deque.poll(2, TimeUnit.MICROSECONDS));
        System.out.println("Poll: " + deque.poll(2, TimeUnit.MICROSECONDS));
        System.out.println(deque);

        System.out.println("");
    }

    private void restoreDataIntegerStringMap(Map<Integer, Integer> map, int numberOfItems){
        map.clear();

        for(int i = 1; i <= numberOfItems; i++)
            map.put(i, i * 10);
    }

    public static void main(String[] args) {
        try {
            TestConcurrentCollections testCollections = new TestConcurrentCollections();
            testCollections.testModifyingHashMap();
            testCollections.testConcurrentHashMap();
            testCollections.testCopyOnWriteArrayList();
            testCollections.testConcurrentLinkedQueue();
            testCollections.testConcurrentLinkedDeque();
            testCollections.testConcurrentSkipListMap();
            testCollections.testConcurrentSkipListSet();
            testCollections.testLinkedBlockedQueue();
            testCollections.testLinkedBlockedDeque();

            testCollections.service.shutdown();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
