import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
     Cryptogram Encrypted = generateCryptogram();
    public static void main(String[] args) {
        Cryptogram Encrypted = generateCryptogram();
        Encrypted.printDetails();
        System.out.println(Encrypted.getFrequencies());
        enterLetter(Encrypted);

    }

    static ArrayList<String> phrases = new ArrayList<String>();



    static String callPhrase(){
        phrases.add("the quick brown fox jumped over the lazy dog");
        phrases.add("hi this is lots of is");
        phrases.add("abcdefghijklmnopqrstuvwxyz");

        Random rand = new Random();
        int random = rand.nextInt(phrases.size());


        return phrases.get(random);
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
        char[] result = new char[charArray.length];
        for(int i =0; i< charArray.length; i++){
            current = phrase.charAt(i); //sets the current ascii value
            if (phrase.charAt(i) == ' '){  //checking for a space and skips this iteration of the loop
                result[i] = (char) current;
                continue;
            } else{
            current = (current + shift);
            if (current > 122){ //checks for ascii values past 122 (z) and then wraps them round
            current = current - 26;


            }
            result[i] = (char) current;
            Character original = phrase.charAt(i);
            cryptogramAlphabet.put(original, (char) current);}}
            generated.cryptogramAlphabet = cryptogramAlphabet;
            generated.fullEncrypt = new String (result);
            System.out.println(generated.fullEncrypt);
      return generated;
    }

 static void enterLetter(Cryptogram Encrypted) {
     HashMap<Character, Character> guesses = new HashMap<Character, Character>();
     guesses = initaliseKey(guesses, Encrypted);
     Scanner object = new Scanner(System.in);
     System.out.println("What position would you like to guess at?");
     String pos = object.nextLine();
     int position = Integer.parseInt(pos);
     System.out.println("What would you like to guess that letter as?");
     String guess = object.nextLine();
     System.out.println("You guessed " + guess + " at position " + pos);
     int total =0;
     for (int i = 0; i < position-1; i++) {
         if (Encrypted.phrase.charAt(i) == ' ') {  //checking for a space and skips this iteration of the loop


         }else{
             total++;
             System.out.println("YO" + guesses.get((char) (total)));}
     }
     System.out.println(total);
     guesses.put((guess.charAt(0)),guesses.get((char) (total)));
     System.out.println(guesses);
 }

    static HashMap<Character,Character>  initaliseKey(HashMap<Character, Character> guesses, Cryptogram Encrypted) {

        for(int i =0; i< Encrypted.fullEncrypt.toCharArray().length; i++){
            if (Encrypted.fullEncrypt.charAt(i) == ' '){  //checking for a space and skips this iteration of the loop
                continue;
            } else{
            guesses.put((char)i, Encrypted.fullEncrypt.charAt(i));
            System.out.println(Encrypted.fullEncrypt.charAt(i));}}

        return guesses;
    }


}
