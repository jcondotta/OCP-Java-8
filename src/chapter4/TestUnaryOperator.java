package chapter4;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class TestUnaryOperator {

    public static void main(String[] args) {
        UnaryOperator<String> unaryOperator1 = String::toUpperCase;
        UnaryOperator<Integer> unaryOperator2 = (x) -> x.hashCode();

        System.out.println(unaryOperator1.apply("Homer"));
        System.out.println(unaryOperator2.apply(25));

        BinaryOperator<Integer> unaryOperator3 = Integer::sum;
        BinaryOperator<Double> unaryOperator4 = Double::sum;

        System.out.println(unaryOperator3.apply(10, 20));
        System.out.println(unaryOperator4.apply(10.10, 20.87));
    }
}
