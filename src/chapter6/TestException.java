package chapter6;

import java.io.IOException;

public class TestException {



    public static void main(String[] args) {
        callExceptions(10);
        callExceptions(15);
        callExceptions(20);
        callExceptions(25);
    }

    private static void callExceptions(Integer intValue){
        try{
            if(intValue == 10){
                throw new UnsupportedOperationException();
            }
            else if(intValue == 15){
                throw new ArrayStoreException("ArrayStore");
            }
            else if(intValue == 20){
                throw new IOException("IoException");
            }
            else if(intValue == 25){
                throw new RuntimeException("RunTime");
            }

        }

        catch (UnsupportedOperationException e){
            e = new UnsupportedOperationException("Replacing the Instance");
            System.out.println(e.getMessage());
        }

        // DateTimeParse will be caught in the first Type
//        catch (DateTimeException | DateTimeParseException e){ }
        catch (NullPointerException | ArrayStoreException e){
            // e = new NullPointerException(); nao da pra saber qual o tipo da variable
            System.out.println(e.getMessage());
        }
//        catch (SQLException e){ }// is never thrown in this method
        catch (IOException e){
//            e = new Exception(); // tipos diferentes
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
//        catch (IOException e){} // Exception has already been caught
//        catch (Exception e){} // Exception has already been caught
    }

    class MyCheckedException extends Exception{
        public MyCheckedException() {
        }

        public MyCheckedException(String message) {
            super(message);
        }

        public MyCheckedException(Exception cause) {
            super(cause);
        }
    }

    class MyUncheckedException extends RuntimeException{
        public MyUncheckedException() {
        }

        public MyUncheckedException(String message) {
            super(message);
        }

        public MyUncheckedException(Throwable cause) {
            super(cause);
        }
    }

}
