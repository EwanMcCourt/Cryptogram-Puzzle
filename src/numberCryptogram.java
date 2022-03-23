import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class numberCryptogram extends Cryptogram {

    public numberCryptogram(String phrase, ArrayList guesses, HashMap<Character, String> cryptogramAlphabet){
        this.isLetter = false;
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

    public numberCryptogram(String file) {
        this.isLetter = false;
        this.phrase = callPhrase(file);
        this.posGuess = new ArrayList<>();
        this.guesses = new ArrayList<>();
        this.parsedGuesses = null;
        this.cryptogramAlphabet = new HashMap<>();

        ArrayList<String> numbers = new ArrayList<>();
        for (int i = 1; i <= 26; i++){
            numbers.add(Integer.toString(i));
        }

        Random rand = new Random();
        int num; //value in array list to be mapped to letter

        ArrayList<String> result = new ArrayList<>();

        char alphabet = (char) 97;
        for (int j = 0; j < 26; j++) {
            num = rand.nextInt(numbers.size());
            cryptogramAlphabet.put(alphabet, numbers.get(num));
            numbers.remove(num);
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
        }}

    public void printDetails() {
        System.out.println("phrase is " + phrase);
        System.out.print("[");
        for (Character i : cryptogramAlphabet.keySet()) {
            if (cryptogramAlphabet.get(i).length() == 2){
                System.out.print(" ");
            }
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println("]");
        System.out.print("[");
        for (Character i : cryptogramAlphabet.keySet()) {

            System.out.print(cryptogramAlphabet.get(i)+ ", ");
        }
        System.out.println("]");
    }
}
