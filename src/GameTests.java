import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class GameTests {

    @Test
  public void testNotNull(){
   Cryptogram encrypted = Game.generateCryptogram();
    assertTrue(encrypted != null);
    }
    @Test
    public void checkFirstChar(){
        Cryptogram encrypted = Game.generateCryptogram();
        char[] charArray;
        charArray = encrypted.phrase.toCharArray();


        for (int i = 0; i<charArray.length; i++) {

            char current = encrypted.phrase.charAt(i);
            char current2 = encrypted.fullEncrypt.charAt(i);
            if (encrypted.phrase.charAt(i) == ' '){  //checking for a space and skips this iteration of the loop

                continue;
            } else {
                System.out.println(current + " == " + current2);
                assertFalse(current == current2);
            }
        }
    }

}
