package model;

public class Cell {
    private CellType type;
    private char symbol; // Add symbol directly to the cell

    public Cell(CellType type, char symbol) {
        this.type = type;
        this.symbol = symbol; // Initialize with default symbol
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
