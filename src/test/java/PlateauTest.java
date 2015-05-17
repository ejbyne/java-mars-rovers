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
  public void willRaiseErrorIfInvalidMaxCoords() {
    try {
      new Plateau("55");
      fail("Expected an IllegalArgumentException to be thrown");
    } catch(IllegalArgumentException anIllegalArgumentException) {
      assertSame("Invalid coordinates", anIllegalArgumentException.getMessage());
    }
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
  public void willRaiseErrorIfSpecifiedCellDoesNotExist() {
    try {
      plateau.placeRover("6 6", rover);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch(IllegalArgumentException anIllegalArgumentException) {
      assertSame("Invalid coordinates", anIllegalArgumentException.getMessage());
    }
  }

  @Test
  public void canMoveRoverToDifferentCell() {
    String startCoords = "1 2";
    String endCoords = "1 3";
    plateau.placeRover(startCoords, rover);
    System.out.println(plateau.getCell(endCoords).getContent());
    plateau.moveRover(startCoords, endCoords, rover);
    assertSame(null, plateau.getCell(startCoords).getContent());
    assertSame(rover, plateau.getCell(endCoords).getContent());
  }

  @Test
  public void willRaiseErrorIfRoverTriesToMoveOutsidePlateau(){
    plateau.placeRover("5 5", rover);
    try {
      plateau.moveRover("5 5", "6 5", rover);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch(IllegalArgumentException anIllegalArgumentException) {
      assertEquals("Cannot move outside plateau. Rover stopped at coordinates 5 5",
        anIllegalArgumentException.getMessage());
    }
  }

  @Test
  public void willRaiseErrorIfRoverTriesToMoveToCellOccupiedByAnotherRover() {
    plateau.placeRover("1 2", rover);
    plateau.placeRover("1 3", rover);
    try {
      plateau.moveRover("1 2", "1 3", rover);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch(IllegalArgumentException anIllegalArgumentException) {
      assertEquals("Cannot move rover onto cell occupied by another rover. " +
        "Rover stopped at coordinates 1 2", anIllegalArgumentException.getMessage());
    }
  }

}
