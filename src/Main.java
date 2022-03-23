import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputReader = new Scanner(System.in);

        System.out.print("Welcome, would you like to make a new player profile? (yes/no) ");
        String input;
        Player player;
        do {
            input = inputReader.nextLine();
            if (input.equals("yes")) { //new player
                Players.addPlayer();
            }
            System.out.println("What is the username of the account you wish to play as? ");
            input = inputReader.nextLine();
            player = Players.loadPlayer(input);
            if (player == null) System.out.print("Player does not exist, would you like to make a new player profile? (yes/no) ");
        }
        while (player == null); //makes sure the player is either a new one or one that exists


        //user command interface

        System.out.print("Welcome, would you like to load a cryptogram or start a new game? ");
        input = inputReader.nextLine();
        Game game;
        boolean solvedByAi = false;
        while (!input.equals("exit")) {
            switch (input) {    //"exit" will exit the interface and program
                case "load": case "new":
                    if (input.equals("load")) {
                        game = new Game(player);
                        if(!game.loadGame()){
                            System.out.print("Enter new to start a new game or help for other commands ");
                            input = inputReader.nextLine();
                            break;
                        }
                    }
                    else {
                        player.incrementCryptogramsPlayed();
                        System.out.println("Would you like to play a number cryptogram?");
                        System.out.println("(Defaults to letter cryptogram)");
                        input = inputReader.nextLine();
                        game = new Game(player, input, "./src/phrases.txt");
                    }
                    game.getEncrypted().printDetails();
                    game.getEncrypted().parsedGuesses = game.parseInput();
                    while(!Objects.equals(game.getEncrypted().parsedGuesses, game.getEncrypted().phrase) && !input.equals("exit")){
                        System.out.println("Do you want to add a guess or undo a guess? Or even let the us solve it if things are too hard!");
                        input = inputReader.nextLine().trim();
                        switch (input) {
                            case "guess":       //User selects "guess" then makes their guess
                                game.currentSol();
                                game.enterLetter();
                                game.currentSol();
                                game.getEncrypted().parsedGuesses = game.parseInput();
                                player.updateAccuracy();
                                if (!(game.getEncrypted().parsedGuesses.contains("?"))&&!Objects.equals(game.getEncrypted().parsedGuesses, game.getEncrypted().phrase)){
                                    System.out.println("Incorrect solution! Keep trying..");
                                }
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
                                System.out.println("guess - begins a guess for the cryptogram");
                                System.out.println("undo - undos the last guess in the cryptogram");
                                System.out.println("help - shows list of commands and their function");
                                System.out.println("exit - exits to main menu");
                                break;
                            case "solve":
                                game.solve();
                                game.getEncrypted().parsedGuesses = game.parseInput();
                                solvedByAi = true;
                            case "exit":
                                break;
                            default:
                                System.out.println("command not found, use 'help' for a list of commands"); //Error message for invalid input
                                break;
                        }
                    }
                    if (input.equals("exit")) {
                        System.out.println("Would you like to save your game? (yes/no)");
                        input = inputReader.nextLine();
                        if (input.equals("yes")){
                            game.saveGame();      //Allows user to save their cryptogram game
                        }
                        else System.out.println("Fail by exit!");
                    }
                    if(solvedByAi){
                        System.out.println(game.getEncrypted().phrase + " was the solution!! Unlucky!");
                        game.removeSave();
                    }
                    else{
                    System.out.println("Congrats you did it!!!");     //Message upon completion of a cryptogram game
                    player.incrementCryptogramsCompleted();
                    game.removeSave();}
                    Players.savePlayers();
                    player.printDetails();
                    System.out.println("Type new to make a new cryptogram or type exit to leave.");
                    input = inputReader.nextLine();  //Allows user to try another cryptogram or exit the program
                    break;
                case "help":       //Displays information on what the user can prompt with their inputs
                    System.out.println("new - generates a new cryptogram");
                    System.out.println("load - loads a previously saved cryptogram");
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