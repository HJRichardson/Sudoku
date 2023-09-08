import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

import java.io.UncheckedIOException;
import java.io.IOException;
import java.io.File;

import java.util.Objects;
import java.util.HashMap;

public class IOUtils {

    /**
     * This function loads a Sudoku game grid from a file.
     *
     * @param gridFileName the path to a Sudoku grid data file
     * @return a two-dimensional integer array holding the data from the specified file
     *
     */
    public static int[][] loadFromFile(String gridFileName) {
        Objects.requireNonNull(gridFileName);

        Path fileName = Paths.get(gridFileName);

        if (!Files.exists(fileName))
            throw new IllegalArgumentException("Given file does not exist: " + fileName);

        int[][] grid = new int[GameGrid.GRID_DIM][GameGrid.GRID_DIM];
        
        try {     
        	Scanner in = new Scanner(fileName);         
	
	        for(int row = 0; row < GameGrid.GRID_DIM; row++) {
	            for(int column = 0; column < GameGrid.GRID_DIM; column++) {
	                if(!in.hasNextInt())
	                    throw new RuntimeException("Given Sudoku file has invalid format: " + fileName);
	
	                int value = in.nextInt();
	                if (value < 0 || value > GameGrid.GRID_DIM)
	                    throw new RuntimeException("Given Sudoku file has invalid "
	                               + "entry at: " + column + "x" + row);
	               
	                grid[row][column] = value;
	            }
	        }
	        
	        in.close();
        
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return grid;
    }

    public static HashMap<String, GameGrid> loadFromFolder(String dir) {
        Objects.requireNonNull(dir);
        
        HashMap<String, GameGrid> map = new HashMap<>();
        File file = new File(dir);
        if (file.isDirectory()) {
            for (String entry : file.list()) {
                if (entry.startsWith("sudoku") && entry.endsWith(".sd")) {
                    map.put(entry, new RGameGrid(dir + "\\" + entry));
                }
            }
        }
        return map;
    }

}
