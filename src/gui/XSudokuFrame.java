package gui;

import java.awt.Font;

import javax.swing.JButton;

import sudoku.*;

/**
* This class implements the frame for the X-Sudoku variation.
*/
public class XSudokuFrame extends SudokuFrame {
    
    /**
     * Creates an X-Sudoku game frame.
     * @param path - The file path to the sudoku game.
    */
    public XSudokuFrame(String path) {
        super(path);
    }

    /**
     * Overrides the regular button grid- creates the same button grid with square brackets
     * for diagonal entries.
     */
    @Override
    protected void createButtonGrid() {
        GameGrid gameGrid = sudokuGame.getGrid();
        for (int row = 0; row < GameGrid.GRID_DIM; row++) {
            for (int col = 0; col < GameGrid.GRID_DIM; col++) {

                // Adds a button which listens for the user trying to add a value to a grid entry.
                JButton button = new JButton();
                button.addActionListener(new SudokuFieldAction(sudokuGame, row, col));
                button.setFont(new Font("Arial", Font.PLAIN, 40));

                // Disables the button if the field is an initial one, displaying this value.
                if (sudokuGame.getGrid().isInitial(row, col)) {
                    boolean onDiagonal = XGameGrid.onDiagonal(row, col);
                    if (onDiagonal) { // Adds square brackets to diagonal entries.
                        button.setText("[" + gameGrid.getField(row, col) + "]");
                    } else {
                        button.setText(gameGrid.getField(row, col) + "");
                    }
                    button.setEnabled(false);
                }
                buttonGrid[row][col] = button; 
                this.add(button);
            }
        }
    }

    /**
     * Overrides the regular button grid- updates the same button grid with square brackets
     * for diagonal entries.
     */
    @Override
    protected void updateButtons() {
        GameGrid gameGrid = sudokuGame.getGrid();
        for (int row = 0; row < GameGrid.GRID_DIM; row++) {
            for (int col = 0; col < GameGrid.GRID_DIM; col++) {

                // Accesses button.
                JButton button = buttonGrid[row][col];

                // Disables button if field is non-empty.
                if (gameGrid.getField(row, col) != GameGrid.EMPTY_VAL) {
                    boolean onDiagonal = XGameGrid.onDiagonal(row, col);
                    if (onDiagonal) { // Adds square brackets for diagonal entries.
                        button.setText("[" + gameGrid.getField(row, col) + "]");
                    } else {
                        button.setText(gameGrid.getField(row, col) + "");
                    }
                    button.setEnabled(false);
                } else { // Non-empty fields can be changed.
                    button.setText("");
                    button.setEnabled(true);
                }
            }
        }
    }
}
