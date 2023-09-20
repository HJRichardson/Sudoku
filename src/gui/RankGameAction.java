package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
* This class implements the action of ranking the initial sudoku game.
*/
public class RankGameAction implements ActionListener {
    
    // Instance variable of the sudoku game.
    private final SudokuGame sudokuGame;

    /**
     * Creates the rank action instance.
     * @param sudokuGame - The SudokuGame instance to rank.
    */
    public RankGameAction(SudokuGame sudokuGame) {
        this.sudokuGame = sudokuGame;
    }

    /**
     * Performs the action of ranking the initial sudoku game.
     * @param action - The action the user has selected.
     */
    @Override
    public void actionPerformed(ActionEvent action) {
        Objects.requireNonNull(action);

        // Displays the rank of the sudoku game if the rank game option is selected.
        if (action.getSource() instanceof JMenuItem) {
            float rank = sudokuGame.getRank();
            JOptionPane.showMessageDialog(null, "The rank of this sudoku is: " + rank);
        }
    }
}
