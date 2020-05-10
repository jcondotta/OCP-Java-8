package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Person {

    protected String name;
    protected int age;
    protected String nationality;
    protected double salary;

    private Random random = new Random();
    public Person(String name) {
        this.name = name;
        this.age = random.nextInt(30);
        this.nationality = (age > 18) ? "Brazilian" : "Italian";
        this.salary = random.nextDouble();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static int compareByName(Person person1, Person person2){
        return person1.getName().compareTo(person2.getName());
    }

    public static List<String> getDefaultPersonNameList(){
        return Arrays.asList("Jefferson", "Jonathan", "Aldersen", "Anderson", "Gerson");
    }

    public static List<Person> buildDefaultPersonList(){
        List<Person> defaultPersonList = new ArrayList<>();
        getDefaultPersonNameList()
                .stream()
                .forEach(name -> defaultPersonList.add(new Person(name)));

        return defaultPersonList;
    }
}