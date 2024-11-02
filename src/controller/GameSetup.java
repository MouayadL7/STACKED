package controller;

import model.Cell;
import model.CellFactory;
import model.Grid;

import java.util.Scanner;

public class GameSetup {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showColorOptions() {
        System.out.println("Enter uppercase letters to represent pieces, e.g., R for Red, B for Blue, etc.");
        System.out.println("Use '.' for EMPTY and '#' for OBSTACLE.");
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
                grid.getCell(i, j).setType(CellFactory.createCell(symbol).getType()); // Set the cell type
            }
        }
        return grid;
    }
}
