package sudoku;

import java.lang.StringBuilder;
import java.util.Objects;

public abstract class GameGrid {
    
    // Constants for the grid, including dimensions and
    // possible values.
    public static final int GRID_DIM = 9;
    public static final int SUBGRID_DIM = GRID_DIM / 3;
    public static final int MAX_VAL = 9;
    public static final int MIN_VAL = 1;
    public static final int EMPTY_VAL = 0;

    // Grid instance variable
    protected final Field[][] grid;

    /**
     * Constructor to create a GameGrid instance from
     * a 2D array containing the sudoku values.
     * @param grid - The grid to be initialised.
     */
    public GameGrid(int[][] grid) {
        Objects.requireNonNull(grid, "grid must not be null");
        this.grid = initialiseGrid(grid);
    }

    /**
     * Constructor to create a GameGrid instance from
     * a file path.
     * @param path - The file path to the sudoku file.
     */
    public GameGrid(String path) {
        int[][] grid = IOUtils.loadFromFile(path);
        Objects.requireNonNull(grid, "grid must not be null");
        this.grid = initialiseGrid(grid);
    }

    /**
     * Creates a deep copy of the current sudoku grid.
     * @param gameGrid - The GameGrid instance to create a copy of.
     */
    public GameGrid(GameGrid gameGrid) {
        Objects.requireNonNull(gameGrid);

        this.grid = new Field[GRID_DIM][GRID_DIM];
        for (int row = 0; row < GRID_DIM; row++) {
            for (int col = 0; col < GRID_DIM; col++) {
                this.grid[row][col] = new Field(gameGrid.getField(row, col), gameGrid.isInitial(row, col));
            }
        }
    }

    /**
     * Creates a deep copy of {@code gameGrid}, calling the constructor
     * based on if {@code gameGrid} is of type {@code RGameGrid} or
     * {@code XGameGrid}.
     * 
     * @param gameGrid - The GameGrid instance.
     * @return The deep copy of {@code gameGrid}.
     */
    public static GameGrid copyGameGrid(GameGrid gameGrid) {
        Objects.requireNonNull(gameGrid);

        if (gameGrid instanceof RGameGrid) {
            return new RGameGrid(gameGrid);
        } else {
            return new XGameGrid(gameGrid);
        }
    }

    /**
     * Initialises a GameGrid instance based on a 2D array of
     * grid values. Each entry is of type Field, containing the value
     * of the sudoku and a boolean of whether it is an initial field.
     * 
     * @param grid - 2D array of sudoku values.
     * @return The 2D array of Field values.
     */
    private Field[][] initialiseGrid(int[][] grid) {
        Objects.requireNonNull(grid);
        
        Field[][] initialisedGrid = new Field[GRID_DIM][GRID_DIM];
        for (int row = 0; row < GRID_DIM; row++) {
            for (int col = 0; col < GRID_DIM; col++) {
                int value = grid[row][col];
                if (value != EMPTY_VAL) {
                    initialisedGrid[row][col] = new Field(value, true);
                } else {
                    initialisedGrid[row][col] = new Field();
                }
            }
        }
        return initialisedGrid;
    }

    /**
     * Gets the value of a Field.
     * 
     * @param row - Row of the grid.
     * @param col - Column of the grid.
     * @return The value at (row, col).
     */
    public int getField(int row, int col) {
        return grid[row][col].getValue();
    }

    /**
     * Sets the value of a Field assuming it follows
     * the sudoku rules.
     * 
     * @param row - Row of the grid.
     * @param col - Column of the grid.
     * @param value - Value to set
     * @param explanation - Whether the program should display why an input cannot be made.
     */
    public boolean setField(int row, int col, int value, boolean explanation) {
        if (isValid(row, col, value, explanation)) {
            grid[row][col].setValue(value);
            return true;
        }
        return false;
    }

