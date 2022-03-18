import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;



import static org.junit.Assert.*;

public class GameTests {

    @Test
    public void testNotNull() {
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(player, " ", "./src/phrases.txt");
        assertNotNull(game.getEncrypted());
    }

    @Test
    public void checkCryptogramGenerationLetters() {
        Cryptogram encrypted = Cryptogram.newCryptogram("", "./src/phrases.txt");
        for (int i = 0; i < encrypted.phrase.length(); i++) {
            String current = Character.toString(encrypted.phrase.charAt(i));
            String current2 = encrypted.fullEncrypt[i];
            if (!(encrypted.phrase.charAt(i) == ' ')) {  //checking for a space and skips this iteration of the loop
                System.out.println(current + " == " + current2);
                assertNotEquals(current, current2);
            }
        }
    }
    @Test
    public void checkCryptogramGenerationNumbers() {
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(player, "yes", "./src/phrases.txt");
        for (int i = 0; i < game.getEncrypted().fullEncrypt.length; i++) {
            if (!(game.getEncrypted().phrase.charAt(i) == ' ')) {
                int currentNum = Integer.parseInt(game.getEncrypted().fullEncrypt[i]);
                assertTrue(currentNum <= 26 && currentNum >= 1);
            }
        }
    }

    @Test //the code seems to work but test fails as program ends
    public void checkEmptyPhrases(){
        assertThrows(NullPointerException.class, () -> {
            new Game(new Player("test"), "letter", "./src/empty.txt");
        });
    }

    @Test //the code seems to work but test fails as program ends
    public void checkNoFile() {
        assertThrows(NullPointerException.class, () -> {
            new Game(new Player("test"), "letter", "./src/fileThatDoesn'tExist.txt");
        });
    }

    @Test
    public void checkEnterLetter(){

        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, "letter", "./src/phrases.txt");
        Cryptogram encrypted = game.getEncrypted();

        String simulatedUserInput = encrypted.fullEncrypt[0] + System.getProperty("line.separator")
                + "e" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.enterLetter();
        System.setIn(System.in);
        String parsedEncrypted = game.parseInput();
        game.currentSol();
        assertEquals(parsedEncrypted.charAt(0),'e');

