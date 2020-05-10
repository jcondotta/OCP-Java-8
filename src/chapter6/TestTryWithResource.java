package chapter6;

import java.util.Arrays;

public class TestTryWithResource {

    public static void main(String[] args) throws Exception{
        try (AutoClose autoClose1 = new AutoClose("autoClose1");
             AutoClose autoClose2 = new AutoClose("autoClose2")){

            throw new RuntimeException("Try Block Exception");

            // AutoClose close() exceptions will get suppressed.

        }

        catch (IllegalStateException e){
            System.out.println("There is a runtimeException"); // Method doesn't pass by
        }
        catch (Exception e){
            System.out.println(e);
            if(e.getSuppressed() != null){
                System.out.println("Suppressed Exceptions");
                Arrays.stream(e.getSuppressed()).forEach(ex -> ex.printStackTrace());
            }
        }
        finally {
            System.out.println("Finally Block");
        }

    }

    static class AutoClose implements AutoCloseable {

        private String name;

        public AutoClose(String name) {
            this.name = name;
        }

        @Override
        public void close() throws Exception {
            throw new IllegalStateException("Closing: " + name);
            // Como a exception dentro do try eh a primaria, essa aqui nao eh testada no catch block
        }
    }
}
