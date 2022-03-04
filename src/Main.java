import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        /*
        //for testing - prints cryptogram of both types
        try {
            numberCryptogram a = new numberCryptogram();
            letterCryptogram b = new letterCryptogram();
            System.out.println(a.getMapping());
            System.out.println(b.getMapping());
        }
        catch(IOException e) {
            System.out.println("file not found");
        }
        */

        //user command interface
        System.out.print("Welcome, would you like to load a cryptogram or start a new game? ");
        Scanner inputReader = new Scanner(System.in);
        String input = inputReader.nextLine();
        while (!input.equals("exit")) {
            input = inputReader.nextLine();
            switch (input) {
                case "new":
                    break;
                case "help":
                    //print out what each command does
                    break;
                default:
                    System.out.print("command not found, use 'help' for a list of commands");
                    break;
            }
        }

    }
}
