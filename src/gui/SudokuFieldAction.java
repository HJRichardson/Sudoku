package gui;

import sudoku.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class SudokuFieldAction implements ActionListener{
    
    // Instance variables.
    private final SudokuGame sudokuGame;    
    private final int row;
    private final int col;

    /**
     * Constructor to create a SudokuFieldAction for a specific field
     * in the grid.
     * @param gameGrid - The grid.
     * @param row - The row of the grid.
     * @param column - The column of the grid.
     */
    public SudokuFieldAction(SudokuGame sudokuGame, int row, int col) {
        this.sudokuGame = sudokuGame;
        this.row = row;
        this.col = col;
    }

    /**
     * Implements the action of setting a field for a specific button in the grid.
     * @param action - The action which will be performed on the field.
     */
    @Override
    public void actionPerformed(ActionEvent action) {
        Objects.requireNonNull(action);

        if (action.getSource() instanceof JButton) {
            JButton actionButton = (JButton) action.getSource();
            String input = JOptionPane.showInputDialog("Please provide a value between 0 and 9. Inputting 0 will clear the field.");
            if (input == null || input.equals("")) {
                return;
            }
            int value = Integer.valueOf(input);
            GameGrid gameGrid = sudokuGame.getGrid();
            if (value == GameGrid.EMPTY_VAL) {
                gameGrid.clearField(row, col);
                actionButton.setText("");
                return;
            } else if (value < GameGrid.MIN_VAL || value > GameGrid.MAX_VAL) {
                JOptionPane.showMessageDialog(null, "Invalid input: " + input);
                return;
            }

            if (gameGrid.isValid(row, col, value, false)) {
                gameGrid.setField(row, col, value, false);
                actionButton.setText(value + "");
                if (gameGrid.countRemainingFields() == 0) {
                    JOptionPane.showMessageDialog(null, "You have successfully completed the sudoku. Well done!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Not a valid move.");
            }
        }
    }
}
