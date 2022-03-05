import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
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
        boolean exit = false;
        while (exit == false) {
            switch (input) {
                case "new":
                    Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
                    Cryptogram encrypted = Game.generateCryptogram();
                    encrypted.printDetails();
                    //System.out.println(Encrypted.getFrequencies());
                    while (!Objects.equals(encrypted.parsedGuesses, encrypted.phrase) && exit == false ) {
                        System.out.println("Do you want to add a guess or undo a guess?");
                        input = inputReader.nextLine();
                        switch (input) {
                            case "guess":
                                Game.currentSol(encrypted, player);
                                encrypted.guesses = Game.enterLetter(encrypted, player);
                                Game.currentSol(encrypted, player);
                                encrypted.parsedGuesses = Game.parseInput(encrypted);
                                player.updateAccuracy(player.getAccuracy());
                                break;
                            case "undo":
                                Game.currentSol(encrypted, player);
                                try {
                                    encrypted.guesses = Game.undoLetter(encrypted, player);
                                    Game.currentSol(encrypted, player);
                                    encrypted.parsedGuesses = Game.parseInput(encrypted);
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Nothing to undo!!");
                                    break;
                                }
                            case "help":
                                System.out.println("new - generates a new cryptogram");
                                System.out.println("guess - begins a guess for the cryptogram");
                                System.out.println("undo - undos the last guess in the cryptogram");
                                System.out.println("help - shows list of commands and their function");
                                System.out.println("exit - exits the program");
                                break;
                            case "exit":
                                exit = true;
                                break;
                            default:
                                System.out.println("Not guess or undo, try again!");
                                break;
                        }

                    }

                    if (exit == true) {
                        break;
                    }
                    System.out.println("Congrats you did it!!!");
                    player.printDetails();
                    exit = true;
                    break;

                case "help":
                    System.out.println("new - generates a new cryptogram");
                    System.out.println("guess - begins a guess for the cryptogram");
                    System.out.println("undo - undos the last guess in the cryptogram");
                    System.out.println("help - shows list of commands and their function");
                    System.out.println("exit - exits the program");
                    System.out.print("Welcome, would you like to load a cryptogram or start a new game? ");
                    input = inputReader.nextLine();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("command not found, use 'help' for a list of commands");
                    System.out.print("Welcome, would you like to load a cryptogram or start a new game? ");
                    input = inputReader.nextLine();
                    break;
            }
        }

    }

}
