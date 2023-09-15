package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import sudoku.*;

public class RankGameAction implements ActionListener {
    
    private final SudokuGame sudokuGame;

    public RankGameAction(SudokuGame sudokuGame) {
        this.sudokuGame = sudokuGame;
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        Objects.requireNonNull(action);

        if (action.getSource() instanceof JMenuItem) {
            float rank = sudokuGame.getRank();
            JOptionPane.showMessageDialog(null, "The rank of this sudoku is: " + rank);
        }
    }
}
