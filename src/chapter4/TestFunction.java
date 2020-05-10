package chapter4;

import chapter3.Person;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TestFunction {

    public static void main(String[] args) {
        Function<String, Integer> function1 = String::length;
        Function<Person, String> function2 = (p) -> p.getName().toUpperCase();

        Person person = new Person("Didi");
        System.out.println(function1.apply(person.getName()));
        System.out.println(function2.apply(person));

        BiFunction<String, String, String> function3 = String::concat;
        BiFunction<Person, String, String> function4 = (p, s) -> p.getName().concat(s);

        System.out.println(function3.apply(person.getName(), " o melhor"));
        System.out.println(function4.apply(person, " o melhor"));
    }
}
