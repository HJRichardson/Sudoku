package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import sudoku.GameGrid;
import sudoku.Solver;

public class SolveGameAction implements ActionListener {

    private final SudokuFrame frame;
    private final SudokuGame sudokuGame;

    public SolveGameAction(SudokuFrame frame, SudokuGame sudokuGame) {
        this.frame = frame;
        this.sudokuGame = sudokuGame;
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        Objects.requireNonNull(action);

        if (action.getSource() instanceof JMenuItem) {
            GameGrid gameGrid = sudokuGame.getGrid();
            GameGrid copy = GameGrid.copyGameGrid(gameGrid);
            boolean solved = Solver.solve(copy);
            if (solved) {
                sudokuGame.setGrid(copy);
                frame.updateButtons();
                JOptionPane.showMessageDialog(null, "The sudoku game has been solved.");
            } else {
                JOptionPane.showMessageDialog(null, "The sudoku game is unsolveable.");
            }
        }
    }
    
}
