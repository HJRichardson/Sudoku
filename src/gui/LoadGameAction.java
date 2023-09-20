package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Objects;

import javax.swing.JMenuItem;

/**
* This class implements the action of loading the sudoku game.
*/
public class LoadGameAction implements ActionListener {

    // Instance variables.
    private SudokuFrame frame;

    /**
    * Constructor for the LoadGameAction instance.
    * @param frame - The SudokuFrame instance.
    */
    public LoadGameAction(SudokuFrame frame) {
        this.frame = frame;
    }

    /**
     * Performs the action of loading the sudoku game.
     * @param action - The action the user has selected.
     */
    @Override
    public void actionPerformed(ActionEvent action) {
        Objects.requireNonNull(action);

        // If the load game action has been selected.
        if (action.getSource() instanceof JMenuItem) {
            String path = UserInterface.askForPath();

            // Removing the old frame.
            frame.setVisible(false);
            frame.dispose();

            // Creating the new frame.
            frame = UserInterface.setUpSudokuFrame(path);
            frame.setVisible(true);
        }
    }
    
}
