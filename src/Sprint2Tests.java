import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class Sprint2Tests {
    @Test
    public void TestSaveToFile() throws IOException {
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(player, " ", "./src/phrases.txt");
        System.setIn(System.in);
        String simulatedUserInput = "yes" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        System.setIn(System.in);
        game.saveGame();
        File file = new File("SaveGames.txt");
        Assert.assertTrue(file.length() > 0);


    }

    @Test
    public void TestLoad() throws IOException {
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(player, " ", "./src/phrases.txt");
        Cryptogram encrypted = game.getEncrypted();
        String simulatedUserInput = encrypted.fullEncrypt.get(0) + System.getProperty("line.separator")
                + "e" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.enterLetter();
        System.setIn(System.in);
        String simulatedUserInput2 = "yes" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput2.getBytes(StandardCharsets.UTF_8)));
        System.setIn(System.in);
        game.saveGame();
        Game game2 = new Game(player);
        game2.loadGame();
        assertEquals(game.getEncrypted().fullEncrypt,game2.getEncrypted().fullEncrypt);
    }


    @Test
    public void TestUserName() throws IOException {
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(player, " ", "./src/phrases.txt");
        String pUserName = "player";
        player.setUsername(pUserName);
        assertEquals(pUserName,player.getUsername());
    }
    @Test
    public void TestStats() throws IOException {
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, "letter", "./src/test.txt");
        Cryptogram encrypted = game.getEncrypted();
        String done = "";
        for (int i = 0; i<(encrypted.phrase.length()); i++) {
            InputStream savedStandardInputStream = System.in;
            if (!(encrypted.phrase.charAt(i) == ' ')&&!done.contains(Character.toString(encrypted.phrase.charAt(i)))) {
                done = done+encrypted.phrase.charAt(i);
                String simulatedUserInput = encrypted.fullEncrypt.get(i) + System.getProperty("line.separator")
                        + encrypted.phrase.charAt(i) + System.getProperty("line.separator");

                System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
                encrypted.guesses = game.enterLetter();
                System.setIn(savedStandardInputStream);
                game.currentSol();
            }

            }
            assertTrue(testPlayer.getTotalGuesses() > 1);
    }
    @Test
    public void TestLoadPlayer() throws IOException {
        Player testPlayer = new Player(1, "hardCoded", 0.0, 5, 10, 1, 0);
        Game game = new Game(testPlayer, "letter", "./src/test.txt");
        Players.savePlayers();
        Player testPlayer2 = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        testPlayer2 = Players.loadPlayer("hardCoded");
        assertEquals(testPlayer.getCryptogramsCompleted(),testPlayer2.getCryptogramsCompleted());
        }
    }


