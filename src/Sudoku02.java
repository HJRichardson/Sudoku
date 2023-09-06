import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Objects;

public class Sudoku02 {

    /**
     * Print a game menu message to the console.
     */
    public static void printMenu() {
        System.out.print("\n" +
                "1. Print game\n" +
                "2. Clear Field\n" +
                "3. Set Field\n" +
                "4. Exit\n\n" + 
                "Select an action [1-4]: ");
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

    public static void printGrid(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                System.out.print(grid[row][col] + " ");
                if ((col + 1) % 3 == 0 && (col != grid[0].length - 1)) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((row + 1) % 3 == 0 && row != grid.length - 1) {
                System.out.println("----------------------");
            }
        }
    }

    public static void clearField(int[][] grid) {
        int col = requestInt("the column of the value to clear (between 1 and 9)", 1, 9) - 1;
        int row = requestInt("the row of the value to clear (between 1 and 9)", 1, 9) - 1;
        grid[row][col] = 0;
    }

    public static void setField(int[][] grid) {
        int col = requestInt("the column of the value to set (between 1 and 9)", 1, 9) - 1;
        int row = requestInt("the row of the value to set (between 1 and 9)", 1, 9) - 1;
        int value = requestInt("the value to set (between 1 and 9)", 1, 9);
        if (isValid(grid, row, col, value)) {
            grid[row][col] = value;
        }
    }

    public static boolean checkRow(int[][] grid, int row, int col, int value) {
        for (int i = 0; i < grid[0].length; i++) {
            if (i != col && grid[row][i] == value) {
                System.out.printf("There is a %d in the same row! Located at (%d, %d).\n", value, i+1, row+1);
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkCol(int[][] grid, int row, int col, int value) {
        for (int j = 0; j < grid.length; j++) {
            if (j != row && grid[j][col] == value) {
                System.out.printf("There is a %d in the same column! Located at (%d, %d).\n", value, col+1, j+1);
                return true;
            }
        }
        return false;
    }

    public static boolean checkSubGrid(int[][] grid, int row, int col, int value) {
        int rowOffset = row % 3;
        int colOffset = col % 3;
        int startRow = row - rowOffset;
        int startCol = col - colOffset;
        for (int j = startRow; j < startRow + 3; j++) {
            for (int i = startCol; i < startCol + 3; i++) {
                if ((grid[j][i] == value) && ((j != row) || (i != col))) {
                    System.out.printf("There is a %d in the same subgrid! Located at (%d, %d).\n", value, i+1, j+1);
                    return true;
                } 
            }
        }
        return false;
    }

    public static boolean isValid(int[][] grid, int row, int col, int value) {
        boolean rowCheck = checkRow(grid, row, col, value);
        boolean colCheck = checkCol(grid, row, col, value);
        boolean subGridCheck = checkSubGrid(grid, row, col, value);
        System.out.println("Row Check: " + rowCheck);
        System.out.println("Col Check: " + colCheck);
        System.out.println("SubGrid Check: " + subGridCheck);
        return !(rowCheck || colCheck || subGridCheck);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {9,4,0,1,0,2,0,5,8},
            {6,0,0,0,5,0,0,0,4},
            {0,0,2,4,0,3,1,0,0},
            {0,2,0,0,0,0,0,6,0},
            {5,0,8,0,2,0,4,0,1},
            {0,6,0,0,0,0,0,8,0},
            {0,0,1,6,0,8,7,0,0},
            {7,0,0,0,4,0,0,0,3},
            {4,3,0,5,0,9,0,1,2}
        };
        while (true) {
            printMenu();
            int input = requestInt("a value between 1 and 4", 1, 4);
            switch (input) {
                case 2:
                    clearField(grid);
                    break;
                case 3:
                    setField(grid);
                    break;
                case 4:
                    return;
            }
            printGrid(grid);
        }
    }
}
