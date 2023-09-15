package gui;

import sudoku.*;

public class SudokuGame {

    private GameGrid gameGrid;

    public SudokuGame(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    public GameGrid getGrid() {
        return gameGrid;
    }

    public void setGrid(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }
    
}
