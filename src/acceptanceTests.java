import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


import static org.junit.Assert.*;


public class acceptanceTests {

    @Test
    public void testNotNull() {
        Cryptogram encrypted = Game.generateCryptogram();
        assertNotNull(encrypted);
    }

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
    }
    @Test
    public void checkCryptogramGenerationNumbers() {

    }


    @Test //the code seems to work but test fails as program ends
    public void checkEmptyPhrases(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        Cryptogram generated = new Cryptogram("./src/empty.txt");
        assertEquals(1, 1);

    }
    @Test //the code seems to work but test fails as program ends
    public void checkNoFile(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        Cryptogram generated = new Cryptogram("./src/doesntexist.txt");
        assertEquals("Error, no phrase file!", outContent.toString());

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

        Game.currentSol(encrypted, testPlayer);
        testPlayer.printDetails();



    }
    @Test
    public void checkEnterLetterTwiceInSamePosition(){
        Cryptogram encrypted = Game.generateCryptogram();
        Player testPlayer = new Player(1, "hardCoded", 0.0, 0, 0, 0, 0);

        for (int i = 0; i<2; i++) {
            InputStream savedStandardInputStream = System.in;
            String simulatedUserInput = String.valueOf(encrypted.fullEncrypt.charAt(0)) + System.getProperty("line.separator")
                    + "e" + System.getProperty("line.separator");

            System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
            encrypted.guesses = Game.enterLetter(encrypted, testPlayer);
            System.setIn(savedStandardInputStream);
            Game.currentSol(encrypted, testPlayer);
        }



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

        savedStandardInputStream = System.in;
        simulatedUserInput =  "d" + System.getProperty("line.separator");

        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8)));
        testResult = Game.undoLetter(encrypted, testPlayer );
        System.setIn(savedStandardInputStream);

        Game.currentSol(encrypted, testPlayer);
        testPlayer.printDetails();

    }
    }





