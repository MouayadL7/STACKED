package algorithm;

import controller.AlgorithmController;
import model.Grid;
import model.Node;

import java.util.*;

public class DFS implements SearchAlgorithm {
    private final AlgorithmController controller;

    public  DFS(AlgorithmController controller) {
        this.controller = controller;
    }

    @Override
    public void search(Grid initialState) {
        Deque<Node> stack = new ArrayDeque<>();
        Set<Grid> visited = new HashSet<>();

        Node startNode = new Node(initialState, 0, null, null);
        stack.push(startNode);
        visited.add(initialState);

        int counter = 0;
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            Grid currentState = currentNode.getState();

            counter++;

            if (controller.isGoalState(currentState)) {
                currentNode.printSolutionPath(counter);
                return;
            }

            for (Node childNode : controller.generateChildNodes(currentNode)) {
                Grid childState = childNode.getState();
                if (!visited.contains(childNode.getState())) {
                    stack.push(childNode);
                    visited.add(childState);
                }
            }
        }

        System.out.println("No solution found. Total nodes visited: " + visited.size());
    }
}

