package view;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Cell;
import model.CellType;
import model.Grid;

public class GameView extends GridPane {
    private static final int CELL_SIZE = 40; // Size of each cell in pixels

    public GameView(Grid grid) {
        initializeGrid(grid);
    }

    private void initializeGrid(Grid grid) {
        this.getChildren().clear();
        this.setGridLinesVisible(true);
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                Rectangle rect = createCellRectangle(grid.getCell(i, j));
                this.add(rect, j, i);
            }
        }
    }

    public void updateGrid(Grid grid) {
        this.getChildren().clear();
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                Rectangle rect = createCellRectangle(grid.getCell(i, j));
                this.add(rect, j, i);
            }
        }
    }

    private Rectangle createCellRectangle(Cell cell) {
        Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE);
        switch (cell.getType()) {
            case EMPTY:
                rect.setFill(Color.LIGHTGRAY);
                break;
            case OBSTACLE:
                rect.setFill(Color.DARKGRAY);
                break;
            case PIECE:
                rect.setFill(getColorForPiece(cell.getType().getSymbol()));
                break;
            default:
                rect.setFill(Color.WHITE);
        }
        rect.setStroke(Color.BLACK);
        return rect;
    }

    private Color getColorForPiece(char symbol) {
        return switch (symbol) {
            case 'R' -> Color.RED;
            case 'B' -> Color.BLUE;
            case 'G' -> Color.GREEN;
            case 'Y' -> Color.YELLOW;
            default -> Color.BLACK; // Default color for unknown symbols
        };
    }
}
