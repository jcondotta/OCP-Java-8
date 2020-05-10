package chapter6;

public class TestAssertion {

    public static void main(String[] args) {
        String value = null;
        int a = 2;
        assert a++ > 0;
        assert (value != null) : ("Falhou");
        System.out.println("If you see this message, it means assertion is disable");


        // java -da -ea:TestAssertion TestAssertion (disable nothing, enable Test, runs Test)
        // java -ea:chapter6...  -> enable all classes in chapter 6 package
        // java -ea chapter6.TestAssertion -> enable the class and runs it
        // java -ea:chapter6... -da:chapter6.TestAssertion  -> enable all classes in chapter 6 package
        // java -ea:chapter5... chapter6.TestAssertion  -> enable all classes in chapter 5 package and run TestAssertion
    }
}
