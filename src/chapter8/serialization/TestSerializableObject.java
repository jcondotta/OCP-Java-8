package chapter8.serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSerializableObject extends TestSerializableParent{

    static String testObjectFilePath = "src/chapter8/serialization/objectData.txt";

    public static String type = "Serializable";
    public TestSerializableMessage composingObject;

    public TestSerializableObject(int id, String name, String type) {
        super.id = id;
        super.name = name;
        this.type = type;
        this.composingObject = new TestSerializableMessage();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<TestSerializableObject> objects = Arrays.asList(new TestSerializableObject(1, "Jefferson", "Type1"),
                new TestSerializableObject(2, "Didi", "Type2"),
                new TestSerializableObject(3, "Pe de Porco", "Type3"));

        // Write objects
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(testObjectFilePath)))){
            for (TestSerializableObject object : objects){
                objectOutputStream.writeObject(object);
            }
        }

        objects = new ArrayList<>();
        // Read Objects
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(testObjectFilePath)))){
            while(true){
                Object object = objectInputStream.readObject();
                if(object instanceof TestSerializableObject) {
                    objects.add((TestSerializableObject) object);
                }
            }
        }
        catch (EOFException e){
            System.out.println("Acabou a leitura aqui minhas amigos");
        }


        objects.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "id:" + this.id + " - name: " + name +
                " - type: " + type + " - " + "message: " + composingObject.message;
    }
}
