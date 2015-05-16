import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class RoverTest {

  private Rover rover;

  @Before
  public void beforeRoverTest() {
    rover = new Rover("N");
  }

  @Test
  public void newInstanceHasAnOrientation() {
    assertSame("N", rover.orientation());
  }

  @Test
  public void canTurnLeft() {
    rover.turn("L");
    assertSame("W", rover.orientation());
    for (int i = 1; i <= 3; i++) {
      rover.turn("L");
    }
    assertSame("N", rover.orientation());
  }

  @Test
  public void canTurnRight() {
    rover.turn("R");
    assertSame("E", rover.orientation());
    for (int i = 1; i <= 3; i++) {
      rover.turn("R");
    }
    assertSame("N", rover.orientation());
  }

}
