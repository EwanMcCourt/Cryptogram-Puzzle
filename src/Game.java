import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Game {
    static HashMap<Game, Player> playerGameMapping;
    private Cryptogram encrypted;
    private Player currentPlayer;

    public Game(Player player, String type, String file) throws NullPointerException{
        this.encrypted = Cryptogram.newCryptogram(type, file);
        this.currentPlayer = player;
        try {
            playerGameMapping.put(this, player);
        } catch (NullPointerException e) {
            playerGameMapping = new HashMap<>();
            playerGameMapping.put(this, player);
        }
    }

    public Cryptogram getEncrypted() {
        return encrypted;
    }

    public ArrayList<String> enterLetter() {
        Scanner object = new Scanner(System.in);
        String target;
        String guess;
        String type;
        if (encrypted.isLetter){
            type = "letter";
        }
        else type = "number";
        System.out.println("What letter do you want to guess?");
        target = object.next();
        while ((type.equals("letter") && target.length() > 1) || (type.equals("number") && target.length() > 2)){
            System.out.println("Input too long.");
            System.out.println("What "+type+" do you want to guess?");
            target = object.next();
        }
        while (!encrypted.fullEncrypt.contains(target)) {
            System.out.println("That "+type+" isn't in this cryptogram try again!");
            System.out.println("What "+type+" do you want to guess?");
            target = object.next();
        }
        System.out.println("What is your guess?");
        guess = object.next();
        while (guess.length() > 1) {
            System.out.println("Your guess is too long!!");
            System.out.println("What is your guess?");
            guess = object.next();
        }
        while (encrypted.guesses.contains(" " + guess)) {
            System.out.println("This letter has already been guessed");
            System.out.println("What is your guess?");
            guess = object.next();
        }
        String conform = null;
        if (encrypted.fullEncrypt.contains(target)) {
            if (!encrypted.guesses.get(encrypted.fullEncrypt.indexOf(target)).equals(" ?")) {
                System.out.println("This letter is already mapped are you sure you want to overwrite? (yes or no)");
                conform = object.next();
            }
        }
        boolean accuracyUpdated = false;
        for (int i = 0; i < encrypted.fullEncrypt.size(); i++) {
            if (target.equals(encrypted.fullEncrypt.get(i))) {

                if (!Objects.equals(encrypted.guesses.get(i), " ?")) {
                    if (Objects.equals(conform, "yes")) {
                        for (int j = 0; j < encrypted.fullEncrypt.size(); j++) {
                            if (target.equals(encrypted.fullEncrypt.get(j))) {
                                encrypted.guesses.set(j, " " + guess);
                                if(!accuracyUpdated) {
                                    currentPlayer.updateTotalGuesses(currentPlayer.getTotalGuesses() + 1);
                                    if (Objects.equals(encrypted.cryptogramAlphabet.get(guess.charAt(0)), target)) {
                                        currentPlayer.updatenumCorrectGuesses(currentPlayer.getnumCorrectGuesses() + 1);
                                    }
                                    accuracyUpdated = true;
                                }
                            }
                        }
                    }
                } else {
                    encrypted.guesses.set(i, " " + guess);
                    if(!accuracyUpdated) {
                        currentPlayer.updateTotalGuesses(currentPlayer.getTotalGuesses() + 1);
                        if (Objects.equals(encrypted.cryptogramAlphabet.get(guess.charAt(0)), target)) {
                            currentPlayer.updatenumCorrectGuesses(currentPlayer.getnumCorrectGuesses() + 1);
                        }
                        accuracyUpdated = true;
                    }
                }
            }
        }
        return encrypted.guesses;
    }

    public ArrayList<String> undoLetter() {
        Scanner reader = new Scanner(System.in);
        System.out.println("What guess are you unmapping?");
        String remove = reader.next();
        boolean exists = false;
        for (int i = 0; i < encrypted.fullEncrypt.size(); i++) {
            if ((" " + remove).equals(encrypted.guesses.get(i))) {
                encrypted.guesses.set(i," ?");
                exists = true;
            }
        }

        if (!exists) {
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
            } else if (encrypted.fullEncrypt.get(i).length()==2){
                System.out.print(encrypted.fullEncrypt.get(i) + " ");
            }
            else {
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
        String returned = String.join("", temp);
        return returned;
    }
}
