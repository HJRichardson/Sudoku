package sudoku;

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
        if (row != col && row != GameGrid.GRID_DIM - col - 1) {
            return false;
        }

        if (row == col) {
            for (int i = 0; i < GameGrid.GRID_DIM; i++) {
                if (row != i && grid[i][i].getValue() == value) {
                    if (explanation) {
                        System.out.printf("There is a %d on the diagonal! Located at (%d, %d).\n", value, i+1, i+1);
                    }
                    return true;
                }
            }
        } else {
            for (int i = 0; i < GameGrid.GRID_DIM; i++) {
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
     * Returns a string representation of the grid, with the diagonal entries
     * surrounded by braces to make them stand out..
     * @return A string representation of the grid.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                Field element = this.grid[row][col];
                if (col == row || row == GameGrid.GRID_DIM - col - 1) {
                    sb.append("[" + element + "]");
                } else {
                    sb.append(" " + element + " ");
                }
                sb.append(" ");
                if (!element.isInitial()) {
                    sb.append(" ");
                }
                if ((col + 1) % SUBGRID_DIM == 0 && (col != grid[0].length - 1)) {
                    sb.append("| ");
                }
            }
            sb.append("\n");
            if ((row + 1) % SUBGRID_DIM == 0 && row != grid.length - 1) {
                sb.append("------------------------------------------------\n");
            }
        }
        return sb.toString();
    }
}
