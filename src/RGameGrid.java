import java.util.Objects;

public class RGameGrid extends GameGrid {

    public RGameGrid(int[][] grid) {
        super(grid);
    }

    public RGameGrid(String path) {
        super(path);
    }

    public RGameGrid(GameGrid gameGrid) {
        super(gameGrid);
    }
}
