package chapter8;

import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestReaderWriter {

    static String testFileRelativePath = "src/chapter8/textReaderWriterData.txt";
    static String testCopiedFilePath = "src/chapter8/textReaderWriterDataCopy.txt";

    public TestReaderWriter() throws IOException {
        File file = new File(testFileRelativePath);
        if(file.exists()) {
            file.delete();
        }
        file.createNewFile();
        writeInitialContentFile();
        readTextReaderWriteData();
        System.out.println();
    }

    private void writeInitialContentFile() throws IOException {
        try(Writer writer = new FileWriter(testFileRelativePath)) {
            writer.write("This is the initial content file.");
            writer.write(System.lineSeparator());

            for (int index = 10; index > 0; index--) {
                String fileContent = IntStream.rangeClosed(1, index).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
                writer.write(fileContent);
                writer.write(System.lineSeparator());
            }
        }
    }

    private void readTextReaderWriteData() throws IOException {
        try(Reader reader = new FileReader(testFileRelativePath)){
            int currentByte = 0;
            while((currentByte = reader.read()) != -1){
                System.out.print((char)currentByte);
            }
        }
    }

    private void copyDataUsingBufferedWriterReader() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(testFileRelativePath))){
            BufferedWriter writer = new BufferedWriter(new FileWriter(testCopiedFilePath));

            String currentLine;
            while((currentLine = reader.readLine()) != null){
                System.out.println(currentLine);
                writer.write(currentLine);
                writer.newLine();
                writer.flush();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        testReaderWriter.copyDataUsingBufferedWriterReader();
    }
}
