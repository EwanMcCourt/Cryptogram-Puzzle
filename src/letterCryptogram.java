import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class letterCryptogram extends Cryptogram {

    public letterCryptogram(String file)  {
        this.isLetter = true;
        this.phrase = callPhrase(file);
        this.posGuess = new ArrayList<>();
        this.guesses = new ArrayList<>();
        this.parsedGuesses = null;
        int current;                        //the ascii value that is eventually shifted
        Random rand = new Random();
        int shift = rand.nextInt(1, 26);           //a int that the phrase will be shifted by
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < phrase.length(); i++) {
            current = phrase.charAt(i);            //sets the current ascii value
            if (phrase.charAt(i) == ' ') {           //checking for a space and skips this iteration of the loop
                result.add(Character.toString((char) current));
                continue;
            } else {
                current = (current + shift);
                if (current > 122) {              //checks for ascii values past 122 (z) and then wraps them round
                    current = current - 26;
                }
            }
            result.add(Character.toString((char) current));
        }
        char alphabet = (char) 97;
        int changed;
        for (int j = 0; j < 26; j++) {
            changed = alphabet + shift;
            if (changed > 122) {                    //checks for ascii values past 122 (z) and then wraps them round
                changed = changed - 26;
            }
            cryptogramAlphabet.put(alphabet, Character.toString((char) changed));
            alphabet++;
        }
        fullEncrypt = result;
        for(int i = 0; i < phrase.length(); i++){
        System.out.print(fullEncrypt.get(i));}
        System.out.println();
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                guesses.add(" ");
            } else {
                guesses.add(" ?");
            }
        }
    }
}