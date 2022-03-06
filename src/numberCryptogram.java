import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class numberCryptogram extends Cryptogram {
    private ArrayList<String> numbers;
    private HashMap<Character, String> cryptogramAlphabet;


    public numberCryptogram(){
        this.isLetter = false;
        this.phrase = callPhrase();
        this.numbers = new ArrayList<>();
        for (Integer i = 1; i <= 26; i++){
            this.numbers.add(i.toString());
        }
        this.posGuess = new ArrayList<>();
        this.guesses = new String[phrase.length()];
        this.parsedGuesses = null;
        this.cryptogramAlphabet = new HashMap<>();

        Random rand = new Random();
        int num; //value in array list to be mapped to letter
        ArrayList<String> numbersCopy = numbers;

        String[] result = new String[phrase.length()];

        char alphabet = (char) 97;
        for (int j = 0; j < 26; j++) {
            num = rand.nextInt(0, numbersCopy.size());
            cryptogramAlphabet.put(alphabet, numbersCopy.get(num));
            numbersCopy.remove(num);
            alphabet++;
        }
        for (int i = 0; i < phrase.length(); i++) {
            if (!(phrase.charAt(i) == ' ')) {
                result[i] = cryptogramAlphabet.get(phrase.charAt(i));
            }
            else result[i] = " ";
        }
        int label = 1;
        for (int i = 0; i < phrase.length(); i++) {
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
        }

        fullEncryptNum = result;
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                guesses[i] = " ";
            } else {
                guesses[i] = " ?";
            }
        }

        }


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
