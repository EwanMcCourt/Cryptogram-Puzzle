import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Cryptogram {
    String phrase;
    HashMap<Character, Character> cryptogramAlphabet = new HashMap<>();
    String fullEncrypt;
    String[] guesses;
    HashMap<Integer, String> labeledMap = new HashMap<>();
    ArrayList<Integer> posGuess;
    String parsedGuesses;

    public Cryptogram() {
        phrase = callPhrase();
        posGuess = new ArrayList<>();
        guesses = new String[phrase.length()];
        parsedGuesses = null;
        int current; //the ascii value that is eventually shifted
        Random rand = new Random();
        int shift = rand.nextInt(1, 26); // a int that the phrase will be shifted by
        int label = 1;
        char[] result = new char[phrase.length()];
        for (int i = 0; i < phrase.length(); i++) {
            current = phrase.charAt(i); //sets the current ascii value
            if (phrase.charAt(i) == ' ') {
                labeledMap.put(i, " ");
            } else {
                String lable2 = String.valueOf(label);
                if (lable2.length() == 1) {
                    lable2 = " " + lable2;
                }
                labeledMap.put(i, lable2);
                label++;
            }
            if (phrase.charAt(i) == ' ') {  //checking for a space and skips this iteration of the loop
                result[i] = (char) current;
                continue;
            } else {
                current = (current + shift);
                if (current > 122) { //checks for ascii values past 122 (z) and then wraps them round
                    current = current - 26;
                }
            }

            result[i] = (char) current;
            Character original = phrase.charAt(i);
        }
        char alphabet = (char) 97;
        int changed;
        for (int j = 0; j < 26; j++) {
            changed = alphabet + shift;
            if (changed > 122) { //checks for ascii values past 122 (z) and then wraps them round
                changed = changed - 26;
            }
            cryptogramAlphabet.put(alphabet, (char) changed);
            alphabet++;
        }


        fullEncrypt = new String(result);
        System.out.println(fullEncrypt);
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                guesses[i] = " ";
            } else {
                guesses[i] = " ?";
            }
        }
    }

    static Cryptogram newCryptogram(String type){
        if (type.equals("yes")){
            return new numberCryptogram();
        }
        else {
            return new letterCryptogram();
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

    HashMap<Character, Integer> getFrequencies() {
        HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>();
        char[] charArray = fullEncrypt.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (fullEncrypt.charAt(i) == ' ') {
                continue;
            } else {
                frequencies.put(fullEncrypt.charAt(i), 0);
            }
        }
        for (int i = 0; i < charArray.length; i++) {
            int total;
            if (fullEncrypt.charAt(i) == ' ') {
                continue;
            } else {
                total = frequencies.get(fullEncrypt.charAt(i));
                frequencies.put(fullEncrypt.charAt(i), total + 1);
            }
        }
        return frequencies;
    }

    public String callPhrase() {
        try {
            long lines = Files.lines(Path.of("./src/phrases.txt")).count(); //gets number of lines in file
            BufferedReader phraseReader = new BufferedReader(new FileReader("./src/phrases.txt"));
            int random = new Random().nextInt((int) lines); //line to read from is chosen
            for (int i = 0; i < random; i++) phraseReader.readLine(); // navigates to right line in file
            return phraseReader.readLine();
        } catch (IOException e) {
            System.out.print("Error, no phrase file!");
            return null;
        }
    }
}