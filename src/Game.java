import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    Cryptogram Encrypted = generateCryptogram();

    public static void main(String[] args) {
        Cryptogram Encrypted = generateCryptogram();
        //Encrypted.printDetails();
        //System.out.println(Encrypted.getFrequencies());
        boolean full = false;
        int filledIn = 0;
        Encrypted.guesses = new String[Encrypted.phrase.length()];
        Encrypted.guesses = initaliseArray(Encrypted);
        for (int i = 0; i < Encrypted.guesses.length; i++) {
            if (Encrypted.phrase.charAt(i) == ' ') {
                filledIn = filledIn + 1;
            }
        }

        Encrypted.guesses = enterLetter(Encrypted);

    }



    static String callPhrase(){
        try {
            long lines = Files.lines(Path.of("./src/phrases.txt")).count(); //gets number of lines in file
            BufferedReader phraseReader = new BufferedReader(new FileReader("./src/phrases.txt"));
            int random = new Random().nextInt((int) lines); //line to read from is chosen
            for (int i = 0; i < random; i++) phraseReader.readLine(); // navigates to right line in file
            String phrase = phraseReader.readLine(); //set phrase to line in file
            return phrase;
        }
        catch (IOException e){
            System.out.print("Error, no phrase file!");
            return null;
        }
    }


    static Cryptogram generateCryptogram() {
        String phrase = callPhrase();

        char[] charArray; // used to store the phrase as a char array and used in loops
        int current; //the ascii value that is eventually shifted
        Random rand = new Random();
        int shift = rand.nextInt(1, 26); // a int that the phrase will be shifted by
        charArray = phrase.toCharArray();
        Cryptogram generated = new Cryptogram();
        HashMap<Character, Character> cryptogramAlphabet = new HashMap<Character, Character>();
        generated.phrase = phrase;
        int label = 1;
        char[] result = new char[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            current = phrase.charAt(i); //sets the current ascii value
            if (phrase.charAt(i) == ' ') {
                generated.labeledMap.put(i, " ");

            } else {
                String lable2 = String.valueOf(label);
                if (lable2.length() == 1) {
                    lable2 = " " + lable2;

                }
                generated.labeledMap.put(i, lable2);
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
        generated.cryptogramAlphabet = cryptogramAlphabet;
        generated.fullEncrypt = new String(result);
        System.out.println(generated.fullEncrypt);
        return generated;
    }

    static String[] enterLetter(Cryptogram Encrypted) {
        char[] charArray = Encrypted.phrase.toCharArray();
        //String [] guesses = new String[charArray.length];

        Scanner object = new Scanner(System.in);
        System.out.println("What position would you like to guess at?");
        String pos = object.nextLine();

        int position = Integer.parseInt(pos);
        System.out.println("What would you like to guess that letter as?");
        String guess = object.next();

        System.out.println("You guessed " + guess + " at position " + pos);
        int total = getKeyByValue(Encrypted.labeledMap, pos) + 1;
        Encrypted.guesses[total - 1] = " " + String.valueOf(guess);
        for (String label : Encrypted.labeledMap.values()) {

            System.out.print(label + " ");
        }


        System.out.println();
        for (int i = 0; i < Encrypted.guesses.length; i++) {
            System.out.print(Encrypted.guesses[i] + " ");
        }
        System.out.println();
        return Encrypted.guesses;
    }


    static String[] initaliseArray(Cryptogram Encrypted) {
        for (int i = 0; i < Encrypted.phrase.length(); i++) {
            if (Encrypted.phrase.charAt(i) == ' ') {

                Encrypted.guesses[i] = " ";

            } else {
                Encrypted.guesses[i] = " ?";
            }

        }


        return Encrypted.guesses;
    }


    public static Integer getKeyByValue(HashMap<Integer, String> labeledMap, String value) {
        for (Map.Entry<Integer, String> entry : labeledMap.entrySet()) {
            if (Objects.equals(value, entry.getValue().trim())) {
                return entry.getKey();
            }
        }
        return null;
    }

}
