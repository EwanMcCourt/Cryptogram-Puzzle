import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class GameTests {

    @Test
  public void testNotNull(){
   String encrypted = Game.generateCryptogram("testing");
    assertTrue(encrypted != null);
    }


}
