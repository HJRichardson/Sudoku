package gui;

import java.util.Objects;

import sudoku.*;

// Wrapper class for GameGrid.
public class SudokuGame {

    // Instance variables.
    private GameGrid gameGrid;
    private float rank; // Rank of the initial game.

    /**
     * Creates a SudokuGame instance based on the GameGrid
     * instance.
     * 
     * @param gameGrid - The GameGrid instance.
     * @return The initialised SudokuFrame instance.
     */
    public SudokuGame(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
        this.rank = Ranker.rankSudoku(gameGrid);
    }

    /**
     * Getter for the GameGrid instance.
     * @return Returns the GameGrid instance.
     */    
    public GameGrid getGrid() {
        return gameGrid;
    }

    /**
     * Setter of the GameGrid instance.
     * @param gameGrid - The GameGrid instance to set.
     */    
    public void setGrid(GameGrid gameGrid) {
        Objects.requireNonNull(gameGrid);
        this.gameGrid = gameGrid;
    }

    /**
     * Getter for the rank of the initial sudoku game.
     * @return Returns the rank of the initial sudoku game.
     */    
    public float getRank() {
        return rank;
    }
    
}
