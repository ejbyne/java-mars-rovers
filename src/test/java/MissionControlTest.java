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
    verify(plateau).placeRover(anyString(), anyObject());
  }

  @Test
  public void canTurnRoverInSpecifiedDirection() {
    missionControl.selectRover("1 2 N");
    missionControl.commandRover("L");
    assertEquals("W", missionControl.getRoverOrientation());
  }

}
