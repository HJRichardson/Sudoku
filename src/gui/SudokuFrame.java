package gui;

import sudoku.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.GridLayout;

public class SudokuFrame extends JFrame {
    
    // Instance variables.
    private final SudokuGame sudokuGame;
    private final JButton[][] buttonGrid;

    /**
     * Constructor to create a SudokuFrame instance from
     * a valid sudoku path.
     * @param path - The sudoku file path.
     */
    public SudokuFrame(String path) {
        super.setSize(750, 750);
        super.setTitle(path);
        super.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridLayout gridLayout = new GridLayout(GameGrid.GRID_DIM, GameGrid.GRID_DIM);
        super.setLayout(gridLayout);

        GameGrid gameGrid = UserInterface.gameGridSetUp(path);
        sudokuGame = new SudokuGame(gameGrid);

        buttonGrid = new JButton[GameGrid.GRID_DIM][GameGrid.GRID_DIM];
        this.createButtonGrid();
        this.createMenuBar();
    }

    /**
     * Sets up the sudoku frame layout, with a grid of NxN buttons with the initial sudoku
     * values, where N = GameGrid.GRID_DIM is the number of rows/columns.
     */
    private void createButtonGrid() {
        GameGrid gameGrid = sudokuGame.getGrid();
        for (int row = 0; row < GameGrid.GRID_DIM; row++) {
            for (int col = 0; col < GameGrid.GRID_DIM; col++) {
                JButton button = new JButton();
                button.addActionListener(new SudokuFieldAction(sudokuGame, row, col));
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                if (sudokuGame.getGrid().isInitial(row, col)) {
                    button.setText(gameGrid.getField(row, col) + "");
                    button.setEnabled(false);
                }
                buttonGrid[row][col] = button; 
                this.add(button);
            }
        }
    }

    protected void updateButtons() {
        GameGrid gameGrid = sudokuGame.getGrid();
        for (int row = 0; row < GameGrid.GRID_DIM; row++) {
            for (int col = 0; col < GameGrid.GRID_DIM; col++) {
                JButton button = buttonGrid[row][col];
                if (gameGrid.isInitial(row, col)) {
                    button.setText(gameGrid.getField(row, col) + "");
                    button.setEnabled(false);
                } else {
                    button.setText("");
                    button.setEnabled(true);
                }
            }
        }
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(new LoadGameAction(this, sudokuGame));
        menu.add(loadGame);
        menu.add(new JMenuItem("Solve Game"));
        menu.add(new JMenuItem("Rank Game"));
        menuBar.add(menu);

        this.setJMenuBar(menuBar);
    }
}
