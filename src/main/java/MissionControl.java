public class MissionControl {

  private Plateau plateau;
  private Rover rover;
  private String roverCoords;

  public MissionControl(Plateau plateau) {
    this.plateau = plateau;
  }

  public void selectRover(String position) {
    String[] positionArray = position.split(" ");
    roverCoords = String.format("%s %s", positionArray[0], positionArray[1]);
    rover = new Rover(positionArray[2]);
    plateau.placeRover(roverCoords, rover);
  }

  public Rover getRover() {
    return rover;
  }

  public String getRoverOrientation() {
    return rover.getOrientation();
  }

  public void commandRover(String commands) {
    String[] commandArray = commands.split("");
    for (int i = 0; i < commandArray.length; i++) {
      if (commandArray[i].equals("L") || commandArray[i].equals("R")) {
        rover.turn(commandArray[i]);
      } else if (commandArray[i].equals("M")) {
        String newRoverCoords = findNewRoverCoords();
        plateau.moveRover(roverCoords, newRoverCoords, rover);
      }
    }
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
