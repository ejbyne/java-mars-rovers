import java.util.TreeMap;

public class Plateau {

  private TreeMap<String, Cell> grid = new TreeMap<String, Cell>();

  public Plateau(String maxCoords) {
    createGrid(maxCoords);
  }

  public TreeMap getGrid() {
    return grid;
  }

  private void createGrid(String maxCoords) {
    for (int x = 0; x <= Integer.parseInt(maxCoords.split(" ")[0]); x++) {
      for (int y = 0; y <= Integer.parseInt(maxCoords.split(" ")[1]); y++) {
        grid.put(String.format("%s %s", x, y), new Cell());
      }  
    }
  }

}