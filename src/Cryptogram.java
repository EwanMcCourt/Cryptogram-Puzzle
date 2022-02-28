import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Cryptogram {
String phrase;
HashMap<Character, Character> cryptogramAlphabet = new HashMap<Character, Character>();
void printDetails(){
    System.out.println("phrase is " + phrase);
    for (Character i : cryptogramAlphabet.keySet()) {
        System.out.println("key: " + i + " value: " + cryptogramAlphabet.get(i));
    }}
HashMap<Character, Integer> getFrequencies(){
    HashMap<Character, Integer>  frequencies = new HashMap<Character, Integer>();
    char[] charArray = phrase.toCharArray();
    for (int i=0; i < charArray.length; i++){
        if (phrase.charAt(i) == ' '){
            continue;}else{
        frequencies.put(phrase.charAt(i), 0);}}
    for (int i=0; i < charArray.length; i++){
        if (phrase.charAt(i) == ' '){
            continue;
        }else{
            int total = frequencies.get(phrase.charAt(i));
            frequencies.put(phrase.charAt(i), total +1);}



    }
    return frequencies;
    }


}