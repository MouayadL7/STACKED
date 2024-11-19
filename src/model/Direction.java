package model;

public enum Direction {
    UP(-1, 0, 4),
    DOWN(1, 0, 3),
    LEFT(0, -1, 2),
    RIGHT(0, 1, 1);

    private final int cost;
    private final int rowDelta;
    private final int colDelta;

    Direction(int rowDelta, int colDelta, int cost) {
        this.rowDelta = rowDelta;
        this.colDelta = colDelta;
        this.cost = cost;
    }

    public int getRowDelta() {
        return rowDelta;
    }

    public int getColDelta() {
        return colDelta;
    }

    public int getCost() {
        return cost;
    }
}

