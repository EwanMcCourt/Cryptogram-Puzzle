import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Cryptogram {
    String phrase;
    HashMap<Character, Character> cryptogramAlphabet = new HashMap<Character, Character>();
    String fullEncrypt;
    String[] guesses;
    HashMap<Integer, String> labeledMap = new HashMap<Integer, String>();
    ArrayList<Integer> posGuess;
    String parsedGuesses;

    public Cryptogram() {
        phrase = Game.callPhrase();
        posGuess = new ArrayList<Integer>();
        guesses = new String[phrase.length()];
        parsedGuesses = null;
        int current; //the ascii value that is eventually shifted
        Random rand = new Random();
        int shift = rand.nextInt(1, 26); // a int that the phrase will be shifted by
        HashMap<Character, Character> cryptogramAlphabet = new HashMap<Character, Character>();
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
                char alphabet = (char) 97;
                int changed;
                for (int j = 0; j < 26; j++) {
                    // System.out.println(j+ " : " +alphabet);
                    changed = alphabet + shift;
                    if (changed > 122) { //checks for ascii values past 122 (z) and then wraps them round
                        changed = changed - 26;
                    }
                    cryptogramAlphabet.put(alphabet, (char) (changed));
                    alphabet++;
                }
                result[i] = (char) current;
                Character original = phrase.charAt(i);
            }
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

    void printDetails() {
        System.out.println("phrase is " + phrase);
        for (Character i : cryptogramAlphabet.keySet()) {
            System.out.println(i + " = " + cryptogramAlphabet.get(i) + " ");
        }
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
}