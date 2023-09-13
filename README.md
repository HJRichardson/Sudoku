# READ ME: Sudoku

## Disclaimer
This project was based on a series of tutorials by The University of Edinburgh in the course Informatics 1: Object-Oriented Programming. However, unless specifically stated in the files, all code/ideas are my own.

## Introduction
Included in this repository is a sudoku game. The user can load in a sudoku game of their choice (using the files in the <code>games</code> folder) and try to solve it themselves, with the program only allowing inputs which follow the standard sudoku rules.

Currently, there are two versions of the sudoku game: one which runs entirely through the terminal and one which has its own GUI using Java Swing. Their details are listed below.

## Version 1: Terminal

The terminal version of the sudoku game has the following features:
- Manual entry: The user can select whichever sudoku field they would like to set/clear a value for by providing coordinates in the form (row, column) (starting from the top left corner as (1,1)). The program will only allow values which satisfy the standard sudoku rules of the instance of the game (cannot have multiple values in the same row/column/subgrid, and any initially set values provided in the game files cannot be changed). If an invalid move is attempted, the value will not be entered into the grid, and the terminal will output which entries in the grid conflict with the attempted move. The terminal's grid can be seen below:

  ![Sudoku Grid](https://github.com/HJRichardson/Sudoku/blob/main/SudokuGrid.png?raw=true)

- Solver: If the user is struggling to solve the game, they can select an option to solve the game using an automated backtracking algorithm. The algorithm moves from the top left entry to the bottom right entry, trying values for each field. Should there not be a possible value for one of the fields, the algorithm moves backwards and increases one of the previous values. If the algorithm moves past the bottom right entry, then a solution has been found. If the algorithm moves back beyond the first entry in the grid, then there is no solution to be found.
  Additionally, the solver can also be used to find all possible solutions to the sudoku game.
- Ranking: The user can display the ranking of the particular sudoku game: a floating point value greater than 1 which specifies the difficulty of the game based on the number of solutions and number of free fields in the grid. The higher the rank, the easier the game. This value can be displayed for the specific sudoku game, or the ranks of all sudoku games within the <code>games</code> folder can be displayed at once.
- X-Sudoku: The user has the option to play sudoku variation called X-Sudoku. This game follows the standard sudoku rules, however it has the additional requirement that any entries on either of the main diagonals must be unique within the diagonal they are on. To play this variation, the user can load in a sudoku file in <code>games</code> which beings with "x". The solver also works for this variation.

## Version 2: GUI

This sudoku version has a Graphical User Interface (GUI) to make the game more interactive. Currently, the GUI version contains the feature to allow the user to manually enter or clear fields within a sudoku grid. An image of the interactive grid is shown below.

![Sudoku Grid](https://github.com/HJRichardson/Sudoku/blob/main/SudokuGridGUI.png?raw=true)

The blue cells are the entries which are blank. They can be clicked on and a message box pops up, prompting the user to enter a value. This value is validated against the sudoku rules and, if valid, is put into the grid.
## How to Run
To run, use an IDE of your choice and click to run the main method in either <code>Terminal.java</code> for the terminal version or <code>UserInterface.java</code> in the GUI version. In both versions, you will be asked to provide a file path to the sudoku game you would like to play. Please find the <code>games</code> folder on your computer and copy the directory path to this folder, adding onto the end the sudoku game to play (e.g. <code>D:\Users\hj1ri\Documents\Java\Sudoku\games\sudoku2.sd</code>). This will then load the sudoku game. 
