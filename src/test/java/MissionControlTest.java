import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.Before;

public class MissionControlTest {

  private Plateau plateau;
  private MissionControl missionControl;

  @Before
  public void beforeMissionControlTest() {
    plateau = mock(Plateau.class);
    missionControl = new MissionControl(plateau);
  }

  @Test
  public void canSelectRoverInSpecifiedPosition() {
    missionControl.selectRover("1 2 N");
    verify(plateau).placeRover("1 2", missionControl.getRover());
  }

  @Test
  public void willNotAcceptInvalidPosition() {
    try {
      missionControl.selectRover("12N");
      fail("Expected an IllegalArgumentException to be thrown");
    } catch(IllegalArgumentException anIllegalArgumentException) {
      assertSame("Invalid position", anIllegalArgumentException.getMessage());
    }
    try {
      missionControl.selectRover("1 2 Z");
      fail("Expected an IllegalArgumentException to be thrown");
    } catch(IllegalArgumentException anIllegalArgumentException) {
      assertSame("Invalid position", anIllegalArgumentException.getMessage());
    }
  }

  @Before
  public void beforeIssuingCommands() {
    missionControl.selectRover("1 2 N");
  }

  @Test
  public void canTurnRoverInSpecifiedDirection() {
    missionControl.commandRover("L");
    assertEquals("W", missionControl.getRoverOrientation());
  }

  @Test
  public void canMoveRoverinDirectionItIsFacing() {
    missionControl.commandRover("M");
    verify(plateau).moveRover("1 2", "1 3", missionControl.getRover());
  }

  @Test
  public void canProvideSeriesOfCommands() {
    missionControl.commandRover("LMLMLMLMM");
    assertEquals("N", missionControl.getRoverOrientation());
    verify(plateau, times(5)).moveRover(anyString(), anyString(), anyObject());
  }

  @Test
  public void willNotAcceptInvalidCommands() {
    try {
      missionControl.commandRover("");
      fail("Expected an IllegalArgumentException to be thrown");
    } catch(IllegalArgumentException anIllegalArgumentException) {
      assertSame("Invalid command", anIllegalArgumentException.getMessage());
    }
    try {
      missionControl.commandRover("Z");
      fail("Expected an IllegalArgumentException to be thrown");
    } catch(IllegalArgumentException anIllegalArgumentException) {
      assertSame("Invalid command", anIllegalArgumentException.getMessage());
    }    
  }

}
