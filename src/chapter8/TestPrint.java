package chapter8;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class TestPrint {

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(System.out);

        writer.write(50); // Inherited from Writer (convert int to ASCII = 2)
        writer.write(System.lineSeparator());

        writer.print(50); // PrintWriter method (transform using String.valueOf())
        writer.write(System.lineSeparator());

        String string = null;
        // writer.write(string); // NullPointerException
        //writer.print(string); // Imprime Nada

        writer.println("Using println");
        writer.write(System.lineSeparator());

        writer.format("Hola mi llamo %s", "Jefferson");
        writer.write(System.lineSeparator());
        writer.printf("Hola otra vez mi llamo %s y vivo en %S", "Jefferson", "Barcelona");

        writer.write(System.lineSeparator());

        writer.flush();
    }


}
