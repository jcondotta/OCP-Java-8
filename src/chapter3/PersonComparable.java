package chapter3;

import java.util.Comparator;

public class PersonComparable extends Person implements Comparable<PersonComparable> {

    public static Comparator<PersonComparable> nameComparator = Comparator.comparing(p -> p.getName());

    public PersonComparable(String name) {
        super(name);
    }

    @Override
    public int compareTo(PersonComparable p) {
        return nameComparator.compare(this, p);
    }
}