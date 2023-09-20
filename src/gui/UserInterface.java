package gui;

import javax.swing.JOptionPane;
import java.io.File;
import java.util.Objects;

/**
* This class controls the user interface for the sudoku
* program.
*/
public class UserInterface {
    
    /**
     * Runs the sudoku program.
     */
    public static void main(String[] args) {
        String path = askForPath();
        SudokuFrame frame = setUpSudokuFrame(path);
        frame.setVisible(true);
    }

    /**
     * Helper function to set up a Sudoku frame based on
     * the type of sudoku game.
     * 
     * @param path - The file path of the sudoku game.
     * @return The initialised SudokuFrame instance.
     */
    public static SudokuFrame setUpSudokuFrame(String path) {
        Objects.requireNonNull(path);

        File file = new File(path);
        String sudokuName = file.getName();
        SudokuFrame frame;
        if (sudokuName.startsWith("x")) { // X-Sudoku game.
            frame = new XSudokuFrame(path);
        } else { // Regular Sudoku game.
            frame = new RSudokuFrame(path);
        }
        return frame;
    } 

    /**
     * Asks the user for a valid sudoku directory, including
     * the file they wish to play. This loops until they give a valid one,
     * or they decide to exit the prompt.
     *
     * @return A valid sudoku file path.
     */
    public static String askForPath() {
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
            if (file.exists() && file.getName().endsWith(".sd")) { // Valid file.
                return path;
            } else if (file.exists()) { // Invalid file type.
                JOptionPane.showMessageDialog(null, "The file you have selected is not a valid sudoku game.");
            } else { // File doesn't exist.
                JOptionPane.showMessageDialog(null, "The selected file does not exist.");
            }
        }
    }
}
