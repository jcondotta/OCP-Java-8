package chapter3;

import java.util.Comparator;
import java.util.TreeSet;

public class TestSet {

    static Comparator<Person> ageComparator = (p1, p2) -> (p1.age - p2.age);
    static Comparator<Person> personComparator = Comparator.comparing(p -> p.age);

    static class TestTreeSet {

        TreeSet<Integer> intergerSet = new TreeSet<>();
        TreeSet<PersonComparable> personSet = new TreeSet<>();

        public TestTreeSet() {
            // personSet.add(new Person("Jefferson")); java.lang.ClassCastException: chapter3.TestSet$Person cannot be cast to java.lang.Comparable
            personSet.add(new PersonComparable("Condotta"));
            personSet.add(new PersonComparable("Feitosa"));
            personSet.add(new PersonComparable("William"));
            personSet.add(new PersonComparable("Jefferson"));

            intergerSet.add(0); intergerSet.add(2); intergerSet.add(4);
            intergerSet.add(6); intergerSet.add(8); // intergerSet.add(null); NullPointerException
        }

        public static void main(String[] args) {
            TestTreeSet testTreeSet = new TestTreeSet();
            //testTreeSet.testTreeSetMethods();

            System.out.println(testTreeSet.personSet);

            TreeSet treeSetWithComparator = new TreeSet(ageComparator);
            treeSetWithComparator.addAll(testTreeSet.personSet.tailSet(new PersonComparable("Feitosa")));
            System.out.println(treeSetWithComparator);

            TreeSet treeSetByOther = new TreeSet(treeSetWithComparator);
            System.out.println(treeSetByOther);

            //Collections.sort(new Person("Nao Compila")); Necessita implementar Comparable
        }

        public void testTreeSetMethods(){
            System.out.println(intergerSet.lower(2)); // 0
            System.out.println(intergerSet.lower(3)); // 2
            System.out.println(intergerSet.lower(0)); // null

            System.out.println(intergerSet.floor(2)); // 2
            System.out.println(intergerSet.floor(1)); // 0

            System.out.println(intergerSet.headSet(5)); // 0, 2, 4
            System.out.println(intergerSet.headSet(6)); // 0, 2, 4
            System.out.println(intergerSet.headSet(6, true)); // 0, 2, 4, 6

            System.out.println("");

            System.out.println(intergerSet.higher(5)); // 6
            System.out.println(intergerSet.higher(6)); // 8

            System.out.println(intergerSet.ceiling(5)); // 6
            System.out.println(intergerSet.ceiling(6)); // 6

            System.out.println(intergerSet.tailSet(5)); // 6, 8
            System.out.println(intergerSet.tailSet(6)); // 6,8
            System.out.println(intergerSet.tailSet(6, false)); // 8
        }
    }
}
