package chapter1;

/* what members can be defined as static or final */


abstract class StaticAndFinal {

    static final String variableStaticAndFinal = "myValue";

    void method() {}
    static void methodStatic() {}
    static final void methodStaticAndFinal() {}

    static class MyInnerStaticClass{    } // Only Inner classes can be defined as static
}

abstract class MyAbstractClass extends StaticAndFinal{

    static final String variableStaticAndFinal = "myValue2";

    void method() {
        super.method();
    }

    static void methodStatic() {}
}
