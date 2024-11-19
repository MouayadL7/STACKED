package view;

import javax.swing.*;
import java.awt.*;
import model.Cell;
import model.Grid;

public class GameView extends JPanel {
    public static final int CELL_SIZE = 130;

    public GameView(Grid grid) {
        setLayout(new GridLayout(grid.getRows(), grid.getCols()));
        drawGrid(grid);
    }

    public void drawGrid(Grid grid) {
        this.removeAll(); // Clear previous components
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                JButton cellButton = createCellButton(grid.getCell(i, j));
                add(cellButton);
            }
        }
        revalidate(); // Refresh layout
        repaint(); // Repaint the panel
    }

    public void updateGrid(Grid grid) {
        drawGrid(grid); // Redraw the grid to update it
    }

    private JButton createCellButton(Cell cell) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        button.setBackground(getColorForPiece(cell.getSymbol()));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return button;
    }

    private Color getColorForPiece(char symbol) {
        return switch (symbol) {
            case 'R' -> Color.RED;
            case 'B' -> Color.BLUE;
            case 'G' -> Color.GREEN;
            case 'Y' -> Color.YELLOW;
            case 'P' -> Color.MAGENTA;
            case '#' -> Color.BLACK;
            default -> Color.LIGHT_GRAY; // Default for empty
        };
    }
}
