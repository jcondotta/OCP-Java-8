package chapter4;

import chapter3.Person;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class TestPredicate {

    public static void main(String[] args) {
        Predicate<String> methodRef = String::isEmpty;
        Predicate<Integer> lambda = (value) -> value >= 18;

        Person person = new Person("Jefferson");

        System.out.println(methodRef.test(person.getName()));
        System.out.println(lambda.test(person.getAge()));

        BiPredicate<String, String> methodRefStartsWith = String::startsWith;
        BiPredicate<Person, String> lambdaIsCalledThis = (p,s) -> p.getName().equals(s);

        System.out.println(methodRefStartsWith.test("Jefferson","Jef"));
        System.out.println(lambdaIsCalledThis.test(person,"Jefferson"));
        System.out.println("");

        Predicate<Person> brazilian = (p) -> p.getNationality().equals("Brazilian");
        Predicate<Person> olderThan18 = (p) -> p.getAge() >= 18;

        Predicate<Person> brazilianOlderThan18 = brazilian.and(olderThan18);
        Predicate<Person> brazilianYoungerThan18 = brazilian.and(olderThan18.negate());

        System.out.println(person);
        System.out.println(brazilian.test(person));
        System.out.println(olderThan18.test(person));
        System.out.println(brazilianOlderThan18.test(person));
        System.out.println(brazilianYoungerThan18.test(person));




    }
}
