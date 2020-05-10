package chapter1.objectMethods;

public class ToString {

    private String name;

    // String toString(){ } causes a compilation error cause package private modifier is a weaker than the overridden method

    /* this method overloads the method toString presented in Object class */
    public String toString(String value){
        return value;
    }

    @Override
    public String toString() {
        return "toString value";
    }

    public static void main(String[] args) {
        System.out.println(new ToString().toString());
        System.out.println(new ToString().toString("myName is"));
    }
}
