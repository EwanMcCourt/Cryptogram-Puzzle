import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Game {


    public static void main(String[] args) {

    }


    static String callPhrase(String fileName) {
        try {
            long lines = Files.lines(Path.of(fileName)).count(); //gets number of lines in file
            if (lines == 0){
                System.out.print("Error, no phrase file!");
                System.exit(0);
                return null;
            }
            BufferedReader phraseReader = new BufferedReader(new FileReader(fileName));
            int random = new Random().nextInt((int) lines); //line to read from is chosen
            for (int i = 0; i < random; i++) phraseReader.readLine(); // navigates to right line in file
            String phrase = phraseReader.readLine(); //set phrase to line in file
            return phrase;
        } catch (IOException e) {
            System.out.print("Error, no phrase file!");
            System.exit(0);
            return null;
        }
    }


    static Cryptogram generateCryptogram() {
        Cryptogram generated = new Cryptogram("./src/phrases.txt");
        return generated;
    }

    static String[] enterLetter(Cryptogram encrypted, Player player) {
        Scanner object = new Scanner(System.in);
        String target;
        String guess;
        System.out.println("What letter do you want to guess?");
        target = object.next();
        if (target.length() > 1) {
            System.out.println("That's multiple letters.");
            System.out.println("What letter do you want to guess?");
            target = object.next();
        }
        char[] targetChar = target.toCharArray();
        System.out.println("What is your guess?");
        guess = object.next();
        if (guess.length() > 1) {
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
            if (targetChar[0] == encrypted.fullEncrypt.charAt(i)) {
            if (!Objects.equals(encrypted.guesses[i], " ?")) {
                toConform = true;
                break;
            }}
        }
        if(toConform){
            System.out.println("This letter is already mapped are you sure you want to overwrite? (yes or no)");
            conform = object.next();
        }
        for (int i = 0; i < encrypted.fullEncrypt.length(); i++) {
            if (targetChar[0] == encrypted.fullEncrypt.charAt(i)) {
                if (!Objects.equals(encrypted.guesses[i], " ?")) {
                    if (Objects.equals(conform, "yes")) {
                        for (int j = 0; j < encrypted.fullEncrypt.length(); j++) {
                            if (targetChar[0] == encrypted.fullEncrypt.charAt(j)) {
                                encrypted.guesses[j] = " " + guess;
                                player.updateTotalGuesses(player.getTotalGuesses() + 1);
                                char[] guessChar = guess.trim().toCharArray();
                                if (encrypted.cryptogramAlphabet.get(guessChar[0]) == targetChar[0]) {
                                    player.updatenumCorrectGuesses(player.getnumCorrectGuesses() + 1);
                                }
                            }
                        }
                    } else {
                        //break;
                    }
                } else {
                    encrypted.guesses[i] = " " + guess;
                    player.updateTotalGuesses(player.getTotalGuesses() + 1);
                    char[] guessChar = guess.trim().toCharArray();
                    if (encrypted.cryptogramAlphabet.get(guessChar[0]) == targetChar[0]) {
                        player.updatenumCorrectGuesses(player.getnumCorrectGuesses() + 1);
                    }
                }
            }
        }
        return encrypted.guesses;
    }

    static String[] undoLetter(Cryptogram encrypted, Player player) {
        Scanner reader = new Scanner(System.in);
        System.out.println("What guess are you unmapping?");
        String remove = reader.next();
        boolean exists = false;
        for (int i = 0; i < encrypted.fullEncrypt.length(); i++) {
            if ((" " + remove).equals(encrypted.guesses[i])) {
                encrypted.guesses[i] = " ?";
                exists = true;
            }
        }
        if (exists == false) {
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


    public static void currentSol(Cryptogram encrypted, Player player) {
        for (int i = 0; i < encrypted.fullEncrypt.length(); i++) {
            if (encrypted.fullEncrypt.charAt(i) == ' ') {
                System.out.print(encrypted.fullEncrypt.charAt(i) + " ");
            } else {
                System.out.print(" " + encrypted.fullEncrypt.charAt(i) + " ");
            }
        }
        System.out.println();
        for (String label : encrypted.labeledMap.values()) {
            System.out.print(label + " ");
        }
        System.out.println();
        for (int i = 0; i < encrypted.guesses.length; i++) {
            System.out.print(encrypted.guesses[i] + " ");
        }
        System.out.println();
        System.out.println("You have guessed " + player.getTotalGuesses() + " times");


    }

    public static String parseInput(Cryptogram encrypted) {
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
