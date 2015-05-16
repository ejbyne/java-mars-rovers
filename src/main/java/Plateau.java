import java.util.TreeMap;

public class Plateau {

  private TreeMap<String, Cell> grid = new TreeMap<String, Cell>();

  public Plateau(String maxCoords) {
    createGrid(maxCoords);
  }

  public TreeMap getGrid() {
    return grid;
  }

  public Cell getCell(String coords) {
    return grid.get(coords);
  }

  public void placeRover(String coords, Rover rover) {
    areValidPlaceCoords(coords);
    getCell(coords).setContent(rover);
  }

  public void moveRover(String startCoords, String endCoords, Rover rover) {
    areValidMoveCoords(startCoords, endCoords);
    getCell(startCoords).setContent(null);
    getCell(endCoords).setContent(rover);
  }

  private void createGrid(String maxCoords) {
    for (int x = 0; x <= Integer.parseInt(maxCoords.split(" ")[0]); x++) {
      for (int y = 0; y <= Integer.parseInt(maxCoords.split(" ")[1]); y++) {
        grid.put(String.format("%s %s", x, y), new Cell());
      }  
    }
  }

  private void areValidPlaceCoords(String coords) {
    if (!grid.containsKey(coords)) {
      throw new IllegalArgumentException("Invalid coordinates");
    }
  }

  private void areValidMoveCoords(String startCoords, String endCoords) {
    if (!grid.containsKey(endCoords)) {
      throw new IllegalArgumentException("Cannot move outside plateau. " +
        "Rover stopped at coordinates " + startCoords);
    } else if (getCell(endCoords).getContent() != null) {
      throw new IllegalArgumentException("Cannot move rover onto cell occupied " +
        "by another rover. Rover stopped at coordinates " + startCoords);
    }
  }

}
