// IDEA: Use boolean variable "solver" on the check field functions to remove the need for solver copies.

import java.lang.StringBuilder;
import java.util.Objects;

public abstract class GameGrid {
    
    // Constants for coordinate boundaries and Sudoku numbers
    public static final int GRID_DIM = 9;
    public static final int SUBGRID_DIM = GRID_DIM / 3;
    public static final int MAX_VAL = 9;
    public static final int MIN_VAL = 1;
    public static final int EMPTY_VAL = 0;

    protected final Field[][] grid;

    public GameGrid(int[][] grid) {
        Objects.requireNonNull(grid, "grid must not be null");
        this.grid = initialiseGrid(grid);
    }

    public GameGrid(String path) {
        int[][] grid = IOUtils.loadFromFile(path);
        Objects.requireNonNull(grid, "grid must not be null");
        this.grid = initialiseGrid(grid);
    }

    // Creates a deep copy of the current state of the game
    public GameGrid(GameGrid gameGrid) {
        this.grid = new Field[GRID_DIM][GRID_DIM];
        for (int row = 0; row < GRID_DIM; row++) {
            for (int col = 0; col < GRID_DIM; col++) {
                this.grid[row][col] = new Field(gameGrid.getField(row, col), gameGrid.isInitial(row, col));
            }
        }
    }

    private Field[][] initialiseGrid(int[][] grid) {
        Field[][] initialisedGrid = new Field[GRID_DIM][GRID_DIM];
        for (int row = 0; row < GRID_DIM; row++) {
            for (int col = 0; col < GRID_DIM; col++) {
                int value = grid[row][col];
                if (value != 0) {
                    initialisedGrid[row][col] = new Field(value, true);
                } else {
                    initialisedGrid[row][col] = new Field();
                }
            }
        }
        return initialisedGrid;
    }

    public static GameGrid copyGameGrid(GameGrid game) {
        if (game instanceof RGameGrid) {
            return new RGameGrid(game);
        } else {
            return new XGameGrid(game);
        }
    }

    public int getField(int row, int col) {
        return grid[row][col].getValue();
    }

    public boolean setField(int row, int col, int value) {
        if (!isInitial(row, col) && isValid(row, col, value)) {
            grid[row][col].setValue(value);
            return true;
        }
        return false;
    }

    public boolean setFieldSolver(int row, int col, int value) {
        if (!isInitial(row, col) && isValidSolver(row, col, value)) {
            grid[row][col].setValue(value);
            return true;
        }
        return false;
    }

    public void clearField(int row, int col) {
        if (!isInitial(row, col)) {
            grid[row][col].setValue(0);
        }
    }

    public boolean isInitial(int row, int col) {
        return grid[row][col].isInitial();
    }

    private boolean checkRow(int row, int col, int value) {
        for (int i = 0; i < grid[0].length; i++) {
            if (i != col && grid[row][i].getValue() == value) {
                System.out.printf("There is a %d in the same row! Located at (%d, %d).\n", value, i+1, row+1);
                return true;
            }
        }
        return false;
    }

    private boolean checkRowSolver(int row, int col, int value) {
        for (int i = 0; i < grid[0].length; i++) {
            if (i != col && grid[row][i].getValue() == value) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkCol(int row, int col, int value) {
        for (int j = 0; j < grid.length; j++) {
            if (j != row && grid[j][col].getValue() == value) {
                System.out.printf("There is a %d in the same column! Located at (%d, %d).\n", value, col+1, j+1);
                return true;
            }
        }
        return false;
    }

    private boolean checkColSolver(int row, int col, int value) {
        for (int j = 0; j < grid.length; j++) {
            if (j != row && grid[j][col].getValue() == value) {
                return true;
            }
        }
        return false;
    }

    private boolean checkSubGrid(int row, int col, int value) {
        int rowOffset = row % 3;
        int colOffset = col % 3;
        int startRow = row - rowOffset;
        int startCol = col - colOffset;
        for (int j = startRow; j < startRow + 3; j++) {
            for (int i = startCol; i < startCol + 3; i++) {
                if ((grid[j][i].getValue() == value) && ((j != row) || (i != col))) {
                    System.out.printf("There is a %d in the same subgrid! Located at (%d, %d).\n", value, i+1, j+1);
                    return true;
                } 
            }
        }
        return false;
    }

    private boolean checkSubGridSolver(int row, int col, int value) {
        int rowOffset = row % 3;
        int colOffset = col % 3;
        int startRow = row - rowOffset;
        int startCol = col - colOffset;
        for (int j = startRow; j < startRow + 3; j++) {
            for (int i = startCol; i < startCol + 3; i++) {
                if ((grid[j][i].getValue() == value) && ((j != row) || (i != col))) {
                    return true;
                } 
            }
        }
        return false;
    }

    protected boolean isValid(int row, int col, int value) {
        boolean rowCheck = checkRow(row, col, value);
        boolean colCheck = checkCol(row, col, value);
        boolean subGridCheck = checkSubGrid(row, col, value);
        return !(rowCheck || colCheck || subGridCheck);
    }

    protected boolean isValidSolver(int row, int col, int value) {
        boolean rowCheck = checkRowSolver(row, col, value);
        boolean colCheck = checkColSolver(row, col, value);
        boolean subGridCheck = checkSubGridSolver(row, col, value);
        return !(rowCheck || colCheck || subGridCheck);
    }

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
                if ((col + 1) % 3 == 0 && (col != grid[0].length - 1)) {
                    sb.append("| ");
                }
            }
            sb.append("\n");
            if ((row + 1) % 3 == 0 && row != grid.length - 1) {
                sb.append("-------------------------------\n");
            }
        }
        return sb.toString();
    }
}
