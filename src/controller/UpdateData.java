package controller;

import model.Cell;

class UpdatedData {
    public Cell lastCell;
    public int lastIndex;

    public UpdatedData(Cell lastCell, int lastIndex) {
        this.lastCell = lastCell;
        this.lastIndex = lastIndex;
    }

    public void setLastCell(Cell lastCell) {
        this.lastCell = lastCell;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public Cell getLastCell() {
        return lastCell;
    }

    public int getLastIndex() {
        return lastIndex;
    }
}

