package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.*;
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
                move(grid, Direction.UP);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDown");
        actionMap.put("moveDown", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                move(grid, Direction.DOWN);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
        actionMap.put("moveLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                move(grid, Direction.LEFT);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
        actionMap.put("moveRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                move(grid, Direction.RIGHT);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "restartGame");
        actionMap.put("restartGame", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
    }

    public void move(Grid grid, Direction direction) {
        Cell lastCell;
        int lastIndex;

        if (direction == Direction.UP || direction == Direction.DOWN) {
            for (int col = 0; col < grid.getCols(); ++col) {
                UpdatedData updatedData = new UpdatedData(
                        grid.getCell(direction == Direction.UP ? 0 : grid.getRows() - 1, col),
                        direction == Direction.UP ? 0 : grid.getRows() - 1
                );

                for (int row = (direction == Direction.UP ? 1 : grid.getRows() - 2);
                     (direction == Direction.UP ? row < grid.getRows() : row >= 0);
                     row += (direction == Direction.UP ? 1 : -1)) {

                    processCell(grid, updatedData, row, col, direction);
                }
            }
        } else if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            for (int row = 0; row < grid.getRows(); ++row) {
                UpdatedData updatedData = new UpdatedData(
                        grid.getCell(row, direction == Direction.LEFT ? 0 : grid.getCols() - 1),
                        direction == Direction.LEFT ? 0 : grid.getCols() - 1
                );


                for (int col = (direction == Direction.LEFT ? 1 : grid.getCols() - 2);
                     (direction == Direction.LEFT ? col < grid.getCols() : col >= 0);
                     col += (direction == Direction.LEFT ? 1 : -1)) {


                    processCell(grid, updatedData, row, col, direction);
                }
            }
        }

//        mainApp.incrementMoveCounter();
//        updateView();
//        checkWinCondition();
    }

    private void processCell(Grid grid, UpdatedData updatedData, int row, int col, Direction direction) {
        Cell currentCell = grid.getCell(row, col);

        if (currentCell.getType() == CellType.EMPTY) {
            return;
        } else if (currentCell.getType() == CellType.OBSTACLE) {
            updatedData.setLastCell(currentCell);
            updatedData.setLastIndex((direction == Direction.UP || direction == Direction.DOWN) ? row : col);
        } else {
            if (updatedData.getLastCell().getType() == CellType.EMPTY) {
                updatedData.getLastCell().setType(currentCell.getType());
                updatedData.getLastCell().setSymbol(currentCell.getSymbol());

                currentCell.setType(CellType.EMPTY);
                currentCell.setSymbol('.');
            } else if (updatedData.getLastCell().getType() == CellType.PIECE && currentCell.getSymbol() == updatedData.getLastCell().getSymbol()) {
                currentCell.setType(CellType.EMPTY);
                currentCell.setSymbol('.');
            } else if ((direction == Direction.UP || direction == Direction.LEFT ? updatedData.getLastIndex() + 1 : updatedData.getLastIndex() - 1) != (direction == Direction.UP || direction == Direction.DOWN ? row : col)) {
                int newIndex = updatedData.getLastIndex() + (direction == Direction.UP || direction == Direction.LEFT ? 1 : -1);
                Cell targetCell = grid.getCell((direction == Direction.UP || direction == Direction.DOWN) ? newIndex : row,
                        (direction == Direction.UP || direction == Direction.DOWN) ? col : newIndex);
                targetCell.setType(currentCell.getType());
                targetCell.setSymbol(currentCell.getSymbol());

                currentCell.setType(CellType.EMPTY);
                currentCell.setSymbol('.');

                updatedData.setLastCell(targetCell);
                updatedData.setLastIndex(newIndex);
            } else {
                updatedData.setLastCell(currentCell);
                updatedData.setLastIndex((direction == Direction.UP || direction == Direction.DOWN) ? row : col);
            }
        }
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
        if (grid.isGameWon()) {
            mainApp.showWinPopup(this);
        }
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
