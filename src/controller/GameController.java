package controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import model.Cell;
import model.CellType;
import model.Grid;
import view.GameView;

public class GameController {
    private final Grid grid;
    private final GameView gameView;
    private final Scene scene;

    public GameController(Grid grid, GameView gameView, Scene scene) {
        this.grid = grid;
        this.gameView = gameView;
        this.scene = scene;

        setupKeyHandlers();
    }

    private void setupKeyHandlers() {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                moveUp();
            } else if (event.getCode() == KeyCode.DOWN) {
                moveDown();
            } else if (event.getCode() == KeyCode.LEFT) {
                moveLeft();
            } else if (event.getCode() == KeyCode.RIGHT) {
                moveRight();
            } else if (event.getCode() == KeyCode.R) {
                restartGame();
            }
        });
    }

    private void moveUp() {
        // Implement move up logic
        // Example: Shift pieces upwards
        System.out.println("Move Up");
        // Update grid accordingly
        updateView();
    }

    private void moveDown() {
        // Implement move down logic
        System.out.println("Move Down");
        updateView();
    }

    private void moveLeft() {
        // Implement move left logic
        System.out.println("Move Left");
        updateView();
    }

    private void moveRight() {
        // Implement move right logic
        System.out.println("Move Right");
        updateView();
    }

    private void restartGame() {
        System.out.println("Restarting Game");
        grid.resetGrid(); // Reset grid to initial state
        updateView();
    }

    private void updateView() {
        gameView.updateGrid(grid);
    }

    // Additional methods for game logic
}
