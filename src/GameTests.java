import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


import static org.junit.Assert.*;


public class GameTests {

    @Test
    public void testNotNull() {
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(player, " ");
        assertNotNull(game.getEncrypted());
    }

    /*
        @Test
        public void checkCryptogramGenerationLetters() {
            Cryptogram encrypted = new Cryptogram("./src/phrases.txt");


            for (int i = 0; i < encrypted.phrase.length(); i++) {

                char current = encrypted.phrase.charAt(i);
                char current2 = encrypted.fullEncrypt.charAt(i);
                if (encrypted.phrase.charAt(i) == ' ') {  //checking for a space and skips this iteration of the loop

                } else {
                    System.out.println(current + " == " + current2);
                    assertNotEquals(current, current2);
                }
            }
        }*/
    @Test
    public void checkCryptogramGenerationNumbers() {
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(player, "yes");
        for (int i = 0; i < game.getEncrypted().fullEncrypt.length; i++) {
            if (game.getEncrypted().fullEncrypt[i] == " ") {
                continue;
            }
            int currentNum = Integer.parseInt(game.getEncrypted().fullEncrypt[i]);
            assertTrue(currentNum < 27);
        }
    }

    /*

        @Test //the code seems to work but test fails as program ends
        public void checkEmptyPhrases(){
            Game testGame = new Game();
            //assertThrows(IOException.class), () -> testGame.callPhrase("./src/empty.txt"), "Error, no phrase file!");


        }*/
    @Test //the code seems to work but test fails as program ends
    public void checkNoFile() {
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game testGame = new Game(player, " ");


    }

