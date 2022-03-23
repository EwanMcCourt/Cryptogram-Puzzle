import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class Game {
    static HashMap<Game, Player> playerGameMapping;
    static File saveFile = new File("SaveGames.txt");
    ;
    private Cryptogram encrypted;
    private Player currentPlayer;

    public Game(Player player, String type, String file) throws NullPointerException {
        this.encrypted = Cryptogram.newCryptogram(type, file);
        this.currentPlayer = player;
        try {
            playerGameMapping.put(this, player);         //Maps user details to the new game
        } catch (NullPointerException e) {
            playerGameMapping = new HashMap<>();
            playerGameMapping.put(this, player);
        }
    }

    public Game(Player player) {
        this.currentPlayer = player;
    }

    public Cryptogram getEncrypted() {
        return encrypted;
    }

    public ArrayList<String> enterLetter() {
        Scanner object = new Scanner(System.in);
        String target;
        String guess;
        String type;
        if (encrypted.isLetter) type = "letter"; //Allows user to guess a letter and checks if it is an acceptable input
        else type = "number";
        System.out.println("What letter do you want to guess?");
        target = object.next();
        while ((type.equals("letter") && target.length() > 1) || target.length() > 2) {
            System.out.println("Input too long.");
            System.out.println("What " + type + " do you want to guess?");
            target = object.next();
        }
        while (!encrypted.fullEncrypt.contains(target)) {
            System.out.println("That " + type + " isn't in this cryptogram try again!");
            System.out.println("What " + type + " do you want to guess?");  //Message for when a guess is wrong
            target = object.next();
        }
        System.out.println("What is your guess?");
        guess = object.next();
        while (guess.length() > 1) {
            System.out.println("Your guess is too long!!");    //Message for if the guess it too long
            System.out.println("What is your guess?");
            guess = object.next();
        }
        while (encrypted.guesses.contains(" " + guess)) {
            System.out.println("This letter has already been guessed");  //Message for when a guess is repeated
            System.out.println("What is your guess?");
            guess = object.next();
        }
        String conform = null;
        if (!encrypted.guesses.get(encrypted.fullEncrypt.indexOf(target)).equals(" ?")) {
            System.out.println("This letter is already mapped are you sure you want to overwrite? (yes or no)");
            conform = object.next();
            while (!conform.equals("yes") && !conform.equals("no")) {      //Allows user to change their previous guess
                System.out.println("Invalid input, try again...");
                conform = object.next();
            }
        }
        if (Objects.equals(conform, "yes") || Objects.equals(conform, null)) {
            applyGuess(target, guess);
        }
        return encrypted.guesses;
    }

    public void applyGuess(String target, String guess) {
        for (int i = 0; i < encrypted.fullEncrypt.size(); i++) {
            if (target.equals(encrypted.fullEncrypt.get(i))) {
                encrypted.guesses.set(i, " " + guess);
            }
        }
        currentPlayer.updateTotalGuesses(currentPlayer.getTotalGuesses() + 1);
        if (Objects.equals(encrypted.cryptogramAlphabet.get(guess.charAt(0)), target)) {
            currentPlayer.updatenumCorrectGuesses(currentPlayer.getnumCorrectGuesses() + 1);
        }
    }

    public ArrayList<String> undoLetter() {
        Scanner reader = new Scanner(System.in);
        System.out.println("What guess are you unmapping?");
        String remove = reader.next();                  //Removes a previous guess (unmaps the letter)
        if (encrypted.guesses.contains(" " + remove)) {
            while (encrypted.guesses.contains(" " + remove)) {
                int i = encrypted.guesses.indexOf(" " + remove);
                encrypted.guesses.set(i, " ?");
            }
        } else {
            System.out.println("That letter is not mapped so there is nothing to undo");
        }
        return encrypted.guesses;
    }


    public static Integer getKeyByValue(HashMap<Integer, String> labeledMap, String value) {
        for (Map.Entry<Integer, String> entry : labeledMap.entrySet()) {
            if (Objects.equals(value, entry.getValue().trim())) {
                return entry.getKey();
            }
        }
        return null;
    }


    public void currentSol() {
        for (int i = 0; i < encrypted.fullEncrypt.size(); i++) {
            if (encrypted.fullEncrypt.get(i).charAt(0) == ' ') {
                System.out.print(encrypted.fullEncrypt.get(i) + " ");
            } else if (encrypted.fullEncrypt.get(i).length() == 2) {
                System.out.print(encrypted.fullEncrypt.get(i) + " ");
            } else {
                System.out.print(" " + encrypted.fullEncrypt.get(i) + " ");
            }
        }
        System.out.println();
        int j = 1;
        for (int i = 0; i < encrypted.phrase.length(); i++) {
            if (!(encrypted.phrase.charAt(i) == ' ')) {
                if (j < 10) {
                    System.out.print(" " + j + " ");
                } else System.out.print(j + " ");
                j++;
            } else System.out.print("  ");
        }
        System.out.println();
        for (int i = 0; i < encrypted.guesses.size(); i++) {
            System.out.print(encrypted.guesses.get(i) + " ");
        }
        System.out.println();
        System.out.println("You have guessed " + currentPlayer.getTotalGuesses() + " times");
    }

    public String parseInput() {
        String[] temp = new String[encrypted.phrase.length()];
        for (int i = 0; i < encrypted.guesses.size(); i++) {
            if (Objects.equals(encrypted.guesses.get(i), " ")) {
                temp[i] = encrypted.guesses.get(i);
            } else {
                temp[i] = encrypted.guesses.get(i).trim();
            }
        }
        return String.join("", temp);
    }

    public void saveGame() {
        try {
            Scanner input = new Scanner(System.in);
            BufferedWriter writing = new BufferedWriter(new FileWriter(saveFile, true));
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(saveFile)), StandardCharsets.UTF_8));
            String overwrite = null;
            String gameInfo = currentPlayer.getUsername() + "~" + encrypted.isLetter + "~" + encrypted.phrase + "~" + parseInput() + "~" + encrypted.cryptogramAlphabet;
            for (int i = 0; i < fileContent.size(); i++) {
                String[] parsed = fileContent.get(i).split("~");
                if (parsed[0].equals(currentPlayer.getUsername())) {
                    System.out.println("You already have a saved game, would you like to overwrite? (yes/no)");
                    overwrite = input.next();
                    if (overwrite.equals("yes")) {       //Allows user to save their current game over the previous one
                        fileContent.remove(i);
                        fileContent.add(i, gameInfo);
                    }
                }
            }
            if (overwrite == null) {
                fileContent.add(gameInfo);       //Allows user to save game when there is not already a saved game
            }
            Files.write(Paths.get(String.valueOf(saveFile)), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loadGame() {
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(saveFile)), StandardCharsets.UTF_8));
            for (int i = 0; i < fileContent.size(); i++) {
                String[] parsed = fileContent.get(i).split("~");
                if (parsed.length != 5) { //if line does not have 5 values it is removed from file
                    fileContent.remove(i);
                    Files.write(Paths.get(String.valueOf(saveFile)), fileContent, StandardCharsets.UTF_8);
                    i--;
                } else if (parsed[0].equals(currentPlayer.getUsername())) { //converts lines from file back into cryptogram objects
                    HashMap<Character, String> alphabet = new HashMap<>();
                    parsed[4] = parsed[4].substring(1, parsed[4].length() - 1);
                    String[] map = parsed[4].split(", ");
                    for (String s : map) {
                        String[] key = s.split("=");
                        alphabet.put(key[0].charAt(0), key[1]);
                    }
                    ArrayList<String> guesses = new ArrayList<>();
                    for (int j = 0; j < parsed[3].length(); j++) {
                        if (parsed[3].charAt(j) == ' ') {
                            guesses.add(Character.toString(parsed[3].charAt(j)));
                        } else guesses.add(" " + Character.toString(parsed[3].charAt(j)));
                    }
                    if (parsed[1].equals("true")) {
                        Cryptogram c = new letterCryptogram(parsed[2], guesses, alphabet); //Allows user to load a previously saved game
                        this.encrypted = c;
                    } else {
                        Cryptogram c = new numberCryptogram(parsed[2], guesses, alphabet); //Allows user to load a previously saved game
                        this.encrypted = c;
                    }
                    return true;
                }
            }
            System.out.println("Saved game not found!");
            return false;
        } catch (Exception e) {
            try {
                if (e.getClass() == NoSuchFileException.class) { //creates new file if none are present
                    System.out.println("Error, no save file, attempting to create...");
                    saveFile.createNewFile();
                } else { //other errors will be coming from corrupt files
                    System.out.println("Error, corrupt file.");
                }
            } catch (IOException ex) {
            }
            return false;
        }
    }

    public void removeSave() {
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(String.valueOf(saveFile)), StandardCharsets.UTF_8));
            for (int i = 0; i < fileContent.size(); i++) {
                String[] parsed = fileContent.get(i).split("~");
                if (parsed[0].equals(currentPlayer.getUsername())) {
                    fileContent.remove(i);
                    i--;
                }
            }
            Files.write(Paths.get(String.valueOf(saveFile)), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solve() {
        for (int i = 0; i < getEncrypted().phrase.length(); i++) {
            if (getEncrypted().guesses.get(i) == " ") {
                continue;
            }
            getEncrypted().guesses.set(i, " " + String.valueOf(getEncrypted().phrase.charAt(i)));
        }
    }


}
