package chapter4;

import chapter3.Person;

import java.util.Arrays;
import java.util.Optional;

public class TestOptional {

    Person person;

    public TestOptional() {
        person = new Person("Jefferson");
    }

    public Optional<Person> getExistingPerson(String name){
        return name.equals(person.getName()) ? Optional.of(person) : Optional.empty();
    }

    public static void main(String[] args) {
        TestOptional testOptional = new TestOptional();

        Optional<Person> optPerson1 = testOptional.getExistingPerson("William");
        Optional<Person> optPerson2 = testOptional.getExistingPerson("Jefferson");

        if(optPerson1.isPresent()){
            System.out.println(optPerson1.get());
        }
        else{
            System.out.println("Getting Second Optional: " + optPerson2.get());
        }

        Optional<Double> opt = testOptional.average(100, 90);
        System.out.println(opt.get());

        Optional<Double> opt1 = testOptional.average();
        opt1.orElse(new Double(10));
        opt1.orElseGet(() -> Math.random());
        opt1.orElseThrow(() -> new UnsupportedOperationException());


    }

    public Optional<Double> average(int... scores){
        if(scores.length == 0){
            return Optional.empty();
        }
        else{
            double total = 0D;
            for (int score : scores){total += score;};
            return Optional.of(total / scores.length);
        }
    }
}
