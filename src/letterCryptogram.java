import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class letterCryptogram extends Cryptogram {

    public letterCryptogram() {
        this.isLetter = true;
        phrase = callPhrase();
        posGuess = new ArrayList<Integer>();
        guesses = new String[phrase.length()];
        parsedGuesses = null;
        int current; //the ascii value that is eventually shifted
        Random rand = new Random();
        int shift = rand.nextInt(1, 26); // a int that the phrase will be shifted by
        String[] result = new String[phrase.length()];
        for (int i = 0; i < phrase.length(); i++) {
            current = phrase.charAt(i); //sets the current ascii value
            if (phrase.charAt(i) == ' ') {  //checking for a space and skips this iteration of the loop
                result[i] = Character.toString((char) current);
                continue;
            } else {
                current = (current + shift);
                if (current > 122) { //checks for ascii values past 122 (z) and then wraps them round
                    current = current - 26;
                }
            }

            result[i] = Character.toString((char) current);
        }
        char alphabet = (char) 97;
        int changed;
        for (int j = 0; j < 26; j++) {
            changed = alphabet + shift;
            if (changed > 122) { //checks for ascii values past 122 (z) and then wraps them round
                changed = changed - 26;
            }
            cryptogramAlphabet.put(alphabet, Character.toString((char) changed));
            alphabet++;
        }


        fullEncrypt = result;
        for(int i = 0; i < phrase.length(); i++){
        System.out.print(fullEncrypt[i]);}
        System.out.println();
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                guesses[i] = " ";
            } else {
                guesses[i] = " ?";
            }
        }
    }



}
