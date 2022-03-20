import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Players {
    static List<Player> players = new ArrayList<>();
    static File file = new File("PlayerInfo.txt");

    //Adds a new player to the file
    public static void addPlayer() {
        Players.loadPlayers();
        Scanner scan = new Scanner(System.in);

        FileWriter writing = null;
        try {
            Scanner input = new Scanner(file);
            writing = new FileWriter(file, true);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();


            System.out.println("Please enter your unique username");
            String username = scan.nextLine();   //User inputs their username
            while (username.length() == 0){
                System.out.println("Invalid input, please try again");
                username = scan.nextLine();
            }

            //Checks if username already exists
            while (input.hasNext()) {
                String check = input.next();
                while (username.equals(check)) {
                    System.out.println("This username exists already, please try again");
                    username = scan.nextLine();
                }
            }

            //Checks if players file is empty before writing to a new line
            if (line == null) {
                writing.append(0 + " " + username + " " + 0.0 + " " + 0 + " " + 0 + " " + 0 + " "+0 );
            } else {
                int amountOfPlayers = 1;

                for (int i = 1; i < players.size(); i++) {
                    amountOfPlayers++;
                }

                writing.append("\n" + amountOfPlayers++ + " " + username + " " + 0.0 + " " + 0 + " " + 0 + " " + 0+ " "+0 );
            }
            writing.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadPlayers();
        savePlayers();
    }

    public static void savePlayers() {
        FileWriter writing;
        try {
            Scanner input = new Scanner(file);
            writing = new FileWriter(file);   //Saves the users details
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (int i = 0; i < players.size(); i++) {
                writing.append( i + " " + players.get(i).getUsername() + " " + players.get(i).getAccuracy() + " " + players.get(i).getnumCorrectGuesses() + " " + players.get(i).getTotalGuesses() + " " + players.get(i).getCryptogramsCompleted() + " " + players.get(i).getCryptogramsPlayed() + "\n");
            }
            writing.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

        //Currently, loads players unordered
    public static List<Player> loadPlayers() {
        try {
            players.clear();
            Scanner input = new Scanner(file);

            while (input.hasNext()) {

                int id = input.nextInt();

                String username = input.next();

                double accuracy = input.nextDouble();

                int numCorrectGuesses = input.nextInt();

                int totalGuesses = input.nextInt();

                int cryptogramsCompleted = input.nextInt();

                int cryptogramsPlayed = input.nextInt();

                players.add(new Player(id, username, accuracy, totalGuesses, cryptogramsPlayed, cryptogramsCompleted, numCorrectGuesses));
            }
            input.close();

        } catch (InputMismatchException | FileNotFoundException e) {
            System.err.format("Sorry, either the file does not exist or a vital component from them is missing\n");
        }
        return players;
    }

    //Currently, displays players unordered
    public static void displayPlayers() {
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                int id = input.nextInt();

                String username = input.next();

                double accuracy = input.nextDouble();

                int numCorrectGuesses = input.nextInt();

                int totalGuesses = input.nextInt();

                int cryptogramsCompleted = input.nextInt();

                int cryptogramsPlayed = input.nextInt();

                players.add(new Player(id, username, accuracy, totalGuesses, cryptogramsPlayed, cryptogramsCompleted, numCorrectGuesses));
            }
            input.close();

        } catch (InputMismatchException | FileNotFoundException e) {
            System.err.format("Sorry, either the file does not exist or a vital component from them is missing\n");
        }
        for (int i = 0; i < players.size(); i++) {
            System.out.println(i + " " + players.get(i).getUsername() + " " + players.get(i).getAccuracy() + " " + players.get(i).getTotalGuesses() + " " + players.get(i).getCryptogramsPlayed() + " " + players.get(i).getCryptogramsCompleted());
        }
    }

    //loads player based on id(line number)
    public static Player loadPlayer(String givenUsername){
        loadPlayers();
        Player playerInfo = null;
        Scanner reader= null;

        //Checks for valid id
//        if (id > players.size() || id < 0) {
//            System.out.println("Sorry, that id is invalid.");
//            return playerInfo;
//        }
        try {
            reader = new Scanner(file);
            for(int i =0;i<players.size();i++){

                if(players.get(i).getUsername().equals(givenUsername)){

                    playerInfo =players.get(i);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //     playerInfo = Files.readAllLines(Paths.get("PlayerInfo.txt")).get(id);
        reader.close();
        
        return playerInfo;
    }

    //displays player based on id(line number)
    public static void displayPlayer(int id) {
        loadPlayers();
        String playerInfo = null;

        //Checks for valid id
        if (id > players.size() || id < 0) {
            System.out.println("Sorry, that id is invalid.");
        }
        try {

            playerInfo = Files.readAllLines(Paths.get("PlayerInfo.txt")).get(id);

        } catch (IOException e) {
            System.err.format("Sorry, either the file does not exist or a vital component from them is missing\n");
        }
        System.out.println(playerInfo);
    }
}