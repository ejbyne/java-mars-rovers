public class Rover {

  private String[] orientationOptions = new String[]{ "N", "E", "S", "W"};
  private int orientationIndex;

  public Rover(String orientation) {
    orientationIndex = findOrientationIndex(orientation);
  }

  public String getOrientation() {
    return orientationOptions[orientationIndex];
  }

  public void turn(String direction) {
    if (direction.equals("L")) {
      turnLeft();
    } else if (direction.equals("R")) {
      turnRight();
    } else {
      throw new IndexOutOfBoundsException("Invalid direction");
    }
  }

  private int findOrientationIndex(String orientation) {
    for (int index = 0; index < orientationOptions.length; index ++) {
      if (orientationOptions[index].equals(orientation)) {
        return index;
      }
    }
    throw new IndexOutOfBoundsException("Invalid orientation");
  }

  private void turnLeft() {
    if (orientationIndex == 0) {
      orientationIndex = orientationOptions.length - 1;
    } else {
      orientationIndex -= 1;
    }
  }

  private void turnRight() {
    if (orientationIndex == orientationOptions.length - 1) {
      orientationIndex = 0;
    } else {
      orientationIndex += 1;
    }
  }

}
