package model;

import java.util.Arrays;
import java.util.Objects;

public class Grid {
    private final int rows;
    private final int cols;
    private Cell[][] cells;
    private Cell[][] initialCells; // Backup for restart

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];
        initializeGrid();
    }

    // Copy constructor
    public Grid(Grid other) {
        this.rows = other.rows;
        this.cols = other.cols;
        this.cells = new Cell[rows][cols];

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                this.cells[row][col] = new Cell(other.cells[row][col].getType(), other.cells[row][col].getSymbol()); // Assuming Cell has a copy constructor
            }
        }
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(CellType.EMPTY, '.');  // Default to empty
            }
        }
    }

    public void backupInitialGrid() {
        initialCells = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                initialCells[i][j] = new Cell(cells[i][j].getType(), cells[i][j].getSymbol());
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

    public boolean isGameWon() {
        boolean[] vis = new boolean[26]; // Initialize an array of 26 booleans for letters 'A' to 'Z'
        Arrays.fill(vis, false); // Set all values in the array to false

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (cells[row][col].getType() == CellType.PIECE) {
                    int x = cells[row][col].getSymbol() - 'A'; // Get index based on symbol
                    if (vis[x]) {
                        return false; // If we've already seen this symbol, return false
                    }
                    vis[x] = true; // Mark this symbol as seen
                }
            }
        }

        return true;
    }


    // Getters
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public Cell getCell(int row, int col) { return cells[row][col]; }


    @Override
    public int hashCode() {
        int hash = Objects.hash(rows, cols); // Start with dimensions
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                hash = 31 * hash + Objects.hash(cells[i][j], i, j); // Include cell and position
            }
        }
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Grid other = (Grid) obj;
        if (rows != other.rows || cols != other.cols) return false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!cells[i][j].equals(other.cells[i][j])) return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                char symbol = cells[row][col].getSymbol();
                switch (symbol) {
                    case '.':
                        sb.append("⬜️");
                        break;
                    case '#':
                        sb.append("⬛️");
                        break;
                    case 'R':
                        sb.append("🟥");
                        break;
                    case 'B':
                        sb.append("🟦");
                        break;
                    case 'G':
                        sb.append("🟩");
                        break;
                    case 'Y':
                        sb.append("🟨");
                        break;
                    case 'O':
                        sb.append("🟧");
                        break;
                    case 'P':
                        sb.append("🟪");
                        break;
                    default:
                        sb.append(" ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
