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

/**
* This class creates the skeleton structure for
* a regular sudoku frame.
*/
public abstract class SudokuFrame extends JFrame {
    
    // Instance variables.
    protected final SudokuGame sudokuGame;
    protected final JButton[][] buttonGrid;

    /**
     * Constructor to create a SudokuFrame instance from
     * a valid sudoku path.
     * @param path - The sudoku file path.
     */
    public SudokuFrame(String path) {
        // Sets the location and size properties of the frame window.
        super.setSize(750, 750);
        super.setTitle(path);
        super.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Creates an NxN grid for the sudoku frame.
        GridLayout gridLayout = new GridLayout(GameGrid.GRID_DIM, GameGrid.GRID_DIM);
        super.setLayout(gridLayout);

        // Creates a sudoku game based on a file path.
        GameGrid gameGrid = GameGrid.gameGridSetUp(path);
        sudokuGame = new SudokuGame(gameGrid);

        // Creates the button grid and adds a menu bar.
        buttonGrid = new JButton[GameGrid.GRID_DIM][GameGrid.GRID_DIM];
        this.createButtonGrid();
        this.createMenuBar();
    }

    /**
     * Sets up the sudoku frame layout, with a grid of NxN buttons with the initial sudoku
     * values, where N = GameGrid.GRID_DIM is the number of rows/columns.
     */
    protected void createButtonGrid() {
        GameGrid gameGrid = sudokuGame.getGrid();
        for (int row = 0; row < GameGrid.GRID_DIM; row++) {
            for (int col = 0; col < GameGrid.GRID_DIM; col++) {

                // Adds a button which listens for the user trying to add a value to a grid entry.
                JButton button = new JButton();
                button.addActionListener(new SudokuFieldAction(sudokuGame, row, col));
                button.setFont(new Font("Arial", Font.PLAIN, 40));

                // Disables the button if the field is an initial one, displaying this value.
                if (sudokuGame.getGrid().isInitial(row, col)) {
                    button.setText(gameGrid.getField(row, col) + "");
                    button.setEnabled(false);
                }
                buttonGrid[row][col] = button; 
                this.add(button);
            }
        }
    }

    /**
     * Updates the sudoku frame grid, determining whether buttons should be enabled/disabled
     * based on the grid's values.
     */
    protected void updateButtons() {
        GameGrid gameGrid = sudokuGame.getGrid();
        for (int row = 0; row < GameGrid.GRID_DIM; row++) {
            for (int col = 0; col < GameGrid.GRID_DIM; col++) {
                // Accesses button.
                JButton button = buttonGrid[row][col];

                // Disables button if field is non-empty.
                if (gameGrid.getField(row, col) != GameGrid.EMPTY_VAL) {
                    button.setText(gameGrid.getField(row, col) + "");
                    button.setEnabled(false);
                } else { // Non-empty fields can be changed.
                    button.setText("");
                    button.setEnabled(true);
                }
            }
        }
    }

    /**
     * Creates the menu bar for the sudoku frame.
     */
    private void createMenuBar() {
        // Creating the skeleton menu.
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        // Creating the load game option.
        JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(new LoadGameAction(this));
        menu.add(loadGame);

        // Creating the solve game option.
        JMenuItem solveGame = new JMenuItem("Solve Game");
        solveGame.addActionListener(new SolveGameAction(this, sudokuGame));
        menu.add(solveGame);

        // Creating the rank game option.
        JMenuItem rankGame = new JMenuItem("Rank Game");
        rankGame.addActionListener(new RankGameAction(sudokuGame));
        menu.add(rankGame);

        // Adds the menu to the bar.
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }
}
