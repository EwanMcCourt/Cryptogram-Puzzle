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


    static String callPhrase() {
        try {
            long lines = Files.lines(Path.of("./src/phrases.txt")).count(); //gets number of lines in file
            BufferedReader phraseReader = new BufferedReader(new FileReader("./src/phrases.txt"));
            int random = new Random().nextInt((int) lines); //line to read from is chosen
            for (int i = 0; i < random; i++) phraseReader.readLine(); // navigates to right line in file
            String phrase = phraseReader.readLine(); //set phrase to line in file
            return phrase;
        } catch (IOException e) {
            System.out.print("Error, no phrase file!");
            return null;
        }
    }


    static Cryptogram generateCryptogram() {
        Cryptogram generated = new Cryptogram();
        return generated;
    }

    static String[] enterLetter(Cryptogram encrypted, Player player) {
        char[] charArray = encrypted.phrase.toCharArray();
        Scanner object = new Scanner(System.in);
        System.out.println("What position would you like to guess at?");
        String pos = object.nextLine();
        int position = Integer.parseInt(pos);
        encrypted.posGuess.add(position);
        System.out.println("What would you like to guess that letter as?");
        String guess = object.next();
        System.out.println("You guessed " + guess + " at position " + pos);
        int total = getKeyByValue(encrypted.labeledMap, pos) + 1;
        encrypted.guesses[total - 1] = " " + String.valueOf(guess);
        player.updateTotalGuesses(player.getTotalGuesses() + 1);
        return encrypted.guesses;
    }


    static String[] undoLetter(Cryptogram encrypted, Player player) {

        int removing = encrypted.posGuess.get(encrypted.posGuess.size() - 1);
        System.out.println("You removed" + encrypted.guesses[getKeyByValue(encrypted.labeledMap, String.valueOf(removing))] + " at " + removing);
        encrypted.guesses[getKeyByValue(encrypted.labeledMap, String.valueOf(removing))] = " ?";
        //encrypted.posGuess.remove(encrypted.posGuess.size());
        if (player.getTotalGuesses() < 0) {
            player.updateTotalGuesses(0);
        } else {
            player.updateTotalGuesses(player.getTotalGuesses() - 1);
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
        for (String label : encrypted.labeledMap.values()) {
            System.out.print(label + " ");
        }
        System.out.println();
        for (int i = 0; i < encrypted.guesses.length; i++) {
            System.out.print(encrypted.guesses[i] + " ");
        }
        System.out.println();
        System.out.println("You have guessed " + player.getTotalGuesses() + " amount of times");


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
