import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class letterCryptogram extends Cryptogram {

    public letterCryptogram(String phrase, ArrayList guesses, HashMap<Character, String> cryptogramAlphabet){
        this.isLetter = true;
        this.phrase = phrase;
        this.guesses = guesses;
        this.cryptogramAlphabet = cryptogramAlphabet;
        this.fullEncrypt = new ArrayList<>();
        for (int i = 0; i < phrase.length(); i++) {
            if (!(phrase.charAt(i) == ' ')) {
                this.fullEncrypt.add(cryptogramAlphabet.get(phrase.charAt(i)));
            }
            else this.fullEncrypt.add(" ");
        }
    }


    public letterCryptogram(String file)  {
        this.isLetter = true;
        this.phrase = callPhrase(file);
        this.posGuess = new ArrayList<>();
        this.guesses = new ArrayList<>();
        this.parsedGuesses = null;
        this.cryptogramAlphabet = new HashMap<>();


        ArrayList<String> letters = new ArrayList<>();
        for (int i = 97; i <= 122; i++){
            letters.add(Character.toString(i));
        }

        Random rand = new Random();
        int num; //value in array list to be mapped to letter

        ArrayList<String> result = new ArrayList<>();

        char alphabet = (char) 97;
        for (int j = 0; j < 26; j++) {
            num = rand.nextInt(0, letters.size());
            if (Character.toString(alphabet).equals(letters.get(num))){
                num = (num+1)%letters.size();
            }
            cryptogramAlphabet.put(alphabet, letters.get(num));
            letters.remove(num);
            alphabet++;
        }

        for (int i = 0; i < phrase.length(); i++) {
            if (!(phrase.charAt(i) == ' ')) {
                result.add(cryptogramAlphabet.get(phrase.charAt(i)));
            }
            else result.add(" ");
        }
        fullEncrypt = result;
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                guesses.add(" ");
            } else {
                guesses.add(" ?");
            }
        }
    }
}