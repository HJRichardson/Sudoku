package gui;

import sudoku.*;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.Objects;

public class UserInterface {
    
    /**
     * Runs the sudoku program.
     */
    public static void main(String[] args) {
        String path = askForPath();
        if (path == null) {
            System.exit(0);
        }
        SudokuFrame frame = new SudokuFrame(path);
        frame.setVisible(true);
    }

    /**
     * Asks the user for a valid sudoku directory, including
     * the file they wish to play. This loops until they give a valid one,
     * or they decide to exit the prompt.
     *
     * @return A valid sudoku file path.
     */
    private static String askForPath() {
        while (true) {
            String path = JOptionPane.showInputDialog("Please provide a file path to the Sudoku game file.");
            if (path == null) {
                System.exit(0);
            }

            if (path.equals("")) {
                JOptionPane.showMessageDialog(null, "The file path cannot be empty");
                continue;
            }

            File file = new File(path);
            if (file.exists() && file.getName().endsWith(".sd")) {
                return path;
            } else if (file.exists()) {
                JOptionPane.showMessageDialog(null, "The file you have selected is not a valid sudoku game.");
            } else {
                JOptionPane.showMessageDialog(null, "The selected file does not exist.");
            }
        }
    }

    /**
     * Helper function to initialise GameGrid instance using file path.
     * Determines the type of the GameGrid instance based on the file name.
     *
     * @param path - The file path of the sudoku file.
     * @return The initialised GameGrid instance.
     */
    public static GameGrid gameGridSetUp(String path) {
        Objects.requireNonNull(path);

        File file = new File(path);
        String sudokuName = file.getName();
        GameGrid gameGrid;
        if (sudokuName.startsWith("x")) {
            gameGrid = new XGameGrid(path);
        } else {
            gameGrid = new RGameGrid(path);
        }
        return gameGrid;
    }
}
