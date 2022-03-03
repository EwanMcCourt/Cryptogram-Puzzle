import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class GameTests {

    @Test
  public void testNotNull(){
   Cryptogram encrypted = Game.generateCryptogram();
    assertTrue(encrypted != null);
    }


}
