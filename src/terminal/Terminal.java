package terminal;

import sudoku.*;
import java.util.Scanner;
import java.util.Objects;
import java.io.File;
import java.util.ArrayList;

/**
* This class implements the terminal user interface for the sudoku game,
* giving options for setting values and solving sudoku games.
*/
public class Terminal {

    /**
     * Runs the sudoku program, giving options to solve it manually through user input
     * or solve it using an algorithm.
     */
    public static void main(String[] args) {
        String path = getDirectory();
        if (path == null) {
            System.exit(0);
        }
        GameGrid gameGrid = gameGridSetUp(path);
        while (true) {
            printMenu();
            int input = requestInt("a value between 1 and 7", 1, 7);
            if (input == 7) {
                System.exit(0);
            }
            processCommand(gameGrid, input, path);
        }
    }

    /**
     * Prints a game menu message to the console, listing choices the
     * user can make.
     */
    public static void printMenu() {
        System.out.print("\nPlease view the list of actions:\n" +
                "1. Print game\n" +
                "2. Clear Field\n" +
                "3. Set Field\n" +
                "4. Solve Game\n" + 
                "5. Find All Solutions\n" +
                "6. Rank Sudoku\n" +
                "7. Exit\n\n" +
                "Select an action [1-7]: ");
    }

     /**
     * Asks the user for a valid sudoku directory, including
     * the file they wish to play. This loops until they give a valid one,
     * or they decide to exit the prompt.
     *
     * @return A valid sudoku file path.
     */
    public static String getDirectory() {
        Scanner scanner = new Scanner(System.in);
        String result;
        while (true) {
            System.out.println("Please provide a file path to the Sudoku game file. To exit, please type 'exit'.");
            if (scanner.hasNextLine()) {
                result = scanner.nextLine();
                if (result.equals("exit")) {
                    return null;
                }
                File file = new File(result);
                if (file.exists() && file.getName().endsWith(".sd")) { // Valid file.
                    return result;
                } else if (file.exists()) { // Invalid file type.
                    System.out.println("The file you have selected is not a valid sudoku game.");
                } else { // File doesn't exist.
                    System.out.println("The selected file does not exist.");
                }
            }
        }
    }

    /**
     * Reads the integer given by the user.
     * NOTE: This function is adapted from one of the University of Edinburgh
     * tutorials.
     * 
     * @return The user's input as integer or -1 if the user's input was invalid.
     */
    public static int parseInput() {
        Scanner scanner = new Scanner(System.in);
        int parsedVal = -1;
        if (scanner.hasNextInt()) {
            parsedVal = Integer.valueOf(scanner.nextInt());
        }
        return parsedVal;
    }

    /**
     * Takes an input from the user until it is between {@code min} and
     * {@code max}.
     * NOTE: This function is adapted from one of the University of Edinburgh
     * tutorials.
     *
     * @param msg - A name for the requested data.
     * @param min - Minimum accepted integer.
     * @param max - Maximum accepted integer.
     * @return The user's input as integer.
     */
    private static int requestInt(String msg, int min, int max) {
        Objects.requireNonNull(msg);
        while(true) {
            System.out.print("Please provide " + msg + ": ");
            int input = parseInput();
            if (input >= min && input <= max)  {
                return input;
            } else {
                System.out.println("Invalid input. Must be between " + min + " and " + max);
                return -1;
            }
        }
    }

    /**
     * Clears a field in the sudoku grid, given the (scaled) inputs from the user.
     * @param gameGrid - The GameGrid instance.
     */
    private static void clearField(GameGrid gameGrid) {
        Objects.requireNonNull(gameGrid);

        int col = requestInt("the column of the value to clear (between " + GameGrid.MIN_VAL + " and " +
                              GameGrid.MAX_VAL + ")", GameGrid.MIN_VAL, GameGrid.MAX_VAL) - 1;
        int row = requestInt("the row of the value to clear (between " + GameGrid.MIN_VAL + " and " +
                              GameGrid.MAX_VAL + ")", GameGrid.MIN_VAL, GameGrid.MAX_VAL) - 1;
        gameGrid.clearField(row, col);
    }

