package chapter8;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TestFile {

    static String chapter8Path = "/chapter8";

    public static void main(String[] args) throws IOException {
        TestFile testFile = new TestFile();
        testFile.testFileAbsolutePath();
        System.out.println();
        testFile.testFileRelativePath();
    }

    public void testFileAbsolutePath() throws IOException {
        File developmentHome = new File(System.getenv("DEVELOPMENT_HOME"));
        File absolutePathFile = new File(developmentHome + "/LocalRepository/OCP-Java-8/src");
        if(absolutePathFile.exists()){
            printFileInfo(absolutePathFile);
        };

        File fileChapter8 = new File(absolutePathFile + chapter8Path);
        fileChapter8.mkdir();

        File chapter8Folders = new File(fileChapter8 + "/test/file");
        chapter8Folders.mkdirs();
        chapter8Folders.renameTo(new File(fileChapter8 + "/test/stream"));
    }

    public void testFileRelativePath() throws IOException {
        File relativePathFile = new File("src");
        if(relativePathFile.exists()){
            System.out.println("RelativePathFile Info:");
            printFileInfo(relativePathFile);
        }

        File fileChapter8 = new File(relativePathFile + chapter8Path);
        File fileChapter8Test = new File(fileChapter8 + "/test");
        System.out.println("Has Deleted Chapter8/Test: " + fileChapter8Test.delete());

        new File(fileChapter8Test + "/stream").delete();
        System.out.println("Has Deleted Chapter8/Test: " + fileChapter8Test.delete());
    }

    public static void printFileInfo(File file) throws IOException {
        System.out.println("Name: " + file.getName());
        System.out.println("AbsolutePath: " + file.getAbsolutePath());
        System.out.println("CanonicalPath: " + file.getCanonicalPath());
        System.out.println("IsDirectory: " + file.isDirectory());
        System.out.println("IsFile: " + file.isFile());
        System.out.println("Length: " + file.length());
        System.out.println("LastModified: " + file.lastModified());
        System.out.println("ParentPath: " + file.getParent());

        System.out.println("List Files: ");
        Arrays.stream(file.listFiles()).forEach(System.out::println);
    }
}
