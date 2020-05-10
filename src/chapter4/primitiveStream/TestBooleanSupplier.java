package chapter4.primitiveStream;

import java.util.function.BooleanSupplier;

public class TestBooleanSupplier {

    public void testBooleanSupplier(){
        BooleanSupplier booleanSupplier1 = () -> Boolean.TRUE;
        BooleanSupplier booleanSupplier2 = () -> Math.random() % 2 == 0;
        System.out.println("Boolean Supplier1: " + booleanSupplier1.getAsBoolean());
        System.out.println("Boolean Supplier2: " + booleanSupplier2.getAsBoolean());
    }

    public static void main(String[] args) {
        TestBooleanSupplier testSupplier = new TestBooleanSupplier();
        testSupplier.testBooleanSupplier();
    }
}
