import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;

import static org.junit.Assert.*;


public class GameTests {

    @Test
    public void testNotNull() {
        Cryptogram encrypted = Game.generateCryptogram();
        assertNotNull(encrypted);
    }

    @Test
    public void checkIfRandom() {
        Cryptogram encrypted = new Cryptogram();


        for (int i = 0; i < encrypted.phrase.length(); i++) {

            char current = encrypted.phrase.charAt(i);
            char current2 = encrypted.fullEncrypt.charAt(i);
            if (encrypted.phrase.charAt(i) == ' ') {  //checking for a space and skips this iteration of the loop

            } else {
                System.out.println(current + " == " + current2);
                assertNotEquals(current, current2);
            }
        }
    }


    @Test
    public void undoCheck() {
        InputStream sysInBackup = System.in;
        Cryptogram test = new Cryptogram();
        Random rand = new Random();
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0);

        int total = 0;
        for (int i = 0; i < test.guesses.length; i++) {
            if (Objects.equals(test.guesses[i], " ?")) {
                total = total + 1;

            }

        }


        for (int i = 0; i < total; i++) {
            int ran = rand.nextInt(1, total);
            String simulatedUserInput = String.valueOf(ran) + System.getProperty("line.separator") + "a" + System.getProperty("line.separator");
            System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
            Game.enterLetter(test, player);
            Game.undoLetter(test, player);
            assertTrue(test.guesses[i] == " ?" || test.guesses[i] == " ");

        }
        System.setIn(sysInBackup);

    }

}
