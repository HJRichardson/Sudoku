public class XGameGrid extends GameGrid {
    
    public XGameGrid(int[][] grid) {
        super(grid);
    }

    public XGameGrid(String path) {
        super(path);
    }

    public XGameGrid(GameGrid gameGrid) {
        super(gameGrid);
    }

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

    private boolean checkDiagonalSolver(int row, int col, int value) {
        if (row != col && row != GameGrid.GRID_DIM - col - 1) {
            return false;
        }

        if (row == col) {
            for (int i = 0; i < GameGrid.GRID_DIM; i++) {
                if (row != i && grid[i][i].getValue() == value) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < GameGrid.GRID_DIM; i++) {
                if (row != i && grid[i][GRID_DIM-i-1].getValue() == value) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected boolean isValid(int row, int col, int value, boolean explanation) {
        boolean checkOriginalRules = super.isValid(row, col, value, explanation);
        return checkOriginalRules && !checkDiagonal(row, col, value, explanation);
    }

    @Override
    protected boolean isValidSolver(int row, int col, int value) {
        boolean checkOriginalRules = super.isValidSolver(row, col, value);
        return checkOriginalRules && !checkDiagonalSolver(row, col, value);
    }

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
