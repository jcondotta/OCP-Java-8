package chapter2.interfaces;

public class ClassTest implements Interface3, Interface2 {

    //@Override
    // void method1(){ } it wouldn't work cause any interface inherited method must be public

    public static void main(String[] args) {
        ClassTest classTest = new ClassTest();
        classTest.defaultMethod1();
        classTest.method2();
    }

    /* if 2 interfaces got the same signature method inherited by a concrete class, this class has to override the method */
    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

    @Override
    public int defaultMethod1() {
        return 0;
    }

    @Override
    public void defaultMethod2() {

    }
}
