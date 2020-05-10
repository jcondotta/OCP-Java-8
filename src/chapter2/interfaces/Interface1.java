package chapter2.interfaces;

public interface Interface1 {

    // variables in interface are public, static and final by default
    int value1 = 10;
    static int value2 = 10;
    final static int value3 = 10;
    static final int value4 = 10;
    public static final int value5 = 10;

    // methods are public and abstract by default
    void method1();
    abstract void method2();
    //public abstract void method3();
    //abstract public void method4();

    // default methods are public by default
    default int defaultMethod1(){ return 1; };
    public default void defaultMethod2(){ };
    default public void defaultMethod3(){ };

    // static methods are public by default
    static void staticMethod1(){
        System.out.println(value1);
    }

    static public void staticMethod2(){
        // value1++; variables on interfaces are final, their values cannot be changed
        System.out.println(value2);
    }





}
