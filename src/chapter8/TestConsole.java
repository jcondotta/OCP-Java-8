package chapter8;

import java.io.*;

public class TestConsole {

    public static void main(String[] args) throws IOException {
        TestConsole testConsole = new TestConsole();
        //testConsole.testOldWayInteractingWithUsers();

        Console console = System.console();
        console.writer().println("Welcome to Apple");
        console.writer().print("Digite o seu username: ");
        String nameInput = console.readLine();
        console.writer().println("Agora sua senha: (ela sera criptografada)");
        char[] password = console.readPassword();

        console.format("%s, login efetuado com sucesso", nameInput);
    }

    private void testOldWayInteractingWithUsers() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite seu nome friend: ");
        String nameInput = bufferedReader.readLine();
        System.out.println("Thanks");

    }

}
