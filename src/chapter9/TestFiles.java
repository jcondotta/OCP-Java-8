package chapter9;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class TestFiles {

    static String testFiles = "/opt/LocalRepository/OCP-JAVA-8/src/chapter9/files/testFiles.txt";

    static String directoryNewFilesTest = "/opt/LocalRepository/OCP-JAVA-8/src/chapter9/newFiles/test";

    public static void main(String[] args) {
        TestFiles testFiles = new TestFiles();

        Path absolutePath = Paths.get(TestFiles.testFiles);
        testFiles.printPathInfoUsingFiles(absolutePath);

        Path relativePath = Paths.get("src", "chapter9", "files", "nonExistentDirectory", "..","testFiles.txt");
        testFiles.printPathInfoUsingFiles(relativePath);

        Path symbolicLink = Paths.get("/opt/LocalRepository/");
        testFiles.printPathInfoUsingFiles(symbolicLink);


        testFiles.testIsSameFile();
        testFiles.testBaseCreateDirectories();
        testFiles.testCopyingDirectories();
        testFiles.movingRenaming();
        testFiles.deleteFiles();
    }


    public void movingRenaming(){
        Path testCopiedPath = Paths.get(directoryNewFilesTest).resolve("../testCopied");
        try {
            System.out.println("Renaming and Moving File: " + testCopiedPath.resolve("newTestFilesCopy.txt") + " to " +
                    Files.move(testCopiedPath.resolve("newTestFilesCopy.txt"), Paths.get(directoryNewFilesTest).resolve("renamedAndMovedFile.txt"), StandardCopyOption.REPLACE_EXISTING));
            System.out.println("Renaming and Moving Directory: " + testCopiedPath + " to " +
                    Files.move(testCopiedPath, Paths.get(directoryNewFilesTest).resolve("testCopiedAndMoved"), StandardCopyOption.REPLACE_EXISTING));

            System.out.println("Moving File: " + Paths.get(directoryNewFilesTest).resolve("renamedAndMovedFile.txt") + " to " +
                    Files.move(Paths.get(directoryNewFilesTest).resolve("renamedAndMovedFile.txt"), Paths.get(directoryNewFilesTest).resolve("testCopiedAndMoved/renamedAndMovedFile.txt"), StandardCopyOption.REPLACE_EXISTING));

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void deleteFiles(){
        try {
            Path testCopiedAndMovesPath = Paths.get(directoryNewFilesTest).resolve("testCopiedAndMoved");
            System.out.println("Deleting File: " + testCopiedAndMovesPath.resolve("renamedAndMovedFile.txt"));
            Files.delete(testCopiedAndMovesPath.resolve("renamedAndMovedFile.txt"));

            System.out.println("Deleting Directory: " + testCopiedAndMovesPath);
            Files.delete(testCopiedAndMovesPath);
        } catch (IOException e) {
            System.err.println(e);
        }
    }


    public void testCopyingDirectories(){
        Path newFilesTestPath = Paths.get(directoryNewFilesTest);

        try(InputStream inputStream = new FileInputStream(newFilesTestPath.resolve("newTestFiles.txt").toFile());
            OutputStream outputStream = new FileOutputStream(newFilesTestPath.resolve("newTestFilesCopyOutputStream.txt").toFile())
        ){
            //Files.copy(newFilesTestPath, newFilesTestPath.resolve("../testCopied")); // java.nio.file.FileAlreadyExistsException
            //Files.copy(newFilesTestPath, newFilesTestPath.resolve("../testCopied"), StandardCopyOption.REPLACE_EXISTING); //java.nio.file.DirectoryNotEmptyException:
            if(Files.notExists(newFilesTestPath.resolve("../testCopied"))){
                Files.copy(newFilesTestPath, newFilesTestPath.resolve("../testCopied"));
            }


            Files.copy(newFilesTestPath.resolve("newTestFiles.txt"), newFilesTestPath.resolve("../testCopied/newTestFilesCopy.txt"), StandardCopyOption.REPLACE_EXISTING);

            //Copying by Streams
            Files.copy(inputStream, newFilesTestPath.resolve("newTestFilesCopyInputStream.txt"), StandardCopyOption.REPLACE_EXISTING);
            if(Files.notExists(newFilesTestPath.resolve("newTestFilesCopyOutputStream.txt")))
                Files.copy(newFilesTestPath.resolve("newTestFiles.txt"), outputStream);
        }
        catch (IOException e){
            System.err.println(e);
        }

        // Create a directory test in /src/chapter9/

//        Path path = newFilesTestPath.resolve(Paths.get("../../"));
//        copyTestFilesTo());
//        Path newFilesTextPath = copyTestFiles(newFilesTestPath, newFilesTestPath.resolve("newTestFiles.txt"));
    }

    private void testBaseCreateDirectories(){
        try {
            // java.nio.file.NoSuchFileException: /opt/LocalRepository/OCP-JAVA-8/src/chapter9/newFiles/test
            //Path path = Files.createDirectory(Paths.get(createDirectories)); // soh se pode criar um diretorio por vez

            // java.nio.file.FileAlreadyExistsException: /opt/LocalRepository/OCP-JAVA-8/src/chapter9/files/testFiles.txt
//            Path path = Files.createDirectory(Paths.get(testFilesPath));
            Path testNewDirectories = Paths.get(directoryNewFilesTest);
            if(Files.notExists(testNewDirectories))
                System.out.println("Creating Directory: " + Files.createDirectories(Paths.get(directoryNewFilesTest)));

            if(Files.notExists(testNewDirectories.resolve("newTestFiles.txt"))) {
                System.out.println("Creating File: " + Files.createFile(testNewDirectories.resolve("newTestFiles.txt")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printPathInfoUsingFiles(Path path) {
        System.out.println("Path: " + path.toAbsolutePath());
        System.out.println("File exists: " + Files.exists(path));
        System.out.println("IsSymbolicLink: " + Files.isSymbolicLink(path));
        System.out.println("IsRegularFile: " + Files.isRegularFile(path));
        System.out.println("IsDirectory: " + Files.isDirectory(path));
        System.out.println("IsReadable: " + Files.isReadable(path));
        System.out.println("IsExecutable: " + Files.isExecutable(path));

//        try {
//            System.out.println("IsHidden: " + Files.isHidden(path));
//
//            //ModifiedTime
//            System.out.println("LastModificationTime: " + Files.getLastModifiedTime(path)); // Returns a FileTime
//            Files.setLastModifiedTime(path, FileTime.from(Instant.now()));
//            System.out.println("Updated LastModificationTime: " + Files.getLastModifiedTime(path));
//
//            //Owner
//
//            System.out.println("Owner: " + Files.getOwner(path));
//            UserPrincipal root = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("root");
//            Files.setOwner(path, root);
//            System.out.println("Updated Owner: " + Files.getOwner(path));
//
//
//            System.out.println("Size: " + Files.size(path)); // if doesn't exist IOException
//        } catch (IOException e) {
//            System.err.println(e);
//        }

        System.out.println();
    }

    public void testIsSameFile(){
        try {
            System.out.println(Files.isSameFile(Paths.get("/opt/LocalRepository"), Paths.get("../")));
            System.out.println(Files.isSameFile(Paths.get("/opt/LocalRepository"), Paths.get("../nonExistent")));
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
