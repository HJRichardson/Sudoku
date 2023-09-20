package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import sudoku.GameGrid;
import sudoku.Solver;

/**
* This class implements the action of solving the current sudoku game.
*/
public class SolveGameAction implements ActionListener {

    // Instance variables.
    private final SudokuFrame frame;
    private final SudokuGame sudokuGame;

    /**
     * Creates a SolveGameAction instance based on the current frame and sudoku game.
     * @param frame - The current frame.
     * @param sudokuGame - The current sudoku game.
     */
    public SolveGameAction(SudokuFrame frame, SudokuGame sudokuGame) {
        this.frame = frame;
        this.sudokuGame = sudokuGame;
    }

    /**
     * Performs the action of solving a sudoku game.
     * @param action - The action the user has selected.
     */
    @Override
    public void actionPerformed(ActionEvent action) {
        Objects.requireNonNull(action);

        // If the solve game action has been selected.
        if (action.getSource() instanceof JMenuItem) {

            // Attempt the solving of the game.
            GameGrid gameGrid = sudokuGame.getGrid();
            GameGrid copy = GameGrid.copyGameGrid(gameGrid);
            boolean solved = Solver.solve(copy);

            if (solved) { // Update the grid to show the solution.
                sudokuGame.setGrid(copy);
                frame.updateButtons();
                JOptionPane.showMessageDialog(null, "The sudoku game has been solved.");
            } else { // Display unsolveable game.
                JOptionPane.showMessageDialog(null, "The sudoku game is unsolveable.");
            }
        }
    }
    
}
