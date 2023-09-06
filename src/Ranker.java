import java.util.ArrayList;
import java.util.HashMap;

public class Ranker {
    
    public static float rankSudoku(GameGrid game) {
        ArrayList<GameGrid> solutions = Solver.findAllSolutions(game);
        int numSolutions = solutions.size();
        int numFreeFields = countFreeFields(game);
        if (numSolutions == 0) {
            return Float.MAX_VALUE;
        }
        return numSolutions + (1 - (numFreeFields * (1f / 81)));
    }

    private static int countFreeFields(GameGrid game) {
        int numFreeFields = 0;
        for (int row = 0; row < GameGrid.GRID_DIM; row++) {
            for (int col = 0; col < GameGrid.GRID_DIM; col++) {
                if (!game.isInitial(row, col)) {
                    numFreeFields++;
                }
            }
        }
        return numFreeFields;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        HashMap<String, GameGrid> map = IOUtils.loadFromFolder(args[0]);
        float maxRank = Float.MIN_VALUE;
        String maxRankName = "";
        for (String key : map.keySet()) {
            GameGrid game = map.get(key);
            float rank = rankSudoku(game);
            System.out.println("For " + key + ", Rank = " + rank);

            if (rank > maxRank) {
                maxRank = rank;
                maxRankName = key;
            }
        }
        System.out.println("\nThe max ranking sudoku is " + maxRankName + " with a rank of " + maxRank);
    }
}
