package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TestList {

    static class TestArrayList {
        List<Person> personList = Arrays.asList(new Person("Jefferson"), new Person("William"));
        List<Integer> integerList = new ArrayList<>();

        public TestArrayList() {
            this.integerList.add(1);
            this.integerList.add(10);
            this.integerList.add(10);
            this.integerList.add(100);
            this.integerList.add(100);
            this.integerList.add(1_000);
            this.integerList.add(10_000);
            this.integerList.add(100_000);
            this.integerList.add(1_000_000);
        }

        public void testRawArrayList(){
            List list = new ArrayList();
            list.add("one");
            list.add("two");
            list.add(3);

            //for (String value : list) { } // Incompatible types, the raw list has Objects not String

            for(int i = 0; i < list.size(); i++){
                String value = (String) list.get(i);
            }
        }

        public void updateAllElements(){
            System.out.println(personList);
            personList.replaceAll(person -> {
                person.setAge(10);
                return person;
            });

            System.out.println(personList);
        }

        public static void main(String[] args) {
            TestArrayList testList = new TestArrayList();
            testList.integerList.remove(1); // removed by index
            testList.integerList.remove(Integer.valueOf(100));
            //testList.integerList.remove(10); // java.lang.IndexOutOfBoundsException:

            Predicate<Integer> removeIntegers = new Predicate<Integer>() {
                @Override
                public boolean test(Integer integer) {
                    return integer == 1000000;
                }
            };
            testList.integerList.removeIf(removeIntegers);

            testList.integerList.removeIf(i -> i == 100000);

            testList.integerList.removeIf(i -> {
                return i == 3;
            });

            testList.integerList.removeIf((Integer i) -> { return i >= 100 && i < 1500; });


            testList.integerList.removeAll(Arrays.asList(1, 10));
            System.out.println(testList.integerList);

            testList.updateAllElements();

            testList.personList.forEach(person -> System.out.print(person));
            testList.personList.forEach(System.out::print);
        }
    }
}
