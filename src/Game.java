import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Game {
    Cryptogram Encrypted = generateCryptogram();
    public static void main(String[] args) {
        Cryptogram Encrypted = generateCryptogram();
        Encrypted.printDetails();
        System.out.println(Encrypted.getFrequencies());
    }

    static ArrayList<String> phrases = new ArrayList<String>();



    static String callPhrase(){
        phrases.add("the quick brown fox jumped over the lazy dog");
        phrases.add("hi this is lots of is");
        phrases.add("abcdefghijklmnopqrstuvwxyz");

        Random rand = new Random();
        int random = rand.nextInt(phrases.size());


        return phrases.get(1);
    }



    static Cryptogram generateCryptogram(){
        String phrase = callPhrase();

        char[] charArray; // used to store the phrase as a char array and used in loops
        int current; //the ascii value that is eventually shifted
        Random rand = new Random();
       int shift = rand.nextInt(1, 26); // a int that the phrase will be shifted by
        charArray = phrase.toCharArray();
        Cryptogram generated = new Cryptogram();
        HashMap<Character, Character> cryptogramAlphabet = new HashMap<Character, Character>();
        generated.phrase = phrase;

        for(int i =0; i< charArray.length; i++){
            current = phrase.charAt(i); //sets the current ascii value
            if (phrase.charAt(i) == ' '){  //checking for a space and skips this iteration of the loop
                continue;
            } else{
            current = (current + shift);
            if (current > 122){ //checks for ascii values past 122 (z) and then wraps them round
            current = current - 26;
            }
            Character original = phrase.charAt(i);
            cryptogramAlphabet.put(original, (char) current);}}
            generated.cryptogramAlphabet = cryptogramAlphabet;

      return generated;
    }

 void enterLetter(char guess){





 }



}
