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

}
