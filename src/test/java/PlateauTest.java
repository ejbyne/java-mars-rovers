import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.Before;

public class PlateauTest {

  private Plateau plateau;
  private Cell cell;

  @Before
  public void beforePlateauTest() {
    plateau = new Plateau("5 5");
    cell = mock(Cell.class);
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

}
