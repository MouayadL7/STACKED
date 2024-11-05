package model;

public class CellFactory {
    public static Cell createCell(char symbol) {
        switch (symbol) {
            case '.':
                return new Cell(CellType.EMPTY, symbol);
            case '#':
                return new Cell(CellType.OBSTACLE, symbol);
            default:
                if (Character.isUpperCase(symbol)) {
                    return new Cell(CellType.PIECE, symbol);
                } else {
                    throw new IllegalArgumentException("Invalid symbol: " + symbol);
                }
        }
    }
}
