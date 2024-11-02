import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import controller.GameController;
import controller.GameSetup;
import model.Grid;
import view.GameView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Show color options and initialize grid
        GameSetup.showOptions();
        Grid grid = GameSetup.initializeGrid();

        // Create GameView
        GameView gameView = new GameView(grid);

        // Create layout and scene
        BorderPane root = new BorderPane();
        root.setCenter(gameView);
        Scene scene = new Scene(root, grid.getCols() * 40, grid.getRows() * 40);

        // Create GameController
        GameController gameController = new GameController(grid, gameView, scene);

        // Set up the stage
        primaryStage.setTitle("STACKED Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
