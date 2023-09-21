# READ ME: Sudoku

## Disclaimer
This project was based on a series of tutorials by The University of Edinburgh in the course Informatics 1: Object-Oriented Programming. However, unless specifically stated in the files, all code/ideas are my own.

## Introduction
Included in this repository is a sudoku game. The user can load in a sudoku game of their choice (using the files in the <code>games</code> folder) and try to solve it themselves, with the program only allowing inputs which follow the standard sudoku rules.

Currently, there are two versions of the sudoku game: one which runs entirely through the terminal and one which has its own GUI using Java Swing. Their details are listed below.

## Version 1: Terminal

The terminal version of the sudoku game has the following features listed in the menu below:

![Terminal Menu](https://github.com/HJRichardson/Sudoku/blob/main/screenshots/TerminalMenu.png?raw=true)
- Manual entry: The user can select whichever sudoku field they would like to set/clear a value for by providing coordinates in the form $(row, column)$, where the top left corner is $(1, 1)$. The program will only allow values which satisfy the standard sudoku rules of the instance of the game (cannot have multiple values in the same row/column/subgrid, and any initially set values provided in the game files cannot be changed). If an invalid move is attempted, the value will not be entered into the grid, and the terminal will output which entries in the grid conflict with the attempted move. The terminal's grid can be seen below:

  ![Sudoku Grid](https://github.com/HJRichardson/Sudoku/blob/main/screenshots/SudokuGrid.png?raw=true)

- Solver: If the user is struggling to solve the game, they can select an option to solve the game using an automated backtracking algorithm. The algorithm moves from the top left entry to the bottom right entry, trying values for each field. Should there not be a possible value for one of the fields, the algorithm moves backwards and increases one of the previous values. If the algorithm moves past the bottom right entry, then a solution has been found. If the algorithm moves back beyond the first entry in the grid, then there is no solution to be found.
  Additionally, the solver can also be used to find all possible solutions to the sudoku game. Below is an example of the game above being solved by the solver:

  ![Sudoku Solutions](https://github.com/HJRichardson/Sudoku/blob/main/screenshots/SudokuSolutions.png?raw=true)
  
- Ranking: The user can display the ranking of the particular sudoku game: a floating point value which specifies the difficulty of the game, denoted as $r$, based on the number of solutions, $s$, and number of free fields in the grid, $f$, for an $n$ x $n$ grid. The higher the rank, the easier the game. Note that a sudoku game with no solutions is assigned a value of infinity (i.e. no rank). The formula for the rank, r, can be seen below:

  $$r = s + \left(1 - \frac{f}{n^{2}}\right)$$

  Within the formula, the number of solutions $s$ controls the integer part of the ranking, while the number of free fields is used to add on a small value based on how many/few free fields there are in the grid. This value can be displayed for the specific sudoku game, or the ranks of all sudoku games within the <code>games</code> folder can be displayed at once by running the main method of <code>Ranker.java</code>.
- X-Sudoku: The user has the option to play a sudoku variation called X-Sudoku. This game follows the standard sudoku rules, however it has the additional requirement that any entries on either of the main diagonals must be unique within the diagonal they are on. To play this variation, the user can load in a sudoku file in <code>games</code> which beings with <code>x</code>. The solver also works for this variation. To make it the diagonal entries clearer, they are enclosed within square braces. An example of this can be seen below:

  ![X-Sudoku Grid](https://github.com/HJRichardson/Sudoku/blob/main/screenshots/XSudokuGrid.png?raw=true)

## Version 2: GUI

This sudoku version has a Graphical User Interface (GUI) to make the game more interactive. The main sudoku frame for this version can be seen below:

![Sudoku Grid GUI](https://github.com/HJRichardson/Sudoku/blob/main/screenshots/SudokuGridGraphics.png?raw=true)

The features are the same as the ones above, with some small changes:

- Manual entry: The user can try to set the values of the blue cells, which are entries that are blank. They can be clicked on and a message box pops up, prompting the user to enter a value:

  ![Input Prompt](https://github.com/HJRichardson/Sudoku/blob/main/screenshots/InputPromptGUI.png?raw=true)

  This value is validated against the sudoku rules and, if valid, is put into the grid. If not, an error message is shown. Should the value be valid, the button remains enabled should the user want to change the value, either by entering in the new value or by entering   a <code>0</code> to clear the field.

- Load game: The user has the option to load another sudoku game by clicking "Load Game" and entering the file path to the sudoku file:

  ![Sudoku File Path](https://github.com/HJRichardson/Sudoku/blob/main/screenshots/SudokuFilePathGUI.png?raw=true)

  This file path may be of the form <code>D:\Users\hj1ri\Documents\Java\Sudoku\games\sudoku2.sd</code>. If either the file does not exist or the file is not of the correct format (i.e. <code>.sd</code> files), then an error message is shown.

- Solve game: The user can click "Solve Game" to allow the backtracking algorithm to solve the problem and display the solution. This will disable every button so no more inputs can be made:

  ![Sudoku File Path](https://github.com/HJRichardson/Sudoku/blob/main/screenshots/SolveGameGraphics.png?raw=true)

  The program will display a message should the sudoku not be solveable.

- Rank game: The user can display the rank of the game by clicking "Rank Game". This displays the rank of the initial game to show its difficulty based on the formula in the "Version 1: Terminal" section:

  ![Sudoku File Path](https://github.com/HJRichardson/Sudoku/blob/main/screenshots/RankGameGUI.png?raw=true?)

  
## How to Run
To run, use an IDE of your choice and click to run the main method in either <code>Terminal.java</code> for the terminal version or <code>UserInterface.java</code> in the GUI version. In both versions, you will be asked to provide a file path to the sudoku game you would like to play. Please find the <code>games</code> folder on your computer and copy the directory path to this folder, adding onto the end the sudoku game to play (e.g. <code>D:\Users\hj1ri\Documents\Java\Sudoku\games\sudoku2.sd</code>). This will then load the sudoku game. 
