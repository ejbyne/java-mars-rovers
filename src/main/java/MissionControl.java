public class MissionControl {

  private Plateau plateau;
  private Rover rover;

  public MissionControl(Plateau plateau) {
    this.plateau = plateau;
  }

  public void selectRover(String position) {
    String[] positionArray = position.split(" ");
    String coords = String.format("%s %s", positionArray[0], positionArray[1]);
    rover = new Rover(positionArray[2]);
    plateau.placeRover(coords, rover);
  }

  public String getRoverOrientation() {
    return rover.getOrientation();
  }

  public void commandRover(String commands) {
    String[] commandArray = commands.split("");
    for (int i = 0; i < commandArray.length; i++) {
      if (commandArray[i].equals("L") || commandArray[i].equals("R")) {
        rover.turn(commandArray[i]);
      }
    }
  }
}
