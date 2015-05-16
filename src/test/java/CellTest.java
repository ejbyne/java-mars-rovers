import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.Before;

public class CellTest {

  private Cell cell;
  private Rover rover;

  @Before
  public void beforeCellTest() {
    cell = new Cell();
  }

  @Test
  public void newInstanceHasNullContent() {
    assertSame(null, cell.getContent());
  }

  @Test
  public void canHoldARover() {
    rover = mock(Rover.class);
    cell.setContent(rover);
    assertSame(rover, cell.getContent());
  }

}
