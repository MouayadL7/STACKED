package controller;

import model.Grid;
import view.GameView;

public class GameController {
    private Grid grid;
    private GameView view;

    public GameController(Grid grid, GameView view) {
        this.grid = grid;
        this.view = view;
    }

    public void startGame() {
        view.displayGrid(grid);
        // Add user input handling and update the grid as needed
    }

    // Add methods for game logic, such as moving pieces or adding obstacles
}
