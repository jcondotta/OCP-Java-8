package chapter3;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TestMap {

    public Map<String, Integer> personMap = new HashMap<>();

    public TestMap() {
        fillMapData();
    }

    public void fillMapData(){
        personMap.clear();
        personMap.put("Jefferson", 31);
        personMap.put("William", 22);
        personMap.put("Feitosa", null);
    }

    // Works only the key isn't present or its value is null
    public void testPutIfAbsent(){
        System.out.println(personMap);
        personMap.putIfAbsent("Jefferson", 15);
        personMap.putIfAbsent("William", 39);
        personMap.putIfAbsent("Feitosa", 78);
        personMap.putIfAbsent("Condotta", null);
        System.out.println(personMap);
        System.out.println("");
        fillMapData();
    }

    // Works only the key isn't present or its value is null
    public void testComputeIfAbsent(){
        Function<String, Integer> function = (k) -> k.equals("Jefferson") ? 1 : 2;

        System.out.println(personMap);
        personMap.computeIfAbsent("Jefferson", function);
        personMap.computeIfAbsent("William", function);
        personMap.computeIfAbsent("Feitosa", function);
        personMap.computeIfAbsent("Condotta", function);
        System.out.println(personMap);
        System.out.println("");
        fillMapData();
    }

    // Works only the key is present and its value is not null
    public void testComputeIfPresent(){
        BiFunction<String, Integer, Integer> biFunction = (k, v) -> v * 2;

        System.out.println(personMap);
        System.out.println(personMap.computeIfPresent("Jefferson", biFunction));
        System.out.println(personMap.computeIfPresent("William", biFunction));
        System.out.println(personMap.computeIfPresent("Feitosa", biFunction));
        System.out.println(personMap.computeIfPresent("Condotta", biFunction));
        System.out.println(personMap);
        System.out.println("");
        fillMapData();
    }

    public void testMerge(){
        BiFunction<Integer, Integer, Integer> biFunction = (v1, v2) -> (v1 > v2 ? v1 : v2);
        BiFunction<Integer, Integer, Integer> biFunctionNull = (v1, v2) -> null;
        personMap.put("Jeffao", 100);

        System.out.println(personMap);
        System.out.println(personMap.merge("Jefferson", 40, biFunction));
        System.out.println(personMap.merge("William", 20, biFunction));
        System.out.println(personMap.merge("Feitosa", 10, biFunction));
        System.out.println(personMap.merge("Condotta", 100, biFunction));
//        System.out.println(personMap.merge("Jeffao", null, biFunction)); // NUllpointerException

        System.out.println(personMap);

        // when the function returns null the object in the map is removed
        System.out.println(personMap.merge("Condotta", 100, biFunctionNull));
        System.out.println(personMap);
        System.out.println("");
        fillMapData();
    }

    public static void main(String[] args) {
        TestMap testMap = new TestMap();
        testMap.testPutIfAbsent();
        testMap.testComputeIfAbsent();
        testMap.testComputeIfPresent();
        testMap.testMerge();
    }
}
