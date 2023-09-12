import java.util.Objects;

public class RGameGrid extends GameGrid {

    /**
     * Constructor to create an RGameGrid instance from
     * a 2D array containing the sudoku values.
     * @param grid - The grid to be initialised.
     */
    public RGameGrid(int[][] grid) {
        super(grid);
    }

    /**
     * Constructor to create an RGameGrid instance from
     * a file path.
     * @param path - The file path to the sudoku file.
     */    
    public RGameGrid(String path) {
        super(path);
    }

    /**
     * Constructor to create an RGameGrid instance from
     * another GameGrid instance.
     * @param gameGrid - The GameGrid instance.
     */    
    public RGameGrid(GameGrid gameGrid) {
        super(gameGrid);
    }
}
