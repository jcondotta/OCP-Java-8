package chapter8.serialization;

import java.io.Serializable;

public class TestSerializableParent implements Serializable {

    protected transient int id;
    protected String name;

    public TestSerializableParent() {
        this.id = 10;
    }
}
