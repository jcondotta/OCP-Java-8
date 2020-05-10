package chapter3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodReferences {

    static Comparator<Person> personAgeNameComparator = Comparator.comparing(Person::getAge).thenComparing(p -> p.getName());
    static Comparator<Person> personAgeComparator1 = Comparator.comparing(p -> p.getAge());
    static Comparator<Person> personNameComparator1 = (p1, p2) -> Person.compareByName(p1, p2);
    static Comparator<Person> personNameComparator2 = Person::compareByName;

    // Nao Compilam porque person nao implementa comparator
    //static Consumer<List<Person>> consumerMethodRef = Collections::sort;
    //static Consumer<List<Person>> consumerLambda = l -> Collections.sort(l);

    Consumer<List<PersonComparable>> consumerMethodRef = Collections::sort;
    Consumer<List<PersonComparable>> consumerLamba = l -> Collections.sort(l);

    final String name = "Jefferson";

    Predicate<String> startsWithMethodRef = name::startsWith;
    Predicate<String> startWithLambda = s -> name.startsWith(s);

    Predicate<String> isEmptyMethodRef = String::isEmpty;
    Predicate<String> isEmptyLambda = s -> s.isEmpty();

    Supplier<ArrayList> supplierMethodRef = ArrayList::new;
    Supplier<ArrayList> supplierLambda = () -> new ArrayList();

    public MethodReferences() {

    }

    public static void main(String[] args) {

    }


}
