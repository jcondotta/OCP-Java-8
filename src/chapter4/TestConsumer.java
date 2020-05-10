package chapter4;

import chapter3.Person;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TestConsumer {

    public static void main(String[] args) {
        Person person = new Person("Jefferson");

        Consumer<Person> methodRef = System.out::println;
        Consumer<Person> lambda = (value) -> System.out.println(value);

        methodRef.accept(person);
        lambda.accept(person);

        BiConsumer<Person, String> methodRefUpdateName = Person::setName;
        BiConsumer<Person, String> lambdaUpdateName = ((p, s) -> p.setName(s));

        methodRefUpdateName.accept(person, "Jeffao");
        methodRef.accept(person);
        lambdaUpdateName.accept(person, "Jeffao da Massa");
        lambda.accept(person);
    }
}
