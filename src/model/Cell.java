package model;

public class Cell {
    private CellType type;

    public Cell(CellType type) {
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public char getSymbol() {
        return type.getSymbol();
    }

    public void setSymbol(char symbol) {
        type.setSymbol(symbol);
    }
}
