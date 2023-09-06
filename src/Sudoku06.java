import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.ArrayList;

public class Sudoku06 {

    /**
     * Print a game menu message to the console.
     */
    public static void printMenu() {
        System.out.print("\n" +
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
     * Read a single integer value from the console and return it.
     * This function blocks the program's execution until a user
     * entered a value into the command line and confirmed by pressing
     * the Enter key.
     * @return The user's input as integer or -1 if the user's input was invalid.
     */
    public static int parseInput() {
        Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch (InputMismatchException missE) {
            in.next(); // discard invalid input
            return -1;
        }
    }   

    /**
     * Display a dialog requesting a single integer which is returned
     * upon completion.
     *
     * The dialog is repeated in an endless loop if the given input 
     * is not an integer or not within min and max bounds.
     *
     * @param msg: a name for the requested data.
     * @param min: minium accepted integer.
     * @param max: maximum accepted integer.
     * @return The user's input as integer.
     */
    public static int requestInt(String msg, int min, int max) {
        Objects.requireNonNull(msg);

        while(true) {
            System.out.print("Please provide " + msg + ": ");
            int input = parseInput();
            if (input >= min && input <= max) return input;
            else {
                System.out.println("Invalid input. Must be between " + min + " and " + max);
            }
        }
    }

    public static void clearField(GameGrid gameGrid) {
        int col = requestInt("the column of the value to clear (between 1 and 9)", 1, 9) - 1;
        int row = requestInt("the row of the value to clear (between 1 and 9)", 1, 9) - 1;
        gameGrid.clearField(row, col);
    }

    public static void setField(GameGrid gameGrid) {
        int col = requestInt("the column of the value to set (between 1 and 9)", 1, 9) - 1;
        int row = requestInt("the row of the value to set (between 1 and 9)", 1, 9) - 1;
        int value = requestInt("the value to set (between 1 and 9)", 1, 9);
        gameGrid.setField(row, col, value);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        String path = args[0];
        GameGrid gameGrid = new GameGrid(path);
        while (true) {
            printMenu();
            int input = requestInt("a value between 1 and 7", 1, 7);
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
                    GameGrid copy = new GameGrid(gameGrid);
                    boolean solved = Solver.solve(copy);
                    if (solved) {
                        System.out.println("A solution has been found!\n");
                        System.out.println(copy);
                    } else {
                        System.out.println("No solution found.");
                    }
                    break;
                case 5:
                    GameGrid gridCopy = new GameGrid(gameGrid);
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
                    GameGrid rankerCopy = new GameGrid(gameGrid);
                    float rank = Ranker.rankSudoku(rankerCopy);
                    System.out.println("Sudoku rank: " + rank);
                    break;
                case 7:
                    return;
            }
        }
    }
}
