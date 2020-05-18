package chapter8;

import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestStreams {

    static String testFileRelativePath = "src/chapter8/textStreamData.txt";
    static String testCopiedFilePath = "src/chapter8/textStreamDataCopy.txt";

    public TestStreams() throws IOException {
        File file = new File(testFileRelativePath);
        if(file.exists()) {
            file.delete();
        }
        file.createNewFile();
        writeInitialContentFile();
        readTextStreamData();
        System.out.println();
    }

    private void writeInitialContentFile() throws IOException {
        try(OutputStream outputStream = new FileOutputStream(testFileRelativePath, false)) {
            outputStream.write("This is the initial content file.".getBytes());
            outputStream.write(System.lineSeparator().getBytes());

            for (int index = 10; index > 0; index--) {
                String fileContent = IntStream.rangeClosed(1, index).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
                outputStream.write(fileContent.getBytes());
                outputStream.write(System.lineSeparator().getBytes());
            }
        }
    }

    private void readTextStreamData() throws IOException {
        try(InputStream inputStream = new FileInputStream(testFileRelativePath)){
            int currentByte = 0;
            while((currentByte = inputStream.read()) != -1){
                System.out.print((char)currentByte);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        TestStreams testStreams = new TestStreams();
        testStreams.copyDataUsingBufferedStream();
    }

    private void copyDataUsingBufferedStream() throws IOException {
        try(InputStream inputStream = new BufferedInputStream(new FileInputStream(testFileRelativePath))){
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(testCopiedFilePath, true));

            byte[] buffer = new byte[1024];
            int lengthRead = 0;
            while((lengthRead = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, lengthRead);
                outputStream.flush();
            }
        }
    }
}
