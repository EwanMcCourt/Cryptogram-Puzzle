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
            cryptogramAlphabet.put(alphabet, Character.toString((char) changed));
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



}
