import java.util.Scanner;
import java.util.Objects;
import java.io.File;
import java.util.ArrayList;

public class Sudoku07 {

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
     * Reads the integer given by the user.
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
        gameGrid.setField(row, col, value, true);
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
        String sudokuName = file.getName();
        GameGrid gameGrid;
        if (sudokuName.startsWith("x")) {
            gameGrid = new XGameGrid(path);
        } else {
            gameGrid = new RGameGrid(path);
        }
        return gameGrid;
    }

    /**
     * Runs the sudoku program, giving options to solve it manually through user input
     * or solve it using an algorithm.
     * 
     * @param args - The file path to the sudoku file.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No path provided.");
            return;
        }
        String path = args[0];
        GameGrid gameGrid = gameGridSetUp(path);
        while (true) {
            printMenu();
            int input = requestInt("a value between 1 and 7", 1, 7);
            if (input == 7) {
                return;
            }
            processCommand(gameGrid, input);
        }
    }

    /**
     * Helper function to process the user's inputs on the current sudoku.
     * 
     * @param gameGrid - The GameGrid instance.
     * @param input - The user's input.
     */
    private static void processCommand(GameGrid gameGrid, int input) {
        Objects.requireNonNull(gameGrid);
        
        switch (input) {
            case 1:
                System.out.println(gameGrid);
                break;
            case 2:
                clearField(gameGrid);
                System.out.println(gameGrid);
                break;
            case 3:
                setField(gameGrid);
                System.out.println(gameGrid);
                break;
            case 4:
                GameGrid copy = GameGrid.copyGameGrid(gameGrid);
                boolean solved = Solver.solve(copy);
                if (solved) {
                    System.out.println("A solution has been found!\n");
                    System.out.println(copy);
                } else {
                    System.out.println("No solution found.");
                }
                break;
            case 5:
                GameGrid gridCopy = GameGrid.copyGameGrid(gameGrid);
                ArrayList<GameGrid> solutions = Solver.findAllSolutions(gridCopy);
                if (solutions.isEmpty()) {
                    System.out.println("No solution found.");
                } else {
                    int counter = 1;
                    System.out.println("Solutions found!\n");
                    for (GameGrid solution : solutions) {
                        System.out.println("Solution " + counter + ":");
                        System.out.println(solution + "\n");
                        counter++;
                    }
                }
                break;
            case 6:
                GameGrid rankerCopy = GameGrid.copyGameGrid(gameGrid);
                float rank = Ranker.rankSudoku(rankerCopy);
                System.out.println("Sudoku rank: " + rank);
                break;
        }
    }
}
