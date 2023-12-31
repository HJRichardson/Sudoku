package sudoku;

/**
* This class creates the structure for a X-Sudoku game,
* where diagonal entries also matter.
*/
public class XGameGrid extends GameGrid {
    
    /**
     * Constructor to create an XGameGrid instance from
     * a 2D array containing the sudoku values.
     * @param grid - The grid to be initialised.
     */
    public XGameGrid(int[][] grid) {
        super(grid);
    }

    /**
     * Constructor to create an XGameGrid instance from
     * a file path.
     * @param path - The file path to the sudoku file.
     */    
    public XGameGrid(String path) {
        super(path);
    }

    /**
     * Constructor to create an XGameGrid instance from
     * another GameGrid instance.
     * @param gameGrid - The GameGrid instance.
     */    
    public XGameGrid(GameGrid gameGrid) {
        super(gameGrid);
    }

    /**
     * Checks whether the value could be placed in the diagonal(s).
     * 
     * @param row - Row of the grid.
     * @param col - Column of the grid.
     * @param value - Value to set.
     * @param explanation - Whether the program should display why an input cannot be made.
     * @return If the value is valid in terms of the diagonal(s).
     */
    private boolean checkDiagonal(int row, int col, int value, boolean explanation) {
        if (row != col && row != GRID_DIM - col - 1) {
            return false;
        }

        if (row == col) {
            for (int i = 0; i < GRID_DIM; i++) {
                if (row != i && grid[i][i].getValue() == value) {
                    if (explanation) {
                        System.out.printf("There is a %d on the diagonal! Located at (%d, %d).\n", value, i+1, i+1);
                    }
                    return true;
                }
            }
        } else {
            for (int i = 0; i < GRID_DIM; i++) {
                if (row != i && grid[i][GRID_DIM-i-1].getValue() == value) {
                    if (explanation) {
                        System.out.printf("There is a %d on the diagonal! Located at (%d, %d).\n", value, i+1, i+1);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks whether the value to set is a valid move, using the original rules
     * and the new diagonal rule.
     * 
     * @param row - Row of the grid.
     * @param col - Column of the grid.
     * @param value - Value to set.
     * @param explanation - Whether the program should display why an input cannot be made.
     * @return If the value is a valid move.
     */
    @Override
    public boolean isValid(int row, int col, int value, boolean explanation) {
        boolean checkOriginalRules = super.isValid(row, col, value, explanation);
        return checkOriginalRules && !checkDiagonal(row, col, value, explanation);
    }

    /**
     * Checks whether the position on the grid is on one of the diagonals.
     * 
     * @param row - Row of the grid.
     * @param col - Column of the grid.
     * @return If the position is on one of the diagonals.
     */
    public static boolean onDiagonal(int row, int col) {
        if (row == col || row == GRID_DIM - col - 1) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the grid, with the diagonal entries
     * surrounded by braces to make them stand out..
     * @return A string representation of the grid.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < GRID_DIM; row++) {
            for (int col = 0; col < GRID_DIM; col++) {
                Field element = this.grid[row][col];
                if (onDiagonal(row, col)) {
                    sb.append("[" + element + "]");
                } else {
                    sb.append(" " + element + " ");
                }
                sb.append(" ");
                if (!element.isInitial()) {
                    sb.append(" ");
                }
                if (endOfSubgrid(col)) {
                    sb.append("| ");
                }
            }
            sb.append("\n");
            if (endOfSubgrid(row)) {
                sb.append("------------------------------------------------\n");
            }
        }
        return sb.toString();
    }
}
