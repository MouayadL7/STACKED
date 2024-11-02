package model;

public enum CellType {
    EMPTY('.'),        // Default symbol for empty cells
    OBSTACLE('#'),     // Default symbol for obstacles
    PIECE;             // Piece symbol will be set dynamically

    private char symbol;

    // Constructor for types with a default symbol
    CellType(char symbol) {
        this.symbol = symbol;
    }

    // Overloaded constructor for PIECE to set its symbol later
    CellType() {}

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        if (this == PIECE) {
            this.symbol = symbol;
        }
    }
}
