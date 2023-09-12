package gui;

import sudoku.*;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.Objects;

public class UserInterface {
    
    public static void main(String[] args) {
        String path = askForPath();
        SudokuFrame frame = new SudokuFrame(path);
        frame.setVisible(true);
    }

    private static String askForPath() {
        String path = JOptionPane.showInputDialog("Please provide a file path to the Sudoku game file.");
        if (path == null) {
            return "D:\\Users\\hj1ri\\Documents\\Java\\Sudoku\\games\\sudoku0.sd";
        }

        File file = new File(path);
        if (!file.exists()) {
            return "D:\\Users\\hj1ri\\Documents\\Java\\Sudoku\\games\\sudoku0.sd";
        }
        return path;
    }

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
