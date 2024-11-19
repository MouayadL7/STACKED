package model;

import java.util.Objects;

public class Cell {
    private CellType type;
    private char symbol;

    public Cell(CellType type, char symbol) {
        this.type = type;
        this.symbol = symbol;
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

    @Override
    public int hashCode() {
        return Objects.hash(type, symbol); // Combine cell's type and symbol
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cell other = (Cell) obj;
        return type == other.type && symbol == other.symbol;
    }

}
