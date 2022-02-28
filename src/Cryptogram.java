import java.util.ArrayList;
import java.util.Random;

public class Cryptogram {

    static ArrayList<String> phrases = new ArrayList<String>();



    static String callPhrase(){
        phrases.add("the quick brown fox jumped over the lazy dog");
        phrases.add("hi this is lots of is");
        phrases.add("abcdefghijklmnopqrstuvwxyz");

        Random rand = new Random();
        int random = rand.nextInt(phrases.size());


        return phrases.get(random);
    }
}
