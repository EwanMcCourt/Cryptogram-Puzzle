import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //user command interface
        System.out.print("Welcome, would you like to load a cryptogram or start a new game? ");
        Scanner inputReader = new Scanner(System.in);
        String input = inputReader.nextLine();
        while (!input.equals("exit")) {
            switch (input) {
                case "new":
                    System.out.println("Would you like to play a number cryptogram?" );
                    System.out.println("(Defaults to letter cryptogram)" );
                    input = inputReader.nextLine();
                    Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
                    Game game = new Game(player, input, "./src/phrases.txt");
                    game.getEncrypted().printDetails();
                    game.getEncrypted().parsedGuesses = game.parseInput();
                    while((!Objects.equals(game.getEncrypted().parsedGuesses, game.getEncrypted().phrase) && !input.equals("exit"))){
                        System.out.println("Do you want to add a guess or undo a guess?");
                        input = inputReader.nextLine();
                        switch (input) {
                            case "guess":
                                game.currentSol();
                                game.getEncrypted().guesses = game.enterLetter();
                                game.currentSol();
                                game.getEncrypted().parsedGuesses = game.parseInput();
                                player.updateAccuracy(player.getAccuracy());
                                if(!(game.getEncrypted().parsedGuesses.contains("?"))){
                                    System.out.println("fail!");
                                    player.incrementCryptogramsPlayed(player.getCryptogramsPlayed());
                                    player.printDetails();
                                }
                                break;
                            case "undo":
                                game.currentSol();
                                try {
                                    game.getEncrypted().guesses = game.undoLetter();
                                    game.currentSol();
                                    game.getEncrypted().parsedGuesses = game.parseInput();
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
                                break;
                            default:
                                System.out.println("Not guess or undo, try again!");
                                break;
                        }
                    }
                    System.out.println("Congrats you did it!!!");
                    player.incrementCryptogramsCompleted(player.getCryptogramsCompleted());
                    player.incrementCryptogramsPlayed(player.getCryptogramsPlayed());
                    player.printDetails();
                    System.out.println("Type new to make a new cryptogram or type exit to leave.");
                    input = inputReader.nextLine();
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
                default:
                    System.out.println("command not found, use 'help' for a list of commands");
                    System.out.print("Welcome, would you like to load a cryptogram or start a new game? ");
                    input = inputReader.nextLine();
                    break;
            }
        }
    }
}