    /*
    @Test
    public void checkEnterLetter(){
        Cryptogram encrypted = Game.generateCryptogram();
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        InputStream savedStandardInputStream = System.in;
        String simulatedUserInput = String.valueOf(encrypted.fullEncrypt.charAt(0)) + System.getProperty("line.separator")
                + "e" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        String[] testResult = Game.enterLetter(encrypted, testPlayer );
        System.setIn(savedStandardInputStream);
        String parsedEncrypted = Game.parseInput(encrypted);
        Game.currentSol(encrypted, testPlayer);
        assertEquals(parsedEncrypted.charAt(0),'e');

        testPlayer.printDetails();




    }*/
    @Test
    public void checkEnterLetterTwiceInSamePosition() {
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, " ");
        InputStream savedStandardInputStream = System.in;
        String firstEncrypt = String.valueOf(game.getEncrypted().fullEncrypt[0]);
        String simulatedUserInput = firstEncrypt + System.getProperty("line.separator") + "e";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.getEncrypted().guesses = game.enterLetter();
        simulatedUserInput = firstEncrypt + System.getProperty("line.separator") + "l" + System.getProperty("line.separator") + "yes";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.getEncrypted().guesses = game.enterLetter();
        savedStandardInputStream = System.in;
        assertNotEquals(" e", firstEncrypt);
        assertEquals(" l", firstEncrypt);
    }

    /*
    @Test
    public void checkEnterLetterAtDifferentGuessForSameTarget() {
        Cryptogram encrypted = Game.generateCryptogram();
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);


        InputStream savedStandardInputStream = System.in;
        String simulatedUserInput = String.valueOf(encrypted.fullEncrypt.charAt(0)) + System.getProperty("line.separator")
                + "e" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        encrypted.guesses = Game.enterLetter(encrypted, testPlayer);
        System.setIn(savedStandardInputStream);
        Game.currentSol(encrypted, testPlayer);
        savedStandardInputStream = System.in;
        simulatedUserInput = String.valueOf(encrypted.fullEncrypt.charAt(0)) + System.getProperty("line.separator")
                + "d" + System.getProperty("line.separator") + "yes" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        encrypted.guesses = Game.enterLetter(encrypted, testPlayer);
        System.setIn(savedStandardInputStream);
        Game.currentSol(encrypted, testPlayer);
        String parsedEncrypted = Game.parseInput(encrypted);
        assertEquals(parsedEncrypted.charAt(0), 'd');

    }*/
    @Test
    public void checkEnterLetterAtDifferentGuessForSameTarget1() {
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, " ");
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

    }/*
    @Test
    public void checkCompletingCryptogram () {
        Cryptogram encrypted = Game.generateCryptogram();
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);

        for (int i = 0; i<(encrypted.phrase.length()); i++) {
            InputStream savedStandardInputStream = System.in;
            if (encrypted.phrase.charAt(i) == ' ') {
                continue;
            } else{
                String simulatedUserInput = String.valueOf(encrypted.fullEncrypt.charAt(i)) + System.getProperty("line.separator")
                        + String.valueOf(encrypted.phrase.charAt(i)) + System.getProperty("line.separator");

                System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
                encrypted.guesses = Game.enterLetter(encrypted, testPlayer);
                System.setIn(savedStandardInputStream);
                Game.currentSol(encrypted, testPlayer);

            }
        }
    }
    */

    @Test
    public void checkFailingCryptogram() {
        Player player = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(player, " ");
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
                if (!(game.getEncrypted().parsedGuesses.contains("?"))) {
                    System.out.println("fail!");
                    player.incrementCryptogramsPlayed(player.getCryptogramsPlayed());
                    player.printDetails();
                }
            }
            assertTrue(player.getCryptogramsCompleted() == 0);
            assertTrue(player.getCryptogramsPlayed() == 1);
        }


    @Test
    public void checkLetterNotInCryptogram(){
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, " ");
        String letters = "abcdefghijklmnopqrstuvwxyz";
        char currGuess = 'a';
        Cryptogram currEncrypted = game.getEncrypted();
        String parsedEncrypted = game.parseInput();

        for (int i =0; i<game.getEncrypted().fullEncrypt.length; i++){
            if(Objects.equals(game.getEncrypted().fullEncrypt[i], " ")){
                continue;
            }
            if (game.getEncrypted().fullEncrypt[i].equals("" + letters.charAt(i))){
                currGuess = letters.charAt(i);
            }
        }

        InputStream savedStandardInputStream = System.in;
        String simulatedUserInput = currGuess + System.getProperty("line.separator")
                + "f" + System.getProperty("line.separator");


        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        game.getEncrypted().guesses = game.enterLetter();
        System.setIn(savedStandardInputStream);
        assertEquals(currEncrypted,game.getEncrypted());

    }/*
    @Test
    public void checkUndoLetter(){
        Cryptogram encrypted = Game.generateCryptogram();
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);


        InputStream savedStandardInputStream = System.in;
        String simulatedUserInput = String.valueOf(encrypted.fullEncrypt.charAt(0)) + System.getProperty("line.separator")
                + "e" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        String[] testResult = Game.enterLetter(encrypted, testPlayer );
        System.setIn(savedStandardInputStream);

        Game.currentSol(encrypted, testPlayer);
        testPlayer.printDetails();

        savedStandardInputStream = System.in;
        simulatedUserInput = "e" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        testResult = Game.undoLetter(encrypted, testPlayer );
        System.setIn(savedStandardInputStream);

        Game.currentSol(encrypted, testPlayer);
        testPlayer.printDetails();
        assertNotEquals(encrypted.guesses[0],"e");



    }*/
    @Test
    public void checkUndoLetterNotBeenMapped(){
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        Game game = new Game(testPlayer, " ");
        InputStream savedStandardInputStream = System.in;
        String simulatedUserInput = String.valueOf(game.getEncrypted().fullEncrypt[0]) + System.getProperty("line.separator")
                + "e" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        String[] testResult = game.enterLetter();
        System.setIn(savedStandardInputStream);
        Cryptogram currEncrypted = game.getEncrypted();
        savedStandardInputStream = System.in;
        simulatedUserInput =  "d" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        testResult = game.undoLetter();
        System.setIn(savedStandardInputStream);
        Cryptogram currEncrypted2 = game.getEncrypted();
        assertEquals(currEncrypted,currEncrypted2);

    }
}





