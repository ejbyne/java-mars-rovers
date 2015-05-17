import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MissionControl {

  private Plateau plateau;
  private Rover rover;
  private String roverCoords;

  public MissionControl(Plateau plateau) {
    this.plateau = plateau;
  }

  public void selectRover(String position) {
    isValidPosition(position);
    roverCoords = String.format("%s %s", position.split(" ")[0], position.split(" ")[1]);
    rover = new Rover(position.split(" ")[2]);
    plateau.placeRover(roverCoords, rover);
  }

  public Rover getRover() {
    return rover;
  }

  public String getRoverOrientation() {
    return rover.getOrientation();
  }

  public void commandRover(String commands) {
    areValidCommands(commands);
    issueCommands(commands.split(""));
  }

  private void isValidPosition(String position) {
    Pattern pattern = Pattern.compile("^\\d\\s\\d\\s[NESW]$");
    Matcher matcher = pattern.matcher(position);
    if (!matcher.find()) {
      throw new IllegalArgumentException("Invalid position");
    }
  }

  private void areValidCommands(String commands) {
    Pattern pattern = Pattern.compile("^[LRM]+$");
    Matcher matcher = pattern.matcher(commands);
    if (!matcher.find()) {
      throw new IllegalArgumentException("Invalid command");
    }  
  }

  private void issueCommands(String[] commands) {
    for (int i = 0; i < commands.length; i++) {
      if (isTurnCommand(commands[i])) {
        turnRover(commands[i]);
      } else if (isMoveCommand(commands[i])) {
        moveRover();
      }
    }
  }

  private boolean isTurnCommand(String command) {
    return command.equals("L") || command.equals("R");
  }

  private boolean isMoveCommand(String command) {
    return command.equals("M");
  }

  private void turnRover(String command) {
    rover.turn(command);
  }

  private void moveRover() {
    String newRoverCoords = findNewRoverCoords();
    plateau.moveRover(roverCoords, newRoverCoords, rover);
  }

  private String findNewRoverCoords() {
    int x = Integer.parseInt(roverCoords.split(" ")[0]);
    int y = Integer.parseInt(roverCoords.split(" ")[1]);
    return calculateNewCoords(x, y);
  }

  private String calculateNewCoords(int x, int y) {
    if (getRoverOrientation().equals("N")) {
      return String.format("%s %s", x, y + 1 );
    } else if (getRoverOrientation().equals("E")) {
      return String.format("%s %s", x + 1, y);
    } else if (getRoverOrientation().equals("S")) {
      return String.format("%s %s", x, y - 1);
    } else {
      return String.format("%s %s", x - 1, y);
    }
  }

}