    /**
     * Clears the value of a sudoku field (if possible).
     * 
     * @param row - Row of the grid.
     * @param col - Column of the grid.
     */
    public void clearField(int row, int col) {
        if (!isInitial(row, col)) {
            grid[row][col].setValue(EMPTY_VAL);
        }
    }

    /**
     * Returns if the field is an initial one.
     * 
     * @param row - Row of the grid.
     * @param col - Column of the grid.
     * @return If the value is initial.
     */
    public boolean isInitial(int row, int col) {
        return grid[row][col].isInitial();
    }

    /**
     * Checks whether the value could be placed in the row.
     * 
     * @param row - Row of the grid.
     * @param col - Column of the grid.
     * @param value - Value to set.
     * @param explanation - Whether the program should display why an input cannot be made.
     * @return If the value is valid in terms of rows.
     */
    private boolean checkRow(int row, int col, int value, boolean explanation) {
        for (int i = 0; i < grid[0].length; i++) {
            if (i != col && grid[row][i].getValue() == value) {
                if (explanation) {
                    System.out.printf("There is a %d in the same row! Located at (%d, %d).\n", value, i+1, row+1);
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether the value could be placed in the column.
     * 
     * @param row - Row of the grid.
     * @param col - Column of the grid.
     * @param value - Value to set.
     * @param explanation - Whether the program should display why an input cannot be made.
     * @return If the value is valid in terms of columns.
     */
    private boolean checkCol(int row, int col, int value, boolean explanation) {
        for (int j = 0; j < grid.length; j++) {
            if (j != row && grid[j][col].getValue() == value) {
                if (explanation) {
                    System.out.printf("There is a %d in the same column! Located at (%d, %d).\n", value, col+1, j+1);
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether the value could be placed in the subgrid.
     * @param row - Row of the grid.
     * @param col - Column of the grid.
     * @param value - Value to set.
     * @param explanation - Whether the program should display why an input cannot be made.
     * @return If the value is a valid move in terms of subgrids.
     */
    private boolean checkSubGrid(int row, int col, int value, boolean explanation) {
        int rowOffset = row % SUBGRID_DIM;
        int colOffset = col % SUBGRID_DIM;
        int startRow = row - rowOffset;
        int startCol = col - colOffset;
        for (int j = startRow; j < startRow + SUBGRID_DIM; j++) {
            for (int i = startCol; i < startCol + SUBGRID_DIM; i++) {
                if ((grid[j][i].getValue() == value) && ((j != row) || (i != col))) {
                    if (explanation) {
                        System.out.printf("There is a %d in the same subgrid! Located at (%d, %d).\n", value, i+1, j+1);
                    }
                    return false;
                } 
            }
        }
        return true;
    }

    /**
     * Checks whether the value to set is a valid move.
     * 
     * @param row - Row of the grid.
     * @param col - Column of the grid.
     * @param value - Value to set.
     * @param explanation - Whether the program should display why an input cannot be made.
     * @return If the value is a valid move.
     */
    public boolean isValid(int row, int col, int value, boolean explanation) {
        if (isInitial(row, col)) {
            if (explanation) {
                System.out.println("Cannot change the value of an initial field.");
            }
            return false;
        }
        boolean rowCheck = checkRow(row, col, value, explanation);
        boolean colCheck = checkCol(row, col, value, explanation);
        boolean subGridCheck = checkSubGrid(row, col, value, explanation);
        return rowCheck && colCheck && subGridCheck;
    }

    /**
     * Returns a string representation of the grid.
     * @return A string representation of the grid.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                Field element = this.grid[row][col];
                sb.append(element);
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
                sb.append("-------------------------------\n");
            }
        }
        return sb.toString();
    }

    public int countRemainingFields() {
        int numRemainingFields = 0;
        for (int row = 0; row < GameGrid.GRID_DIM; row++) {
            for (int col = 0; col < GameGrid.GRID_DIM; col++) {
                if (this.getField(row, col) == GameGrid.EMPTY_VAL) {
                    numRemainingFields++;
                }
            }
        }
        return numRemainingFields;
    }
}
