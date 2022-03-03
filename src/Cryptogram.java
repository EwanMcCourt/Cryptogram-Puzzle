import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Cryptogram {
String phrase;
HashMap<Character, Character> cryptogramAlphabet = new HashMap<Character, Character>();
String fullEncrypt;
String [] guesses;
HashMap<Integer, String> labeledMap = new HashMap<Integer, String>();
void printDetails(){
    System.out.println("phrase is " + phrase);
    for (Character i : cryptogramAlphabet.keySet()) {
        System.out.println("key: " + i + " value: " + cryptogramAlphabet.get(i));
    }}
HashMap<Character, Integer> getFrequencies(){
    HashMap<Character, Integer>  frequencies = new HashMap<Character, Integer>();
    char[] charArray = fullEncrypt.toCharArray();
    for (int i=0; i < charArray.length; i++){
        if (fullEncrypt.charAt(i) == ' '){
            continue;}else{
        frequencies.put(fullEncrypt.charAt(i), 0);}}
    for (int i=0; i < charArray.length; i++) {
        int total;
        if (fullEncrypt.charAt(i) == ' ') {
            continue;
        } else {
            total = frequencies.get(fullEncrypt.charAt(i));
            frequencies.put( fullEncrypt.charAt(i), total + 1);
        }}





    return frequencies;
    }


}