    /**
     * Sets a field in the sudoku grid, given the (scaled) inputs from the user.
     * @param gameGrid - The GameGrid instance.
     */
    private static void setField(GameGrid gameGrid) {
        Objects.requireNonNull(gameGrid);

        int col = requestInt("the column of the value to set (between " + GameGrid.MIN_VAL + " and " +
                              GameGrid.MAX_VAL + ")", GameGrid.MIN_VAL, GameGrid.MAX_VAL) - 1;
        int row = requestInt("the row of the value to set (between " + GameGrid.MIN_VAL + " and " +
                              GameGrid.MAX_VAL + ")", GameGrid.MIN_VAL, GameGrid.MAX_VAL) - 1;
        int value = requestInt("the value to set (between " + GameGrid.MIN_VAL + " and " +
                              GameGrid.MAX_VAL + ")", GameGrid.MIN_VAL, GameGrid.MAX_VAL);
        boolean success = gameGrid.setField(row, col, value, true);
        if (success && gameGrid.countRemainingFields() == 0) {
            System.out.println("Well done! You solved the sudoku!");
        }
    }

    /**
     * Helper function to initialise GameGrid instance using file path.
     * Determines the type of the GameGrid instance based on the file name.
     *
     * @param path - The file path of the sudoku file.
     * @return The initialised GameGrid instance.
     */
    private static GameGrid gameGridSetUp(String path) {
        Objects.requireNonNull(path);

        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Sudoku file does not exist.");
            return null;
        }
        String sudokuName = file.getName();
        GameGrid gameGrid;
        if (sudokuName.startsWith("x")) { // X-Sudoku game.
            gameGrid = new XGameGrid(path);
        } else { // Regular Sudoku game.
            gameGrid = new RGameGrid(path);
        }
        return gameGrid;
    }

    /**
     * Helper function to process the user's inputs on the current sudoku.
     * 
     * @param gameGrid - The GameGrid instance.
     * @param input - The user's input.
     */
    private static void processCommand(GameGrid gameGrid, int input, String path) {
        Objects.requireNonNull(gameGrid);
        
        switch (input) {
            // Prints game.
            case 1:
                System.out.println(gameGrid);
                break;
            // Clears field and prints updated game.
            case 2:
                clearField(gameGrid);
                System.out.println(gameGrid);
                break;
            // Sets field and prints updated game.
            case 3:
                setField(gameGrid);
                System.out.println(gameGrid);
                break;
            // Solves game and prints it.
            case 4:
                GameGrid originalGameGrid = gameGridSetUp(path);
                GameGrid copy = GameGrid.copyGameGrid(originalGameGrid);
                boolean solved = Solver.solve(copy);
                if (solved) {
                    System.out.println("A solution has been found!\n");
                    System.out.println(copy);
                } else {
                    System.out.println("No solution found.");
                }
                break;
            // Finds all solutions and prints them.
            case 5:
                GameGrid gridCopy = GameGrid.copyGameGrid(gameGrid);
                ArrayList<GameGrid> solutions = Solver.findAllSolutions(gridCopy);
                if (solutions.isEmpty()) {
                    System.out.println("No solution found.");
                } else {
                    int numSolutions = solutions.size();
                    if (numSolutions == 1) {
                        System.out.println("1 solution has been found!\n");
                    } else {
                        System.out.println(numSolutions + " solutions have been found!\n");
                    }
                    int counter = 1;
                    for (GameGrid solution : solutions) {
                        System.out.println("Solution " + counter + ":");
                        System.out.println(solution + "\n");
                        counter++;
                    }
                }
                break;
            // Ranks the current game.
            case 6:
                GameGrid rankerCopy = GameGrid.copyGameGrid(gameGrid);
                float rank = Ranker.rankSudoku(rankerCopy);
                System.out.println("Sudoku rank: " + rank);
                break;
        }
    }
}
