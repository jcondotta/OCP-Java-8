package chapter3;

import java.util.*;

public class TestQueue {

    LinkedList<Integer> integerLinkedList = new LinkedList<>();

    public static class TestArrayDeque {

        Deque<String> stringArrayDeque = new ArrayDeque<>();

        public TestArrayDeque() {
            //stringArrayDeque.element(); // java.util.NoSuchElementException
            //stringArrayDeque.add(null); // NullPointerException
            stringArrayDeque.addFirst("AddFirst");
            System.out.println("getFirst: " + stringArrayDeque.getFirst());
            System.out.println(stringArrayDeque);

            stringArrayDeque.addLast("AddLast");
            System.out.println("getLast: " + stringArrayDeque.getLast());
            System.out.println(stringArrayDeque);

            stringArrayDeque.add("Add");
            System.out.println("Element: " + stringArrayDeque.element());
            System.out.println(stringArrayDeque);

            System.out.println("Removing: " + stringArrayDeque.remove());
            System.out.println(stringArrayDeque);
            System.out.println("Removing: " + stringArrayDeque.remove());
            System.out.println(stringArrayDeque);
            System.out.println("Removing: " + stringArrayDeque.remove());
            System.out.println(stringArrayDeque);

            System.out.println("");

            stringArrayDeque.offerFirst("OfferFirst");
            System.out.println("PeekFirst: " + stringArrayDeque.peekFirst());
            System.out.println(stringArrayDeque);

            stringArrayDeque.offerLast("OfferLast");
            System.out.println("PeekLast: " + stringArrayDeque.peekLast());
            System.out.println(stringArrayDeque);

            stringArrayDeque.offer("Offer");
            System.out.println("Peek: " + stringArrayDeque.peek());
            System.out.println(stringArrayDeque);

            System.out.println("Polling: " + stringArrayDeque.poll());
            System.out.println(stringArrayDeque);
            System.out.println("Polling: " + stringArrayDeque.poll());
            System.out.println(stringArrayDeque);
            System.out.println("Polling: " + stringArrayDeque.poll());
            System.out.println(stringArrayDeque);

            System.out.println("");

            stringArrayDeque.push("Push1");
            System.out.println(stringArrayDeque);

            stringArrayDeque.push("Push2");
            System.out.println(stringArrayDeque);

            stringArrayDeque.push("Push3");
            System.out.println(stringArrayDeque);

            System.out.println("Poping: " + stringArrayDeque.pop());
            System.out.println(stringArrayDeque);
            System.out.println("Poping: " + stringArrayDeque.pop());
            System.out.println(stringArrayDeque);
            System.out.println("Poping: " + stringArrayDeque.pop());
            System.out.println(stringArrayDeque);
        }

        public static void main(String[] args) {
            TestArrayDeque testArrayDeque = new TestArrayDeque();
        }
    }

    public static class TestLinkedList {

        LinkedList<Integer> integerLinkedList = new LinkedList<>();

        public TestLinkedList() {
            integerLinkedList.add(10);
            integerLinkedList.add(120);
            integerLinkedList.add(12);
            integerLinkedList.push(100);
            System.out.println(integerLinkedList.get(2));
            System.out.println(integerLinkedList);
        }

        public static void main(String[] args) {
            TestLinkedList testLinkedList = new TestLinkedList();
        }
    }
}
