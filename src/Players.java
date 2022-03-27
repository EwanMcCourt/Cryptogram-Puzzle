import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
                writing.append( username + " " + 0.0 + " " + 0 + " " + 0 + " " + 0 + " "+0 );
            } else {



                writing.append("\n" + username + " " + 0.0 + " " + 0 + " " + 0 + " " + 0+ " "+0 );
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
                writing.append(players.get(i).getUsername() + " " + players.get(i).getAccuracy() + " " + players.get(i).getnumCorrectGuesses() + " " + players.get(i).getTotalGuesses() + " " + players.get(i).getCryptogramsCompleted() + " " + players.get(i).getCryptogramsPlayed() + "\n");
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



                String username = input.next();

                double accuracy = input.nextDouble();

                int numCorrectGuesses = input.nextInt();

                int totalGuesses = input.nextInt();

                int cryptogramsCompleted = input.nextInt();

                int cryptogramsPlayed = input.nextInt();

                players.add(new Player(username, accuracy, totalGuesses, cryptogramsPlayed, cryptogramsCompleted, numCorrectGuesses));
            }
            input.close();

        } catch (InputMismatchException | FileNotFoundException e) {
            System.err.format("Sorry, either the file does not exist or a vital component from them is missing\n");
        }
        return players;
    }

    //Currently, displays players unordered
    public static void displayTopTenPlayers() {

        List<Player> topPlayers = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {


                String username = input.next();

                double accuracy = input.nextDouble();

                int numCorrectGuesses = input.nextInt();

                int totalGuesses = input.nextInt();

                int cryptogramsCompleted = input.nextInt();

                int cryptogramsPlayed = input.nextInt();

                topPlayers.add(new Player( username, accuracy, totalGuesses, cryptogramsPlayed, cryptogramsCompleted, numCorrectGuesses));
            }
            input.close();

        } catch (InputMismatchException | FileNotFoundException e) {
            System.err.format("Sorry, either the file does not exist or a vital component from them is missing\n");
        }
        topPlayers.sort(Comparator.comparing(Player::getCryptogramsCompleted, Comparator.reverseOrder()));
        if (topPlayers.size()<10){
            System.out.println("The top ten players are: ");
            for (int i = 0; i < topPlayers.size(); i++) {
                System.out.println(topPlayers.get(i));
            }
        }else{

       System.out.println("The top ten players are: ");
        for (int i = 0; i < 10; i++) {
            System.out.println(topPlayers.get(i));
        }
        }
    }

    //loads player based on id(line number)
    public static Player loadPlayer(String givenUsername){
        loadPlayers();
        Player playerInfo = null;
        Scanner reader= null;

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


        reader.close();

        return playerInfo;
    }


}
