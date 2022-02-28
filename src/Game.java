import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
public class Game {
    public static void main(String[] args) {
    String Encrypted = generateCryptogram(Cryptogram.callPhrase());

        //System.out.println("Encrypted = " +Encrypted); //Uncomment to see encrypted phrase

    }


    static String generateCryptogram(String phrase){
        char[] charArray; // used to store the phrase as a char array and used in loops
        int current; //the ascii value that is eventually shifted
        Random rand = new Random();
       int shift = rand.nextInt(1, 26); // a int that the phrase will be shifted by
        charArray = phrase.toCharArray();
        char[] result = new char[charArray.length]; // a char array of the encrypted string

        for(int i =0; i< charArray.length; i++){
            current = phrase.charAt(i); //sets the current ascii value
            if (phrase.charAt(i) == ' '){  //checking for a space so it can just return a space
                result[i] = (char) current;
            } else{
            current = (current + shift);
            if (current > 122){ //checks for ascii values past 122 (z) and then wraps them round
            current = current - 26;
            }
            result[i] = (char) current;}}

        //System.out.println("phrase going in = " +phrase); //Uncomment to see unencrypted phrase
      return new String(result);
    }




}
