import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputReader = new Scanner(System.in);

        System.out.print("Welcome, would you like to make a new player profile?");
        String input = inputReader.nextLine();    //User can create a new profile

        if(input.equals("yes")){
            Players.addPlayer();
        }
            System.out.println("What is the username of the account you wish to play as?");
            input = inputReader.nextLine();           //Creates username from user input
            Player player = Players.loadPlayer(input);
//            player.incrementCryptogramsCompleted();
//            player.incrementCryptogramsPlayed();

        //user command interface
        System.out.print("Welcome, would you like to load a cryptogram or start a new game? ");

         input = inputReader.nextLine();

        Game game;
        while (!input.equals("exit")) {
            switch (input) {    //"exit" will exit the interface and program
                case "load": case "new":
                    if (input.equals("load")) {
                        game = new Game(player);
                        game.loadGame();            //Loads a preciously saved game
                    }
                    else {
                        System.out.println("Would you like to play a number cryptogram?");
                        System.out.println("(Defaults to letter cryptogram)");
                        input = inputReader.nextLine();
                        game = new Game(player, input, "./src/phrases.txt");
                    }
                    game.getEncrypted().printDetails();
                    game.getEncrypted().parsedGuesses = game.parseInput();
                    while((game.getEncrypted().parsedGuesses.contains("?") && !input.equals("exit"))){
                        System.out.println("Do you want to add a guess or undo a guess?");
                        input = inputReader.nextLine().trim();
                        switch (input) {
                            case "guess":       //User selects "guess" then makes their guess
                                game.currentSol();
                                game.enterLetter();
                                game.currentSol();
                                game.getEncrypted().parsedGuesses = game.parseInput();
                                player.updateAccuracy(player.getAccuracy());
                                break;
                            case "undo":      //User selects "undo" which reverts their previous guess
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
                            case "help":    //Displays information on what the user can prompt with their inputs
                                System.out.println("new - generates a new cryptogram");
                                System.out.println("guess - begins a guess for the cryptogram");
                                System.out.println("undo - undos the last guess in the cryptogram");
                                System.out.println("help - shows list of commands and their function");
                                System.out.println("exit - exits the program");
                                break;
                            case "exit":
                                break;
                            default:
                                System.out.println("Not guess or undo, try again!"); //Error message for invalid guess
                                break;
                        }
                    }
                    if(!Objects.equals(game.getEncrypted().parsedGuesses, game.getEncrypted().phrase)){
                        if (input.equals("exit")) {
                            System.out.println("Woulds you like to save your game? (yes/no)");
                            input = inputReader.nextLine();
                            if (input.equals("yes")){
                                game.saveGame();      //Allows user to save their cryptogram game
                            }
                            else System.out.println("Fail by exit!");
                        }
                        else System.out.println("Fail!");
                    }
                    else{
                    System.out.println("Congrats you did it!!!");     //Message upon completion of a cryptogram game
                    player.incrementCryptogramsCompleted();}
                    player.incrementCryptogramsPlayed();
                    player.printDetails();
                    System.out.println("Type new to make a new cryptogram or type exit to leave.");
                    input = inputReader.nextLine();  //Allows user to try another cryptogram or exit the program
                    break;
                case "help":       //Displays information on what the user can prompt with their inputs
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
                    input = inputReader.nextLine();    //Allows user to try another cryptogram or exit the program
                    break;                             //(Or ask for help)
            }
        }
    }
}