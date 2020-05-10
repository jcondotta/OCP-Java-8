package chapter3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenericClass {

    // Case 1
    private interface ParamEqualsT<T> {
        public default T execute(){ return null; }
    }

    // Case 2
    private interface ParamExtendsObject<T extends Object> { }

    // Case 3
    private interface ParamExtendsNumber<T extends Number> { }

    // Classes

    // Case 1 Test
    private static class StaticMethodsParamEqualsT<T> implements ParamEqualsT<T> {

        public static <T> void staticExecute1 (T t){
            System.out.println("staticExecute1 with param <T>");
        }

        public static <T extends Number> T staticExecute2(T t){
            System.out.println("staticExecute2 with param <T extends Number>");
            return null;
        }

        public static <S extends Number, U> U staticExecute3(S s, U u){
            System.out.println("staticExecute3 with param <S extends Number, U>");
            return null;
        }

        public static void main(String[] args) {
            StaticMethodsParamEqualsT.staticExecute1(new String());
            StaticMethodsParamEqualsT.staticExecute2(new Integer(10));
            StaticMethodsParamEqualsT.staticExecute3(new Integer(10), new String("12345"));

            StaticMethodsParamEqualsT.<String>staticExecute1(new String());
            StaticMethodsParamEqualsT.<Integer>staticExecute2(new Integer(10));
            StaticMethodsParamEqualsT.<Integer,String>staticExecute3(new Integer(10), new String("12345"));
        }
    }
    private static class RawInputParamEqualsT<U> implements ParamEqualsT<U> {

        // Como nao tem generic a verificao so sera feita em runtime,
        public static void main(String[] args) {
            List numbers = new ArrayList();

            try{
                numbers.add(1);
                printStringData(numbers);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

            try{
                List<String> strings = new ArrayList<>();
                addString(strings);
                String string1 = strings.get(0); // ClassCastException the Integer presented in the list will not be cast
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

            // int i = numbers.get(0); Nao Compila, necessita cast
        }

        public static void printStringData(List<String> items){
            for(String item : items){ // Nesta linha o cast sera feito e uma classCastException sera lancado.
                System.out.println(item);
            }
        }

        public static void addString(List strings){
            strings.add(new Integer(10));
        }

    }
    private static class LowerBoundsParamEqualsT3<U extends String> implements ParamEqualsT<U> {

        public Integer getTotal(List<? extends Number> numbers){
            int total = 0;
            for(Number number : numbers){
                total += number.intValue();
            }
            numbers.remove(0);
//            numbers.add(new Integer(10)); Nao Compila
            return total;
        }

        public void test(){
            List<? super IOException> ioExceptions = new ArrayList<Exception>();
            // ioExceptions.add(new Exception()); Nao compila porque se o tipo da lista for IOException, Exception objects wouldn't fit there.
            ioExceptions.add(new IOException());
            ioExceptions.add(new FileNotFoundException());

            List<Exception> exceptions = new ArrayList<>();
            exceptions.add(new Exception());
            exceptions.add(new IOException());
            exceptions.add(new FileNotFoundException());

            List<Throwable> throwables = new ArrayList<>();
            throwables.add(new Exception());
            throwables.add(new IOException());
            throwables.add(new FileNotFoundException());

            List<Object> objects = new ArrayList<>();
            objects.add(new Exception());
            objects.add(new IOException());
            objects.add(new FileNotFoundException());

            testList(ioExceptions);
            testList(exceptions);
            testList(throwables);
            testList(objects);
        }

        public void testList(List<? super IOException> exceptions){
            System.out.println(exceptions);
        }

        public <T extends String, U extends Long> void print(U u, T t ){

        }

        public static void main(String[] args) {
            LowerBoundsParamEqualsT3<String> lowerBoundsParamEqualsT3 = new LowerBoundsParamEqualsT3();
            lowerBoundsParamEqualsT3.print(10L, "123");
        }

    }
    private class ClassNonParameterized implements ParamEqualsT {
        public <T> void print1(){ }
        public <T extends Number> void print2(){ }
        // public <T super Number> void print3(){ } Nao compila

        public <T> void print4(List<?> myList){ } // declara um tipo nao o utiliza
        public <T extends Number> void print5(List<T> myList){ }
        public <T> void print6(List<? extends Number> myList){ }
        public <T extends Number> void print7(List<T> myList){ }
        public <T extends Number> void print8(List<? extends String> myList){ }
        public <T extends Number> void print9
                (List<? super String> myList){ }


//        public <T> void print(List<? extends Number> list){
//
//        }
    }

    // Case 2 Test
    private class ExtendsParamExtendsObject1<T> implements ParamEqualsT<T> {}
    private class ExtendsParamExtendsObject2<U> implements ParamEqualsT<U> {}
    private class ExtendsParamExtendsObject3<T extends String> implements ParamEqualsT<T> {}
    private class ExtendsParamExtendsObject4 implements ParamEqualsT {}

    // Case 3 Test

    /* the class has to extends Number or wider in this case, otherwise the class can't ensure it's <T extends Number>
        private class ExtendsParamExtendsNumber1<T> implements ParamExtendsNumber<T> {}  */

    /* It can't refer to extends Object, cause it would accept String which doesn't extends Number
        private class ExtendsParamExtendsNumber1<U extends Object> implements ParamExtendsNumber<U> {} */

    private class ExtendsParamExtendsString1<T extends Number> implements ParamExtendsNumber<T> {}
    private class ExtendsParamExtendsString2<U extends Number> implements ParamExtendsNumber<U> {}
    private class ExtendsParamExtendsString3<U extends Integer> implements ParamExtendsNumber<U> {}
    private class ExtendsParamExtendsString4 implements ParamExtendsNumber {}
}
