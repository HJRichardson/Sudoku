package gui;

import sudoku.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JMenuItem;

public class LoadGameAction implements ActionListener {

    private final SudokuFrame frame;
    private final SudokuGame sudokuGame;

    public LoadGameAction(SudokuFrame frame, SudokuGame sudokuGame) {
        this.frame = frame;
        this.sudokuGame = sudokuGame;
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        Objects.requireNonNull(action);

        if (action.getSource() instanceof JMenuItem) {
            String path = UserInterface.askForPath();
            GameGrid newGameGrid = UserInterface.gameGridSetUp(path);
            sudokuGame.setGrid(newGameGrid);
            frame.updateButtons();
        }
    }
    
}
