package gui;

import sudoku.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.GridLayout;

public class SudokuFrame extends JFrame {
    
    // Instance variables.
    private final GameGrid gameGrid;

    /**
     * Constructor to create a SudokuFrame instance from
     * a valid sudoku path.
     * @param path - The sudoku file path.
     */
    public SudokuFrame(String path) {
        super.setSize(750, 750);
        super.setTitle(path);
        super.setLocationRelativeTo(null);

        GridLayout gridLayout = new GridLayout(GameGrid.GRID_DIM, GameGrid.GRID_DIM);
        super.setLayout(gridLayout);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameGrid = UserInterface.gameGridSetUp(path);
        createButtonGrid();
    }

    /**
     * Sets up the sudoku frame layout, with a grid of NxN buttons with the initial sudoku
     * values, where N = GameGrid.GRID_DIM is the number of rows/columns.
     */
    private void createButtonGrid() {
        for (int row = 0; row < GameGrid.GRID_DIM; row++) {
            for (int col = 0; col < GameGrid.GRID_DIM; col++) {
                JButton button = new JButton();
                button.addActionListener(new SudokuFieldAction(gameGrid, row, col));
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                if (gameGrid.isInitial(row, col)) {
                    button.setText(gameGrid.getField(row, col) + "");
                    button.setEnabled(false);
                } 
                this.add(button);
            }
        }
    }
}
