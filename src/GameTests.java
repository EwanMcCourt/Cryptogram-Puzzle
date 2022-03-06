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
        Game game = new Game(new Player("test"), "letter");
        assertNotNull(game);
        assertNotNull(game.getEncrypted());
        assertNotNull(Game.playerGameMapping);
    }

    @Test
    public void checkIfRandom() {
        Cryptogram encrypted = Cryptogram.newCryptogram("");


        for (int i = 0; i < encrypted.phrase.length(); i++) {

            String current = Character.toString(encrypted.phrase.charAt(i));
            String current2 = encrypted.fullEncrypt[i];
            if (encrypted.phrase.charAt(i) == ' ') {  //checking for a space and skips this iteration of the loop
            } else {
                System.out.println(current + " == " + current2);
                assertNotEquals(current, current2);
            }
        }
    }

    @Test
    public void testLetterCryptogram() {
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        String input = " ";
        Game game = new Game(player, input);
        for (int i = 0; i < game.getEncrypted().phrase.length(); i++) {
            String currentPhaseLetter = String.valueOf(game.getEncrypted().phrase.charAt(i));
            String currentEncryptedLetter = game.getEncrypted().fullEncrypt[i];
            if (currentPhaseLetter.equals(" ")) {
                continue;
            }
            assertFalse(currentPhaseLetter.equals(currentEncryptedLetter));
        }
    }
}
