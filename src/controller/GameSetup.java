package controller;

import model.Cell;
import model.CellFactory;
import model.Grid;

import java.util.Scanner;

public class GameSetup {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showOptions() {
        System.out.println("🔹 Game Setup Instructions 🔹");
        System.out.println("Choose letters to represent each piece's color:");
        System.out.println("  - 🟥 R for Red");
        System.out.println("  - 🟦 B for Blue");
        System.out.println("  - 🟨 Y for Yellow");
        System.out.println("  - 🟩 G for Green");
        System.out.println("  - 🟪 P for Purple");
        System.out.println("Use '.' to represent an EMPTY cell.");
        System.out.println("Use '#' to represent an OBSTACLE cell.");
        System.out.println("Example grid input: R B . # Y\n");
    }

    public static Grid initializeGrid() {
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();

        Grid grid = new Grid(rows, cols); // Create a new Grid instance
        System.out.println("Enter grid symbols row by row:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char symbol = scanner.next().charAt(0);
                Cell cell = CellFactory.createCell(symbol);
                grid.getCell(i, j).setType(cell.getType()); // Set the cell type
                grid.getCell(i, j).setSymbol(cell.getSymbol()); // Set the cell symbol
            }
        }

        grid.backupInitialGrid();
        return grid;
    }
}
