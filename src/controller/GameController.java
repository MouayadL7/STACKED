package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import model.Cell;
import model.CellType;
import model.Grid;
import view.GameView;
import app.Main;

public class GameController {
    private final Grid grid;
    private final GameView gameView;
    private final Main mainApp;

    public GameController(Grid grid, GameView gameView, Main mainApp) {
        this.grid = grid;
        this.gameView = gameView;
        this.mainApp = mainApp;

        setupKeyBindings();
    }

    private void setupKeyBindings() {
        InputMap inputMap = gameView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = gameView.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUp");
        actionMap.put("moveUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveUp();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDown");
        actionMap.put("moveDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveDown();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
        actionMap.put("moveLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveLeft();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
        actionMap.put("moveRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveRight();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "restartGame");
        actionMap.put("restartGame", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
    }

    private void moveUp() {
        Cell lastCell;
        int lastRow;
        for (int col = 0; col < grid.getCols(); ++col) {
            lastCell = grid.getCell(0, col);
            lastRow = 0;
            for (int row = 1; row < grid.getRows(); ++row) {
                Cell currentCell = grid.getCell(row, col);

                if (currentCell.getType() == CellType.EMPTY) {
                    continue;
                }
                else if (currentCell.getType() == CellType.OBSTACLE) {
                    lastCell = currentCell;
                    lastRow = row;
                }
                else {
                    if (lastCell.getType() == CellType.EMPTY) {
                        lastCell.setType(currentCell.getType());
                        lastCell.setSymbol(currentCell.getSymbol());

                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');
                    }
                    else if (lastCell.getType() == CellType.PIECE && currentCell.getSymbol() == lastCell.getSymbol()) {
                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');
                    }
                    else if (lastRow + 1 != row) {
                        Cell downCell = grid.getCell(lastRow + 1, col);
                        downCell.setType(currentCell.getType());
                        downCell.setSymbol(currentCell.getSymbol());

                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');

                        lastCell = downCell;
                        lastRow++;
                    }
                    else {
                        lastCell = currentCell;
                        lastRow = row;
                    }
                }
            }
        }

        mainApp.incrementMoveCounter();
        updateView();
        checkWinCondition();
    }

    private void moveDown() {
        Cell lastCell;
        int lastRow;
        for (int col = 0; col < grid.getCols(); ++col) {
            lastCell = grid.getCell(grid.getRows() - 1, col);
            lastRow = grid.getRows() - 1;
            for (int row = grid.getRows() - 2; row >= 0; --row) {
                Cell currentCell = grid.getCell(row, col);

                if (currentCell.getType() == CellType.EMPTY) {
                    continue;
                }
                else if (currentCell.getType() == CellType.OBSTACLE) {
                    lastCell = currentCell;
                    lastRow = row;
                }
                else {
                    if (lastCell.getType() == CellType.EMPTY) {
                        lastCell.setType(currentCell.getType());
                        lastCell.setSymbol(currentCell.getSymbol());

                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');
                    }
                    else if (lastCell.getType() == CellType.PIECE && currentCell.getSymbol() == lastCell.getSymbol()) {
                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');
                    }
                    else if (lastRow - 1 != row) {
                        Cell aboveCell = grid.getCell(lastRow - 1, col);
                        aboveCell.setType(currentCell.getType());
                        aboveCell.setSymbol(currentCell.getSymbol());

                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');

                        lastCell = aboveCell;
                        lastRow--;
                    }
                    else {
                        lastCell = currentCell;
                        lastRow = row;
                    }
                }
            }
        }

        mainApp.incrementMoveCounter();
        updateView();
        checkWinCondition();
    }

    private void moveLeft() {
        Cell lastCell;
        int lastCol;
        for (int row = 0; row < grid.getRows(); ++row) {
            lastCell = grid.getCell(row, 0);
            lastCol = 0;
            for (int col = 1; col < grid.getCols(); ++col) {
                Cell currentCell = grid.getCell(row, col);

                if (currentCell.getType() == CellType.EMPTY) {
                    continue;
                }
                else if (currentCell.getType() == CellType.OBSTACLE) {
                    lastCell = currentCell;
                    lastCol = col;
                }
                else {
                    if (lastCell.getType() == CellType.EMPTY) {
                        lastCell.setType(currentCell.getType());
                        lastCell.setSymbol(currentCell.getSymbol());

                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');
                    }
                    else if (lastCell.getType() == CellType.PIECE && currentCell.getSymbol() == lastCell.getSymbol()) {
                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');
                    }
                    else if (lastCol + 1 != col) {
                        Cell rightCell = grid.getCell(row, lastCol + 1);
                        rightCell.setType(currentCell.getType());
                        rightCell.setSymbol(currentCell.getSymbol());

                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');

                        lastCell = rightCell;
                        lastCol++;
                    }
                    else {
                        lastCell = currentCell;
                        lastCol = col;
                    }
                }
            }
        }

        mainApp.incrementMoveCounter();
        updateView();
        checkWinCondition();
    }

    private void moveRight() {
        Cell lastCell;
        int lastCol;
        for (int row = 0; row < grid.getRows(); ++row) {
            lastCell = grid.getCell(row, grid.getCols() - 1);
            lastCol = grid.getCols() - 1;
            for (int col = grid.getCols() - 2; col >= 0; --col) {
                Cell currentCell = grid.getCell(row, col);

                if (currentCell.getType() == CellType.EMPTY) {
                    continue;
                }
                else if (currentCell.getType() == CellType.OBSTACLE) {
                    lastCell = currentCell;
                    lastCol = col;
                }
                else {
                    if (lastCell.getType() == CellType.EMPTY) {
                        lastCell.setType(currentCell.getType());
                        lastCell.setSymbol(currentCell.getSymbol());

                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');
                    }
                    else if (lastCell.getType() == CellType.PIECE && currentCell.getSymbol() == lastCell.getSymbol()) {
                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');
                    }
                    else if (lastCol - 1 != col) {
                        Cell leftCell = grid.getCell(row, lastCol - 1);
                        leftCell.setType(currentCell.getType());
                        leftCell.setSymbol(currentCell.getSymbol());

                        currentCell.setType(CellType.EMPTY);
                        currentCell.setSymbol('.');

                        lastCell = leftCell;
                        lastCol--;
                    }
                    else {
                        lastCell = currentCell;
                        lastCol = col;
                    }
                }
            }
        }

        mainApp.incrementMoveCounter();
        updateView();
        checkWinCondition();
    }

    public void restartGame() {
        grid.resetGrid(); // Reset grid to initial state
        mainApp.resetMoveCounter();
        updateView();
    }

    private void updateView() {
        gameView.updateGrid(grid);
    }

    private void checkWinCondition() {
        if (isGameWon()) {
            mainApp.showWinPopup(this);
        }
    }

    private boolean isGameWon() {
        boolean[] vis = new boolean[26]; // Initialize an array of 26 booleans for letters 'A' to 'Z'
        Arrays.fill(vis, false); // Set all values in the array to false

        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                if (grid.getCell(row, col).getType() == CellType.PIECE) {
                    int x = grid.getCell(row, col).getSymbol() - 'A'; // Get index based on symbol
                    if (vis[x]) {
                        return false; // If we've already seen this symbol, return false
                    }
                    vis[x] = true; // Mark this symbol as seen
                }
            }
        }

        return true;
    }

    public void startNewGame() {
        // Close the current frame
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(gameView);
        if (frame != null) {
            frame.dispose();
        }

        // Start a new instance of the game
        SwingUtilities.invokeLater(Main::new);
    }
}
