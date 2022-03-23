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

    public Cryptogram() {
    }

    static Cryptogram newCryptogram(String type, String file) {
        if (type.equals("yes")) {
            return new numberCryptogram(file);                         //Creates a new cryptogram
        } else {
            return new letterCryptogram(file);
        }
    }

    public void printDetails() {
        System.out.println("phrase is " + phrase);
        System.out.print(cryptogramAlphabet.keySet());
        System.out.println();                                        //Displays cryptogram details
        System.out.print("[");
        for (Character i : cryptogramAlphabet.keySet()) {
            System.out.print(cryptogramAlphabet.get(i) + ", ");
        }
        System.out.println("]");
    }

    public void getFrequencies() {
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
        System.out.println("Letter:");
        for (String i : frequencies.keySet()) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Appearences:");
        for (String i : frequencies.keySet()) {
            System.out.print(frequencies.get(i) + " ");
        }
        System.out.println();
    }

    public void calcFrequencies() {
        System.out.println("The common proportion of letter frequencies in the English Language");
        HashMap<String, Double> frequencies = new HashMap<>();
        ArrayList<String> charArray = fullEncrypt;
        frequencies.put("a", 8.5);
        frequencies.put("b", 2.0);
        frequencies.put("c", 4.5);
        frequencies.put("d", 3.4);
        frequencies.put("e", 11.2);
        frequencies.put("f", 1.8);
        frequencies.put("g", 2.5);
        frequencies.put("h", 3.0);
        frequencies.put("i", 7.5);
        frequencies.put("j", 0.2);
        frequencies.put("k", 1.1);
        frequencies.put("l", 5.5);
        frequencies.put("m", 3.0);
        frequencies.put("n", 6.6);
        frequencies.put("o", 7.1);
        frequencies.put("p", 3.2);
        frequencies.put("q", 0.2);
        frequencies.put("r", 7.6);
        frequencies.put("s", 5.7);
        frequencies.put("t", 7.0);
        frequencies.put("u", 3.6);
        frequencies.put("v", 1.0);
        frequencies.put("w", 1.3);
        frequencies.put("x", 0.3);
        frequencies.put("y", 1.8);
        frequencies.put("z", 0.3);
        System.out.println("Letter:");
        for (String i : frequencies.keySet()) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Percentage:");
        for (String i : frequencies.keySet()) {
            System.out.print(frequencies.get(i) + "% ");
        }
        System.out.println();
    }

    public String callPhrase(String file) {
        try {
            long lines = Files.lines(Path.of(file)).count(); //gets number of lines in file
            BufferedReader phraseReader = new BufferedReader(new FileReader(file));
            int random = new Random().nextInt((int) lines); //line to read from is chosen
            for (int i = 0; i < random; i++) phraseReader.readLine(); // navigates to right line in file
            return phraseReader.readLine();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error, no phrase file!");            //Displays error message
            return null;
        }
    }

}
