package chapter9;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestPath {

    static String testPathFile = "/opt/LocalRepository/OCP-JAVA-8/src/chapter9/files/testPath.txt";

    public static void main(String[] args) throws URISyntaxException {
        TestPath testPath = new TestPath();

        Path absoluteSymbolLinkPath = Paths.get(testPathFile);
        testPath.printPathInfo(absoluteSymbolLinkPath);

        Path relativePath = Paths.get("src", "chapter9", "files");
        testPath.printPathInfo(relativePath);

        Path uriPath = Paths.get(new URI("file:///opt/LocalRepository/OCP-JAVA-8/src/chapter9/files/testPath.txt"));
        testPath.printPathInfo(uriPath);

        Path nonExistentPath = FileSystems.getDefault().getPath("/test/Jefferson");
        testPath.printPathInfo(nonExistentPath);

        Path pathBySubPath = absoluteSymbolLinkPath.subpath(0, 6).resolve("testPath.txt");
        testPath.printPathInfo(pathBySubPath);

        testPath.testPathRelativizeNormalize();
        testPath.testRealPath();
    }

    private void testRealPath(){
        try {
            Path absoluteSymbolLinkPath = Paths.get("/opt/LocalRepository");
            Path javaOCPProject = Paths.get("/opt/LocalRepository").resolve("OCP-JAVA-8");
            Path nonExistentPath = Paths.get("/opt/LocalRepository/nonExistentDirectory");

            System.out.println(absoluteSymbolLinkPath.toRealPath()); ///Users/jeffersoncondotta/Development/LocalRepository
            System.out.println(absoluteSymbolLinkPath.toRealPath(LinkOption.NOFOLLOW_LINKS)); ///opt/LocalRepository
            System.out.println(nonExistentPath.toRealPath());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void testPathRelativizeNormalize(){
        System.out.println("Testing Relativize");
        Path pathChapter9 = Paths.get("src/chapter9/files");
        Path pathSrc = Paths.get("src");

        System.out.println(pathChapter9.relativize(pathSrc));
        System.out.println(pathSrc.relativize(pathChapter9));

        Path pathTestFile = Paths.get(testPathFile);
        Path homePath = Paths.get("/Users/jeffersoncondotta");

        System.out.println(pathTestFile.relativize(homePath));
        System.out.println(homePath.relativize(pathTestFile));

        System.out.println();
        System.out.println("Testing Normalize");

        Path pathLocalRepository = Paths.get("/opt/LocalRepository");
        System.out.println(homePath.resolve(homePath.relativize(pathLocalRepository)).normalize());

    }

    public void printPathInfo(Path path){
        System.out.println("Root Path: " + path.getRoot());
        System.out.println("Path Name: " + path.getFileName());

        String elements = IntStream.iterate(0, x -> ++x).limit(path.getNameCount())
                .mapToObj(x -> "Element" + x + " " + path.getName(x)).collect(Collectors.joining(", "));
        System.out.println(elements);
        System.out.println("IsAbsolute: " + path.isAbsolute());
        System.out.println("Absolute Path: " + path.toAbsolutePath() + " - are the instances equals? " + (path.toAbsolutePath() == path));
        System.out.println("ToURI: " + path.toUri().toString());
        System.out.println("Converts Path to File: " + path.toFile());

        if(path.toString().equals(testPathFile)){
            Path currentParent = path;
            System.out.print("Parent Path: ");
            while((currentParent = currentParent.getParent()) != null){
                System.out.print(" - " + currentParent.toString());
            }
            System.out.println();
        }
        else{
            System.out.println("Parent Path: " + path.getParent());
        }


        System.out.println();
    }
}
