package model;

public class CellFactory {
    public static Cell createCell(char symbol) {
        switch (symbol) {
            case '.':
                return new Cell(CellType.EMPTY);
            case '#':
                return new Cell(CellType.OBSTACLE);
            default:
                if (Character.isUpperCase(symbol)) {
                    CellType pieceType = CellType.PIECE;
                    pieceType.setSymbol(symbol);  // Set symbol for the piece
                    return new Cell(pieceType);
                } else {
                    throw new IllegalArgumentException("Invalid symbol: " + symbol);
                }
        }
    }
}
