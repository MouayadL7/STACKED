package algorithm;

import controller.AlgorithmController;
import model.Grid;
import model.Node;

import java.util.*;

public class UCAlgorithm implements SearchAlgorithm {
    private final AlgorithmController controller;

    public UCAlgorithm(AlgorithmController controller) {
        this.controller = controller;
    }
    @Override
    public void search(Grid initialState) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        Set<Grid> visited = new HashSet<>();

        Node startNode = new Node(initialState, 0, null, null); // Modify based on start position
        queue.add(startNode);
        visited.add(initialState);

        int counter = 0;
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            Grid currentState = currentNode.getState();

            counter++;

            if (controller.isGoalState(currentState)) {
                currentNode.printSolutionPath(counter);
                return;
            }

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

