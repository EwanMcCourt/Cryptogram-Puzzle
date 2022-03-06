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

    public Game(Player player, String type) {
        this.encrypted = Cryptogram.newCryptogram(type);
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

    public String[] enterLetter() {
        Scanner object = new Scanner(System.in);
        String target = " ";
        String guess;
        int contains = 0;
        System.out.println("What letter do you want to guess?");
        target = object.next();
        while (contains == 0) {
            for (int i = 0; i < encrypted.phrase.length(); i++) {
                if (Objects.equals(encrypted.fullEncrypt[i], target)) {
                    contains = contains + 1;
                }
            }
            if (contains == 0) {
                System.out.println("That letter isn't in this cryptogram try again!");
                System.out.println("What letter do you want to guess?");
                target = object.next();
            }
        }
        if (target.length() > 1) {
            System.out.println("That's multiple letters.");
            System.out.println("What letter do you want to guess?");
            target = object.next();
        }
        System.out.println("What is your guess?");
        guess = object.next();
        while (guess.length() > 1) {
            System.out.println("Your guess is too long!!");
            System.out.println("What is your guess?");
            guess = object.next();
        }
        for (int i = 0; i < encrypted.phrase.length(); i++) {
            if ((" " + guess).equals(encrypted.guesses[i])) {
                System.out.println("Error this letter has already been mapped");
                return encrypted.guesses;
            }
        }
        String conform = null;
        boolean toConform = false;
        for (int i = 0; i < encrypted.phrase.length(); i++) {
            if (target.equals(encrypted.fullEncrypt[i])) {
                if (!Objects.equals(encrypted.guesses[i], " ?")) {
                    toConform = true;
                    break;
                }
            }
        }
        if (toConform) {
            System.out.println("This letter is already mapped are you sure you want to overwrite? (yes or no)");
            conform = object.next();
        }
        for (int i = 0; i < encrypted.fullEncrypt.length; i++) {
            if (target.equals(encrypted.fullEncrypt[i])) {

                if (!Objects.equals(encrypted.guesses[i], " ?")) {
                    if (Objects.equals(conform, "yes")) {
                        for (int j = 0; j < encrypted.fullEncrypt.length; j++) {
                            if (target.equals(encrypted.fullEncrypt[j])) {
                                encrypted.guesses[j] = " " + guess;
                                currentPlayer.updateTotalGuesses(currentPlayer.getTotalGuesses() + 1);
                                if (Objects.equals(encrypted.cryptogramAlphabet.get(guess.charAt(0)), target)) {
                                    currentPlayer.updatenumCorrectGuesses(currentPlayer.getnumCorrectGuesses() + 1);
                                }
                            }
                        }
                    }
                } else {
                    encrypted.guesses[i] = " " + guess;
                    currentPlayer.updateTotalGuesses(currentPlayer.getTotalGuesses() + 1);
                    if (Objects.equals(encrypted.cryptogramAlphabet.get(guess.charAt(0)), target)) {
                        currentPlayer.updatenumCorrectGuesses(currentPlayer.getnumCorrectGuesses() + 1);
                    }
                }
            }
        }
        return encrypted.guesses;
    }

    public String[] undoLetter() {
        Scanner reader = new Scanner(System.in);
        System.out.println("What guess are you unmapping?");
        String remove = reader.next();
        boolean exists = false;
        if (encrypted.isLetter) {
            for (int i = 0; i < encrypted.fullEncrypt.length; i++) {
                if ((" " + remove).equals(encrypted.guesses[i])) {
                    encrypted.guesses[i] = " ?";
                    exists = true;
                }
            }
        } else {
            for (int i = 0; i < encrypted.fullEncrypt.length; i++) {
                if ((" " + remove).equals(encrypted.guesses[i])) {
                    encrypted.guesses[i] = " ?";
                    exists = true;
                }
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

        for (int i = 0; i < encrypted.fullEncrypt.length; i++) {
            if (encrypted.fullEncrypt[i].charAt(0) == ' ') {
                System.out.print(encrypted.fullEncrypt[i] + " ");
            } else if (encrypted.fullEncrypt[i].length()==2){
                System.out.print(encrypted.fullEncrypt[i] + " ");
            }
            else {
                System.out.print(" " + encrypted.fullEncrypt[i] + " ");
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
        for (int i = 0; i < encrypted.guesses.length; i++) {
            System.out.print(encrypted.guesses[i] + " ");
        }
        System.out.println();
        System.out.println("You have guessed " + currentPlayer.getTotalGuesses() + " times");
    }

    public String parseInput() {
        String[] temp = new String[encrypted.phrase.length()];
        for (int i = 0; i < encrypted.guesses.length; i++) {
            if (encrypted.guesses[i] == " ") {
                temp[i] = encrypted.guesses[i];
            } else {
                temp[i] = encrypted.guesses[i].trim();
            }
        }
        String returned = String.join("", temp);
        return returned;
    }
}
