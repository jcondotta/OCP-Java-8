package chapter8.serialization;

import java.io.Serializable;

public class TestSerializableMessage implements Serializable {

    String message = "Default message";

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "TestSerializableMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
