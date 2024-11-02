package model;

public class Grid {
    private int rows;
    private int cols;
    private Cell[][] cells;
    private Cell[][] initialCells; // Backup for restart

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];
        initializeGrid();
        backupInitialGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(CellType.EMPTY);  // Default to empty
            }
        }
    }

    private void backupInitialGrid() {
        initialCells = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                initialCells[i][j] = new Cell(cells[i][j].getType());
                initialCells[i][j].setSymbol(cells[i][j].getSymbol());
            }
        }
    }

    public void resetGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].setType(initialCells[i][j].getType());
                cells[i][j].setSymbol(initialCells[i][j].getSymbol());
            }
        }
    }

    // Getters
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public Cell getCell(int row, int col) { return cells[row][col]; }
}
