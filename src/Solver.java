import java.util.Objects;
import java.util.ArrayList;

public class Solver {
    
    public static boolean solve(GameGrid game) {
        Objects.requireNonNull(game);

        // Begin search at top left of grid (0, 0)
        int col = 0;
        int row = 0;

        // We move backwards if true, forwards otherwise
        boolean goBack = false;

        // Keep iterating until we move backwards past the start (no solution found)
        // Condition for finding solution checked within
        while(!(col == GameGrid.GRID_DIM - 1 && row == -1)) {
            
            // Attempt inserting values in should the field not be an initial one
            if(!game.isInitial(col, row)) {
                goBack = false; // assume we will find a valid value and move forward

                // Attempt the increase: if we do not find a valid value, then clear field
                // and move backwards to a previously checked field
                if(!tryIncrease(game, col, row)) {
                    game.clearField(col, row);
                    goBack = true;
                }
            } 
            
            // Logic for moving through grid
            if(goBack) {
                col--;
                if(col < 0) {
                    col = GameGrid.GRID_DIM - 1;
                    row--;
                }
            } else {
               col++;
               if(col >= GameGrid.GRID_DIM) {
                   col = 0;
                   row++;
               }
            }

            // If we move past the final cell, the solution has been found
            if (col == 0 && row == GameGrid.GRID_DIM) {
                return true;
            }
       }

       // All possible combinations have been tried and no solution found
       return false;
     }

    public static ArrayList<GameGrid> findAllSolutions(GameGrid game) {
        Objects.requireNonNull(game);
        ArrayList<GameGrid> solutions = new ArrayList<>();

        // Begin search at top left of grid (0, 0)
        int col = 0;
        int row = 0;

        // We move backwards if true, forwards otherwise
        boolean goBack = false;

        // Keep iterating until we move backwards past the start (no solution found)
        // Condition for finding solution checked within
        while(!(col == GameGrid.GRID_DIM - 1 && row == -1)) {
            
            // Attempt inserting values in should the field not be an initial one
            if(!game.isInitial(col, row)) {
                goBack = false; // assume we will find a valid value and move forward

                // Attempt the increase: if we do not find a valid value, then clear field
                // and move backwards to a previously checked field
                if(!tryIncrease(game, col, row)) {
                    game.clearField(col, row);
                    goBack = true;
                }
            } 
            
            // Logic for moving through grid
            if(goBack) {
                col--;
                if(col < 0) {
                    col = GameGrid.GRID_DIM - 1;
                    row--;
                }
            } else {
               col++;
               if(col >= GameGrid.GRID_DIM) {
                   col = 0;
                   row++;
               }
            }

            // If we move past the final cell, the solution has been found
            if (col == 0 && row == GameGrid.GRID_DIM) {
                GameGrid copy = GameGrid.copyGameGrid(game);
                solutions.add(copy);
                col = GameGrid.GRID_DIM - 1;
                row = GameGrid.GRID_DIM - 1;
                goBack = true;
            }
        }

       // All possible combinations have been tried and no solution found
       return solutions;
     }

     private static boolean tryIncrease(GameGrid game, int row, int col) {
        int value = game.getField(row, col);
        for (int newValue = value + 1; newValue <= GameGrid.MAX_VAL; newValue++) {
            if (game.setFieldSolver(row, col, newValue)) {
                return true;
            }
        }
        return false;
    }
}