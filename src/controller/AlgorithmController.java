package controller;

import algorithm.*;
import model.Direction;
import model.Grid;
import model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlgorithmController {

    private GameController controller;
    private SearchAlgorithm algorithm;

    public void setAlgorithm(SearchAlgorithm algorithm, GameController controller) {
        this.algorithm = algorithm;
        this.controller = controller;
    }

    public void solve(Grid grid) {
        if (algorithm == null) {
            throw new IllegalStateException("No algorithm set!");
        }

        if (isGoalState(grid)) {
            Node startNode = new Node(grid, 0, null, null);
            startNode.printSolutionPath(0);
            return;
        }

        // Start the search with the provided algorithm and grid
        algorithm.search(grid);
    }

    // Shared logic to check if a state is the goal (you can call this within algorithms)
    public boolean isGoalState(Grid state) {
        return state.isGameWon();  // This method checks if the grid state is a goal
    }

    // Shared logic to generate successors (you can call this within algorithms)
    public List<Node> generateChildNodes(Node parent) {
        List<Node> childNodes = new ArrayList<>();
        Grid currentState = parent.getState();

        for (Direction direction : Direction.values()) {
            Grid newState = new Grid(currentState); // Deep copy the grid
            controller.move(newState, direction);       // Apply movement

            Node child = new Node(newState, parent.getCost() + direction.getCost(), parent, direction.name());
            childNodes.add(child);
        }

        return childNodes;
    }
}
