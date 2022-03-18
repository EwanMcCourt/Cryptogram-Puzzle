import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputReader = new Scanner(System.in);



        System.out.print("Welcome, would you like to make a new player profile?");
        String input = inputReader.nextLine();

        if(input.equals("yes")){
            Players.addPlayer();
        }
            System.out.println("What is the username of the account you wish to play as?");
            input = inputReader.nextLine();
            Player player = Players.loadPlayer(input);
//            player.incrementCryptogramsCompleted();
//            player.incrementCryptogramsPlayed();


        //user command interface
        System.out.print("Welcome, would you like to load a cryptogram or start a new game? ");

         input = inputReader.nextLine();








        while (!input.equals("exit")) {
            switch (input) {
                case "new":
                    System.out.println("Would you like to play a number cryptogram?" );
                    System.out.println("(Defaults to letter cryptogram)" );
                    input = inputReader.nextLine();

                    Game game = new Game(player, input, "./src/phrases.txt");
                    game.getEncrypted().printDetails();
                    game.getEncrypted().parsedGuesses = game.parseInput();
                    while((game.getEncrypted().parsedGuesses.contains("?") && !input.equals("exit"))){
                        System.out.println("Do you want to add a guess or undo a guess?");
                        input = inputReader.nextLine().trim();
                        switch (input) {
                            case "guess":
                                System.out.println(game.getEncrypted().guesses);
                                game.currentSol();
                                game.enterLetter();
                                System.out.println(game.getEncrypted().guesses);
                                game.currentSol();
                                game.getEncrypted().parsedGuesses = game.parseInput();
                                player.updateAccuracy(player.getAccuracy());
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
                    if(!Objects.equals(game.getEncrypted().parsedGuesses, game.getEncrypted().phrase)){
                        if (input.equals("exit")) {
                            System.out.println("Fail by exit!");
                        }
                        else System.out.println("Fail!");
                    }
                    else{
                    System.out.println("Congrats you did it!!!");
                    player.incrementCryptogramsCompleted();}
                    player.incrementCryptogramsPlayed();
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
