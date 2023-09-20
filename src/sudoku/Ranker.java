package sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
* This class implements functionality for a ranking system
* for sudoku games.
*/
public class Ranker {
    
    /**
     * Ranks the sudoku game based on number of solutions and
     * number of free fields- higher value is an easier game.
     *
     * @param gameGrid - The GameGrid instance.
     * @return The rank of the sudoku game.
     */
    public static float rankSudoku(GameGrid gameGrid) {
        Objects.requireNonNull(gameGrid);

        ArrayList<GameGrid> solutions = Solver.findAllSolutions(gameGrid);
        int numSolutions = solutions.size();
        int numFreeFields = countFreeFields(gameGrid);
        float rank = calculateRank(numSolutions, numFreeFields);
        return rank;
    }

    /**
     * Counts the number of free fields in a sudoku game
     * (fields which are not initially set).
     *
     * @param gameGrid - The GameGrid instance.
     * @return The number of free fields in the sudoku game.
     */
    private static int countFreeFields(GameGrid gameGrid) {
        Objects.requireNonNull(gameGrid);

        int numFreeFields = 0;
        for (int row = 0; row < GameGrid.GRID_DIM; row++) {
            for (int col = 0; col < GameGrid.GRID_DIM; col++) {
                if (!gameGrid.isInitial(row, col)) {
                    numFreeFields++;
                }
            }
        }
        return numFreeFields;
    }

    /**
     * Calculates the rank of a sudoku game given the number of solutions
     * and free fields.
     * NOTE: This function is adapted from one of the University of Edinburgh
     * tutorials.
     *
     * @param numSolutions - Number of solutions to the sudoku game.
     * @param numFreeFields - Number of free fields in the sudoku game.
     * @return The rank of the sudoku game.
     */
    private static float calculateRank(int numSolutions, int numFreeFields) {
        if (numSolutions == 0) {
            return Float.MAX_VALUE;
        }
        return numSolutions + (1 - (numFreeFields * (1f / (GameGrid.GRID_DIM * GameGrid.GRID_DIM))));
    }

    /**
     * Displays the ranks of every sudoku game and finds the easiest game,
     * including calculating the maximum rank.
     * @param args - The directory where the sudoku games are located.
     */
    private static void displayRanks(String directory) {
        Objects.requireNonNull(directory);

        HashMap<String, GameGrid> map = IOUtils.loadFromFolder(directory);
        float maxRank = Float.MIN_VALUE;
        String maxRankName = "";
        for (String key : map.keySet()) {
            GameGrid gameGrid = map.get(key);
            float rank = rankSudoku(gameGrid);
            System.out.println("For " + key + ", Rank = " + rank);

            if (rank > maxRank) {
                maxRank = rank;
                maxRankName = key;
            }
        }
        System.out.println("\nThe max ranking sudoku is " + maxRankName + " with a rank of " + maxRank);
    } 

    /**
     * Displays the ranks of every sudoku game, inclduing the easiest game.
     * @param args - The directory where the sudoku games are located.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        String directory = args[0];
        displayRanks(directory);
    }
}
