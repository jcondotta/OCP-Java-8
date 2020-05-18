package chapter9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestWalkingFiles {

    public static void main(String[] args) throws IOException {
        Path chapter9 = Paths.get("src/chapter9");
        Files.walk(chapter9).filter(p -> Files.isRegularFile(p)).forEach(System.out::println);

        System.out.println();

        Stream<Path> pathStream = Files.find(Paths.get("src/chapter9"), Integer.MAX_VALUE, (p, a) -> Files.isDirectory(p));
        pathStream.forEach(System.out::println);

        System.out.println();

        new TestWalkingFiles().printFileNewWay();
        new TestWalkingFiles().printFileOldWay();
    }

    private void printFileNewWay() throws IOException {
        Files.lines(Paths.get("src/chapter9/TestWalkingFiles.java")).forEach(System.out::println);
    }

    private void printFileOldWay() throws IOException {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get("README.md"));
            BufferedWriter write = Files.newBufferedWriter(Paths.get("README_COPY.md"))){
            String currentLine = null;
            while((currentLine = reader.readLine()) != null){
                write.write(currentLine);
            }
        }
    }

}
