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
     * NOTE: This function is adapted from one of the University of Edinburgh
     * tutorials.
     * @param path - The file path to a sudoku file.
     * @return The 2D array of sudoku values.
     *
     */
    public static int[][] loadFromFile(String path) {
        Objects.requireNonNull(path);

        Path fileName = Paths.get(path);

        if (!Files.exists(fileName))
            throw new IllegalArgumentException("Given file does not exist: " + fileName);

        int[][] grid = new int[GameGrid.GRID_DIM][GameGrid.GRID_DIM];
        
        try {     
        	Scanner scanner = new Scanner(fileName);         
	        for(int row = 0; row < GameGrid.GRID_DIM; row++) {
	            for(int col = 0; col < GameGrid.GRID_DIM; col++) {
	                if(!scanner.hasNextInt())
	                    throw new RuntimeException("Given Sudoku file has invalid format: " + fileName);
	
	                int value = scanner.nextInt();
	                if (value < 0 || value > GameGrid.GRID_DIM)
	                    throw new RuntimeException("Given Sudoku file has an invalid "
	                               + "entry at: " + col + "x" + row);
	               
	                grid[row][col] = value;
	            }
	        }
	        scanner.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return grid;
    }

    /**
     * This function loads every valid sudoku game from a folder.
     * 
     * @param path - The file path to a folder with sudoku files.
     * @return A HashMap of file name to GameGrid instances.
     */    
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