        testPlayer.printDetails();
    }
    @Test
    public void checkEnterLetterTwiceInSamePosition() {
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, " ", "./src/phrases.txt");
        game.currentSol();
        String simulatedUserInput = game.getEncrypted().fullEncrypt[0] + System.getProperty("line.separator") + "e";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.getEncrypted().guesses = game.enterLetter();

        game.currentSol();
        simulatedUserInput = game.getEncrypted().fullEncrypt[0] + System.getProperty("line.separator") + "l" + System.getProperty("line.separator") + "yes";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.getEncrypted().guesses = game.enterLetter();
        assertNotEquals(" e", game.getEncrypted().guesses[0]);
        assertEquals(" l", game.getEncrypted().guesses[0]);

        game.currentSol();
        simulatedUserInput = game.getEncrypted().fullEncrypt[0] + System.getProperty("line.separator") + "k" + System.getProperty("line.separator") + "no";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.getEncrypted().guesses = game.enterLetter();
        assertEquals(" l", game.getEncrypted().guesses[0]);
    }

    @Test
    public void checkEnterLetterAtDifferentGuessForSameTarget() {
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, "letter", "./src/phrases.txt");
        Cryptogram encrypted = game.getEncrypted();

        InputStream savedStandardInputStream = System.in;
        String simulatedUserInput = encrypted.fullEncrypt[0] + System.getProperty("line.separator")
                + "e" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        encrypted.guesses = game.enterLetter();
        System.setIn(savedStandardInputStream);
        game.currentSol();
        savedStandardInputStream = System.in;
        simulatedUserInput = encrypted.fullEncrypt[0] + System.getProperty("line.separator")
                + "d" + System.getProperty("line.separator") + "yes" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        encrypted.guesses = game.enterLetter();
        System.setIn(savedStandardInputStream);
        game.currentSol();
        String parsedEncrypted = game.parseInput();
        assertEquals(parsedEncrypted.charAt(0), 'd');

    }
    @Test
    public void checkEnterLetterAtDifferentGuessForSameTarget1() {
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, " ", "./src/phrases.txt");
        String firstEncrypt = String.valueOf(game.getEncrypted().fullEncrypt[0]);
        InputStream savedStandardInputStream = System.in;
        String simulatedUserInput = firstEncrypt + System.getProperty("line.separator")
                + "e" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.getEncrypted().guesses = game.enterLetter();
        System.setIn(savedStandardInputStream);
        savedStandardInputStream = System.in;
        simulatedUserInput = firstEncrypt + System.getProperty("line.separator")
                + "d" + System.getProperty("line.separator") + "no" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.getEncrypted().guesses = game.enterLetter();
        System.setIn(savedStandardInputStream);
        game.parseInput();
        assertNotEquals(game.parseInput().charAt(0), 'd');
        assertEquals(game.parseInput().charAt(0), 'e');

    }
    @Test
    public void checkCompletingCryptogram () {
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, "letter", "./src/test.txt");
        Cryptogram encrypted = game.getEncrypted();

        for (int i = 0; i<(encrypted.phrase.length()); i++) {
            InputStream savedStandardInputStream = System.in;
            if (!(encrypted.phrase.charAt(i) == ' ')) {
                String simulatedUserInput = encrypted.fullEncrypt[i] + System.getProperty("line.separator")
                        + encrypted.phrase.charAt(i) + System.getProperty("line.separator");

                System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
                encrypted.guesses = game.enterLetter();
                System.setIn(savedStandardInputStream);
                game.currentSol();

            }
        }

        assertEquals("hi this is a lot of is", game.parseInput());
    }

    @Test
    public void checkFailingCryptogram() {
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(player, " ", "./src/phrases.txt");
        game.getEncrypted().printDetails();
        game.getEncrypted().parsedGuesses = game.parseInput();
            for (int i = 0; i < game.getEncrypted().phrase.length(); i++) {
                String wrongInput = game.getEncrypted().fullEncrypt[i];
                if (wrongInput.equals(" ")) {
                    continue;
                }
                String simulatedUserInput = wrongInput + System.getProperty("line.separator") + wrongInput + System.getProperty("line.separator");
                System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
                game.getEncrypted().guesses = game.enterLetter();
                game.getEncrypted().parsedGuesses = game.parseInput();
                player.updateAccuracy(player.getAccuracy());
            }
        if (!(game.getEncrypted().parsedGuesses.contains("?"))) {
            System.out.println("fail!");
            player.incrementCryptogramsPlayed();
            player.printDetails();
        }
        assertEquals(0, player.getCryptogramsCompleted());
        assertEquals(1, player.getCryptogramsPlayed());
        }


    @Test
    public void checkLetterNotInCryptogram(){
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, " ", "./src/test.txt");


        InputStream savedStandardInputStream = System.in;
        String simulatedUserInput = "%" + System.getProperty("line.separator")
                + game.getEncrypted().fullEncrypt[0] + System.getProperty("line.separator") + "h";


        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.enterLetter();
        System.setIn(savedStandardInputStream);
        assertEquals(" h",game.getEncrypted().guesses[0]);

    }
    @Test
    public void checkUndoLetter(){
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, " ", "./src/test.txt");

        InputStream savedStandardInputStream = System.in;
        String simulatedUserInput = game.getEncrypted().fullEncrypt[0] + System.getProperty("line.separator")
                + "e" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.enterLetter();
        System.setIn(savedStandardInputStream);

        game.currentSol();
        testPlayer.printDetails();

        savedStandardInputStream = System.in;
        simulatedUserInput = "e" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.undoLetter();
        System.setIn(savedStandardInputStream);

        game.currentSol();
        testPlayer.printDetails();
        assertNotEquals(game.getEncrypted().guesses[0],"e");



    }
    @Test
    public void checkUndoLetterNotBeenMapped(){
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, " ", "./src/phrases.txt");
        InputStream savedStandardInputStream = System.in;
        String simulatedUserInput = String.valueOf(game.getEncrypted().fullEncrypt[0]) + System.getProperty("line.separator")
                + "e" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        System.setIn(savedStandardInputStream);
        Cryptogram currEncrypted = game.getEncrypted();
        savedStandardInputStream = System.in;
        simulatedUserInput =  "d" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        System.setIn(savedStandardInputStream);
        Cryptogram currEncrypted2 = game.getEncrypted();
        assertEquals(currEncrypted,currEncrypted2);

    }
}





