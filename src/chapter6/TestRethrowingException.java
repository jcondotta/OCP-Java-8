package chapter6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.ParseException;

public class TestRethrowingException {

    public static String parseData() throws SQLException, ParseException {
        throw new SQLException();
    }

    public static void main(String[] args) throws SQLException, ParseException {
        Path p = null;
        try {
            parseData();
//            BufferedReader n = Files.newBufferedReader(p);
        } catch (SQLException | ParseException e) {
            System.out.println(e);
            throw e;
        }

        try {
            parseData();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

}
