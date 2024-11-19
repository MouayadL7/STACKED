package algorithm;

import controller.AlgorithmController;
import model.Grid;
import model.Node;

import java.util.*;

public class BFS implements SearchAlgorithm{
    private final AlgorithmController controller;

    public  BFS(AlgorithmController controller) {
        this.controller = controller;
    }

    public void search(Grid initialState) {
        Set<Grid> visited = new HashSet<>(); // To track visited states
        Queue<Node> queue = new LinkedList<>(); // Queue for BFS

        Node startNode = new Node(initialState, 0, null, null);
        queue.add(startNode);
        visited.add(initialState); // Mark as visited

        int counter = 0;
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            Grid currentState = currentNode.getState();

            counter++;

            if (controller.isGoalState(currentState)) {
                currentNode.printSolutionPath(counter);
                return;
            }

            // Generate successors
            for (Node childNode : controller.generateChildNodes(currentNode)) {
                Grid childState = childNode.getState();
                if (!visited.contains(childNode.getState())) {
                    queue.add(childNode);
                    visited.add(childState);
                }
            }
        }

        System.out.println("No solution found. Total nodes visited: " + visited.size());
    }
}

