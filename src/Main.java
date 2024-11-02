import controller.GameSetup;
import model.Grid;
import view.GameView;

public class Main {
    public static void main(String[] args) {
        GameView gameView = new GameView();

        // Show color options for user input
        GameSetup.showColorOptions();

        // Initialize the grid based on user input
        Grid grid = GameSetup.initializeGrid();

        // Display the initialized grid
        gameView.displayGrid(grid);
    }
}
