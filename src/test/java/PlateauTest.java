import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.Before;

public class PlateauTest {

  private Plateau plateau;
  private Rover rover;

  @Before
  public void beforePlateauTest() {
    plateau = new Plateau("5 5");
    rover = mock(Rover.class);
  }

  @Test
  public void newInstanceContainsGridAccordingToSpecifiedCoordinates() {
    assertSame(36, plateau.getGrid().size());
  }

  @Test
  public void gridHasCellForEachCoordinate() {
    for (Object value: plateau.getGrid().values()) {
      assertSame(Cell.class, value.getClass());
    }
  }

  @Test
  public void canGetSpecifiedCell() {
    assertSame(Cell.class, plateau.getCell("1 2").getClass());
  }

  @Test
  public void canPlaceRoverOnCell() {
    String coords = "1 2";
    plateau.placeRover(coords, rover);
    assertSame(rover, plateau.getCell("1 2").getContent());
  }

  @Test
  public void willRaiseAnErrorIfSpecifiedCellDoesNotExist() {
    try {
      plateau.placeRover("6 6", rover);
      fail("Expected an illegalArgumentException to be thrown");
    } catch(IllegalArgumentException anIllegalArgumentException) {
      assertSame("Invalid coordinates", anIllegalArgumentException.getMessage());
    }
  }

}
