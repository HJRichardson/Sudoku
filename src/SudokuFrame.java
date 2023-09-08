import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.GridLayout;

public class SudokuFrame extends JFrame {
    
    private final GameGrid gameGrid;

    public SudokuFrame(String path) {
        super.setSize(500, 500);
        super.setTitle(path);
        super.setLocationRelativeTo(null);

        GridLayout gridLayout = new GridLayout(GameGrid.GRID_DIM, GameGrid.GRID_DIM);
        super.setLayout(gridLayout);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameGrid = UserInterface.gameGridSetUp(path);
        createButtonGrid();
    }

    private void createButtonGrid() {
        for (int row = 0; row < GameGrid.GRID_DIM; row++) {
            for (int col = 0; col < GameGrid.GRID_DIM; col++) {
                JButton button = new JButton();
                button.addActionListener(new SudokuFieldAction(gameGrid, row, col));
                if (gameGrid.isInitial(row, col)) {
                    button.setText(gameGrid.getField(row, col) + "");
                    button.setEnabled(false);
                } 
                this.add(button);
            }
        }
    }
}
