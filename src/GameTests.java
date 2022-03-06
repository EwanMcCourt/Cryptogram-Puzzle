import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


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
        game.getEncrypted().fullEncrypt[i]
    }
/*

    @Test //the code seems to work but test fails as program ends
    public void checkEmptyPhrases(){
        Game testGame = new Game();
        //assertThrows(IOException.class), () -> testGame.callPhrase("./src/empty.txt"), "Error, no phrase file!");


    }
    @Test //the code seems to work but test fails as program ends
    public void checkNoFile(){
        Game testGame = new Game();
        //assertThrows(IOException.class), () -> testGame.callPhrase("./src/doesntexist.txt"), "Error, no phrase file!");


    }
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




    }
    @Test
    public void checkEnterLetterTwiceInSamePosition(){
        Cryptogram encrypted = Game.generateCryptogram();
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        String parsedEncrypted1 = "";
        String parsedEncrypted2 = "";
        for (int i = 0; i<2; i++) {
            InputStream savedStandardInputStream = System.in;
            String simulatedUserInput = String.valueOf(encrypted.fullEncrypt.charAt(0)) + System.getProperty("line.separator")
                    + "e" + System.getProperty("line.separator");

            System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
            encrypted.guesses = Game.enterLetter(encrypted, testPlayer);
            System.setIn(savedStandardInputStream);
            Game.currentSol(encrypted, testPlayer);
            if (i==0){
                parsedEncrypted1 = Game.parseInput(encrypted);
            } else {
                parsedEncrypted2 = Game.parseInput(encrypted);
            }
        }
        assertEquals(parsedEncrypted1.charAt(0),parsedEncrypted2.charAt(0) );



    }
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

    }
    @Test
    public void checkEnterLetterAtDifferentGuessForSameTarget1() {
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
                + "d" + System.getProperty("line.separator") + "no" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        encrypted.guesses = Game.enterLetter(encrypted, testPlayer);
        System.setIn(savedStandardInputStream);
        Game.currentSol(encrypted, testPlayer);
        String parsedEncrypted = Game.parseInput(encrypted);
        assertNotEquals(parsedEncrypted.charAt(0), 'd');

    }
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
    @Test
    public void checkFailingCryptogram(){




    }
    @Test
    public void checkLetterNotInCryptogram(){
        Cryptogram encrypted = Game.generateCryptogram();
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);
        String letters = "abcdefghijklmnopqrstuvwxyz";
        char currGuess = 'a';
        Cryptogram currEncrypted = encrypted;
        String parsedEncrypted = Game.parseInput(encrypted);

        for (int i =0; i<26; i++){
            if (encrypted.fullEncrypt.contains("" + letters.charAt(i)) == false){
                currGuess = letters.charAt(i);
            }
        }

        InputStream savedStandardInputStream = System.in;
        String simulatedUserInput = currGuess + System.getProperty("line.separator")
                + "f" + System.getProperty("line.separator");


        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        encrypted.guesses = Game.enterLetter(encrypted, testPlayer);
        System.setIn(savedStandardInputStream);
        Game.currentSol(encrypted, testPlayer);
        assertEquals(currEncrypted,encrypted);

    }
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



    }
    @Test
    public void checkUndoLetterNotBeenMapped(){
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
        Cryptogram currEncrypted = encrypted;

        savedStandardInputStream = System.in;
        simulatedUserInput =  "d" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        testResult = Game.undoLetter(encrypted, testPlayer );
        System.setIn(savedStandardInputStream);

        Game.currentSol(encrypted, testPlayer);
        testPlayer.printDetails();
        Cryptogram currEncrypted2 = encrypted;
        assertEquals(currEncrypted,currEncrypted2);

    }*/
}





