package sudoku;

import java.util.Objects;
import java.util.ArrayList;

/**
* This class implements the functionality for solving a sudoku game,
* finding all possible solutions.
*/
public class Solver {
    
    /**
     * Solves a sudoku game using a backtracking algorithm.
     * NOTE: This function is adapted from one of the University of Edinburgh
     * tutorials.
     * 
     * @param gameGrid - The sudoku game to solve.
     * @return Whether the game was solved or not.
     */    
    public static boolean solve(GameGrid gameGrid) {
        if (gameGrid.countRemainingFields() == 0) {
            return true;
        }
        
        Objects.requireNonNull(gameGrid);

        // Begin search at top left of grid (0, 0).
        int col = 0;
        int row = 0;

        // We move backwards if true, forwards otherwise.
        boolean goBack = false;

        // Keep iterating until we move backwards past the start (no solution found)
        // Condition for finding solution checked within.
        while(!(col == GameGrid.GRID_DIM - 1 && row == -1)) {
            
            // Attempt inserting values in should the field not be an initial one.
            if(!gameGrid.isInitial(col, row)) {
                goBack = false; // assume we will find a valid value and move forward.

                // Attempt the increase: if we do not find a valid value, then clear field
                // and move backwards to a previously checked field.
                if(!increaseValue(gameGrid, col, row)) {
                    gameGrid.clearField(col, row);
                    goBack = true;
                }
            } 
            
            // Logic for moving through grid.
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

            // If we move past the final cell, the solution has been found.
            if (col == 0 && row == GameGrid.GRID_DIM) {
                return true;
            }
       }

       // All possible combinations have been tried and no solution found.
       return false;
     }

    /**
     * Finds every solution to a sudoku game using a backtracking
     * algorithm.
     * 
     * @param gameGrid - The sudoku game to solve.
     * @return All the solutions to the sudoku game, stored within an ArrayList.
     */    
    public static ArrayList<GameGrid> findAllSolutions(GameGrid gameGrid) {
        Objects.requireNonNull(gameGrid);
        ArrayList<GameGrid> solutions = new ArrayList<>();

        // Begin search at top left of grid (0, 0).
        int col = 0;
        int row = 0;

        // We move backwards if true, forwards otherwise.
        boolean goBack = false;

        // Keep iterating until we move backwards past the start (no solution found)
        // Condition for finding solution checked within.
        while(!(col == GameGrid.GRID_DIM - 1 && row == -1)) {
            
            // Attempt inserting values in should the field not be an initial one.
            if(!gameGrid.isInitial(col, row)) {
                goBack = false; // assume we will find a valid value and move forward.

                // Attempt the increase: if we do not find a valid value, then clear field
                // and move backwards to a previously checked field.
                if(!increaseValue(gameGrid, col, row)) {
                    gameGrid.clearField(col, row);
                    goBack = true;
                }
            } 
            
            // Logic for moving through grid.
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

            // If we move past the final cell, the solution has been found.
            if (col == 0 && row == GameGrid.GRID_DIM) {
                GameGrid copy = GameGrid.copyGameGrid(gameGrid);
                solutions.add(copy);
                col = GameGrid.GRID_DIM - 1;
                row = GameGrid.GRID_DIM - 1;
                goBack = true;
            }
        }

       // All possible combinations have been tried and no solution found.
       return solutions;
     }

    /**
     * Tries to increase the value of a specific field.
     * 
     * @param gameGrid - The sudoku game to solve.
     * @param row - The row of the game.
     * @param col - The column of the game.
     * @return Whether the field could have its value validly increased.
     */    
     private static boolean increaseValue(GameGrid gameGrid, int row, int col) {
        int value = gameGrid.getField(row, col);
        for (int newValue = value + 1; newValue <= GameGrid.MAX_VAL; newValue++) {
            if (gameGrid.setField(row, col, newValue, false)) {
                return true;
            }
        }
        return false;
    }
}