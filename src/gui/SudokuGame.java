package gui;

import sudoku.*;

public class SudokuGame {

    private GameGrid gameGrid;
    private float rank;

    public SudokuGame(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
        this.rank = Ranker.rankSudoku(gameGrid);
    }

    public GameGrid getGrid() {
        return gameGrid;
    }

    public void setGrid(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    public float getRank() {
        return rank;
    }
    
}
