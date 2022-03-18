import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Cryptogram {
    protected boolean isLetter;
    protected String phrase;
    protected HashMap<Character, String> cryptogramAlphabet;
    protected ArrayList<String> fullEncrypt;
    protected ArrayList<String> guesses;
    protected ArrayList<Integer> posGuess;
    protected String parsedGuesses;

    public Cryptogram() {}

    static Cryptogram newCryptogram(String type, String file) {
        if (type.equals("yes")){
            return new numberCryptogram(file);
        }
        else {
            return new letterCryptogram(file);
        }
    }

    public void printDetails() {
        System.out.println("phrase is " + phrase);
        System.out.print(cryptogramAlphabet.keySet());
        System.out.println();
        System.out.print("[");
        for (Character i : cryptogramAlphabet.keySet()) {
            System.out.print(cryptogramAlphabet.get(i)+ ", ");
        }
        System.out.println("]");
    }

    HashMap<String, Integer> getFrequencies() {
        HashMap<String, Integer> frequencies = new HashMap<>();
        ArrayList<String> charArray = fullEncrypt;
        for (int i = 0; i < charArray.size(); i++) {
            if (!(fullEncrypt.get(i).charAt(0) == ' ')) {
                frequencies.put(fullEncrypt.get(i), 0);
            }
        }
        for (int i = 0; i < charArray.size(); i++) {
            int total;
            if (!(fullEncrypt.get(i).charAt(0) == ' ')) {
                total = frequencies.get(fullEncrypt.get(i));
                frequencies.put(fullEncrypt.get(i), total + 1);
            }
        }
        return frequencies;
    }

    public String callPhrase(String file) {
        try {
            long lines = Files.lines(Path.of(file)).count(); //gets number of lines in file
            BufferedReader phraseReader = new BufferedReader(new FileReader(file));
            int random = new Random().nextInt((int) lines); //line to read from is chosen
            for (int i = 0; i < random; i++) phraseReader.readLine(); // navigates to right line in file
            return phraseReader.readLine();
        } catch (IOException | IllegalArgumentException e) {
            System.out.print("Error, no phrase file!");            //Displays error message
            return null;
        }
    }
}