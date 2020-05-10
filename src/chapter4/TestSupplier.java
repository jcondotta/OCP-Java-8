package chapter4;

import chapter3.Person;

import java.util.function.Supplier;

public class TestSupplier {

    public static void main(String[] args) {
        Supplier<StringBuilder> methodRef = StringBuilder::new;
        Supplier<StringBuilder> lambda = () -> new StringBuilder();

        System.out.println(methodRef.get().append("Jefferson"));
        System.out.println(lambda.get().append("William"));
    }
}
