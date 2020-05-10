package chapter1.objectMethods;

import java.util.Objects;

public class Equals {

    private int identifier;
    private String name;

    public Equals(int identifier) {
        this.identifier = identifier;
    }

    public int getIdentifier() {
        return identifier;
    }

    // @Override compile error cause this methods overloads the method equals presented in Object class
    public boolean equals(Equals equals){
        return identifier == equals.getIdentifier();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equals equals = (Equals) o;
        return identifier == equals.identifier &&
                Objects.equals(name, equals.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, name);
    }

    public static void main(String[] args) {
        Equals equals = new Equals(1);
        System.out.println(equals.equals(new Equals(2)));
    }
}
