package app;

import javax.swing.*;

import algorithm.SearchAlgorithm;
import algorithm.SearchAlgorithmFactory;
import controller.AlgorithmController;
import controller.GameController;
import controller.GameSetup;
import model.Grid;
import view.GameView;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private int moveCount = 0;
    private JLabel movesLabel;
    private JLabel titleLabel; // Add a title label
    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    public Main() {
        GameSetup.showOptions();
        Grid grid = GameSetup.initializeGrid();

        GameView gameView = new GameView(grid);
        GameController gameController = new GameController(grid, gameView, this);

        AlgorithmController algorithmController = new AlgorithmController();

        // Display algorithm choices
        System.out.println("Select an algorithm to solve the puzzle:");
        System.out.println("1. DFS");
        System.out.println("2. BFS");
        System.out.println("3. UC");
        String chosenAlgorithm = scanner.nextLine();
        SearchAlgorithm algorithm = SearchAlgorithmFactory.getAlgorithm(chosenAlgorithm, algorithmController);

        // Set the appropriate algorithm
        algorithmController.setAlgorithm(algorithm, gameController);

        // Solve with the selected algorithm
        algorithmController.solve(grid);


//        frame = new JFrame("STACKED Game");
//
//        movesLabel = new JLabel("Moves: 0");
//        titleLabel = new JLabel("STACKED GAME", SwingConstants.CENTER); // Centered title label
//        titleLabel.setFont(titleLabel.getFont().deriveFont(24f)); // Increase font size for visibility
//
//
//        JMenuBar menuBar = createMenuBar(gameController);
//
//        frame.setJMenuBar(menuBar);
//
//        // Create a panel for title and moves
//        JPanel titlePanel = new JPanel(new BorderLayout());
//        titlePanel.add(titleLabel, BorderLayout.CENTER); // Add title label to the center
//        titlePanel.add(movesLabel, BorderLayout.SOUTH); // Add moves label to the bottom
//
//        frame.add(titlePanel, BorderLayout.NORTH); // Add title panel at the top
//        frame.add(gameView, BorderLayout.CENTER); // Add gameView in the center
//
//        frame.setSize(grid.getCols() * GameView.CELL_SIZE, grid.getRows() * GameView.CELL_SIZE + 50);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//
//        // Add a component listener to resize title dynamically
//        frame.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                adjustFont(frame.getWidth());
//            }
//        });
//
//        // Initial font adjustment
//        adjustFont(frame.getWidth());
    }

    private JMenuBar createMenuBar(GameController gameController) {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Menu");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem exit = new JMenuItem("Exit");

        newGame.addActionListener(e -> {
            gameController.startNewGame();
            resetMoveCounter();
        });
        restart.addActionListener(e -> {
            gameController.restartGame();
            resetMoveCounter();
        });
        exit.addActionListener(e -> System.exit(0));

        gameMenu.add(newGame);
        gameMenu.add(restart);
        gameMenu.add(exit);
        menuBar.add(gameMenu);

        return menuBar;
    }

    public void incrementMoveCounter() {
        moveCount++;
        movesLabel.setText("Moves: " + moveCount);
    }

    public void resetMoveCounter() {
        moveCount = 0;
        movesLabel.setText("Moves: " + moveCount);
    }

    public void showWinPopup(GameController gameController) {
        JOptionPane.showMessageDialog(null, "😊 Congratulations! You won the game! 🎉", "Win", JOptionPane.INFORMATION_MESSAGE);

        // Show the options dialog
        showOptionsDialog(gameController);
    }

    public void showOptionsDialog(GameController gameController) {
        JDialog optionsDialog = new JDialog((JFrame) null, "Game Options", true);
        optionsDialog.setLayout(new BoxLayout(optionsDialog.getContentPane(), BoxLayout.Y_AXIS));

        JLabel instructionLabel = new JLabel("Choose an option:");
        instructionLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        // Create uniform buttons with same size
        Dimension buttonSize = new Dimension(150, 40);
        JButton newGameButton = new JButton("New Game 🚀");
        JButton restartButton = new JButton("Restart ♻");
        JButton exitButton = new JButton("Exit ❌");

        // Set alignment for buttons
        newGameButton.setPreferredSize(buttonSize);
        restartButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        // Set button maximum and minimum sizes to ensure uniform sizing and centering
        newGameButton.setMaximumSize(buttonSize);
        restartButton.setMaximumSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);

        // Center-align buttons
        newGameButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        restartButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        // Add action listeners for buttons
        newGameButton.addActionListener(e -> {
            optionsDialog.dispose();  // Close dialog
            gameController.startNewGame();           // Start a new game
        });

        restartButton.addActionListener(e -> {
            optionsDialog.dispose();  // Close dialog
            gameController.restartGame();            // Restart the current game
        });

        exitButton.addActionListener(e -> System.exit(0)); // Exit the game

        // Add components to dialog
        optionsDialog.add(Box.createVerticalStrut(10)); // Spacer above label
        optionsDialog.add(instructionLabel);
        optionsDialog.add(Box.createVerticalStrut(10)); // Spacer between label and buttons
        optionsDialog.add(newGameButton);
        optionsDialog.add(Box.createVerticalStrut(5));  // Spacer between buttons
        optionsDialog.add(restartButton);
        optionsDialog.add(Box.createVerticalStrut(5));
        optionsDialog.add(exitButton);

        optionsDialog.pack();
        optionsDialog.setLocationRelativeTo(frame); // Center dialog in main game window
        optionsDialog.setVisible(true);
    }

    // Method to adjust title font size based on the window width
    private void adjustFont(int width) {
        // Calculate font size based on width; you can adjust the scaling factor
        float titleFontSize = width / 15f;
        float movesFontSize = width / 25f;
        titleLabel.setFont(titleLabel.getFont().deriveFont(titleFontSize));
        movesLabel.setFont(movesLabel.getFont().deriveFont(movesFontSize));
    }
}
