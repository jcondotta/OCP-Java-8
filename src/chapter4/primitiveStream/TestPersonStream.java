package chapter4.primitiveStream;

import chapter3.Person;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;

public class TestPersonStream {

    public void testMapPersonToString(){
        Stream<Person> personStream = getPersonStream();
        Function<Person, String> functionPersonToString = (p -> p.getName());
        personStream.map(functionPersonToString).forEach(s -> System.out.println("My name is: "+ s));
    }

    public void testMapPersonToInt(){
        Stream<Person> personStream = getPersonStream();
        ToIntFunction<Person> toIntFunction = (p -> p.getAge());
        personStream.mapToInt(toIntFunction).forEach(s -> System.out.println("My Integer Age is: "+ s));
    }

    public void testMapPersonToLong(){
        Stream<Person> personStream = getPersonStream();
        ToLongFunction<Person> toLongFunction = (p -> p.getAge());
        personStream.mapToLong(toLongFunction).forEach(s -> System.out.println("My Long Age is: "+ s));
    }

    public void testMapPersonToDouble(){
        Stream<Person> personStream = getPersonStream();
        ToDoubleFunction<Person> toDoubleFunction = (p -> p.getAge());
        personStream.mapToDouble(toDoubleFunction).forEach(s -> System.out.println("My Double Age is: "+ s));
    }

    public static void main(String[] args) {
        TestPersonStream testPersonStream = new TestPersonStream();
        testPersonStream.testMapPersonToString();
        testPersonStream.testMapPersonToDouble();
        testPersonStream.testMapPersonToInt();
        testPersonStream.testMapPersonToLong();
    }

    public static Stream<Person> getPersonStream(){
        return Person.buildDefaultPersonList().stream();
    }
}
