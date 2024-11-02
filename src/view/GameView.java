package view;

import model.Grid;

public class GameView {
    public void displayGrid(Grid grid) {
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                System.out.print(grid.getCell(i, j).getType() + " ");
            }
            System.out.println();
        }
    }

    // Add any other display functions as needed...
}
