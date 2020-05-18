package chapter9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TestAttributeViews {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/chapter9/TestAttributeViews.java");
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        new TestAttributeViews().printAttributes(attributes);

        BasicFileAttributeView fileAttributeView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        new TestAttributeViews().printAttributes(fileAttributeView.readAttributes());

        fileAttributeView.setTimes(FileTime.from(Instant.now().minus(10, ChronoUnit.HOURS)), null, null);
        fileAttributeView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        new TestAttributeViews().printAttributes(fileAttributeView.readAttributes());
    }

    private void printAttributes(BasicFileAttributes basicFileAttributes){
        System.out.println("File Identifier: " + basicFileAttributes.fileKey());
        System.out.println("IsOther: " + basicFileAttributes.isOther());
        System.out.println("LastDateAccessed: " + basicFileAttributes.lastAccessTime());
        System.out.println("LastDateModified: " + basicFileAttributes.lastModifiedTime());
        System.out.println("DateCreated: " + basicFileAttributes.creationTime());
        System.out.println();
    }

//    private BasicFileAttributes getAttributes(Path path, Class<? extends BasicFileAttributes> classFileAttributes) throws IOException {
//        if(classFileAttributes == BasicFileAttributes.class){
//            return Files.readAttributes(path, classFileAttributes);
//        }
//        else if(classFileAttributes == BasicFileAttributeView.class){
//            BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
//            return basicFileAttributeView.readAttributes();
//        }
//    }
}
