package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private final Grid state;
    private final int cost;
    private final Node parent;
    private final String direction;

    public Node(Grid state, int cost, Node parent, String direction) {
        this.state = state;
        this.cost = cost;
        this.parent = parent;
        this.direction = direction;
    }

    public Grid getState() {
        return state;
    }

    public int getCost() {
        return cost;
    }

    public Node getParent() {
        return parent;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public int hashCode() {
        // Use the Grid's hashCode method to uniquely identify the state
        return Objects.hash(state);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node other = (Node) obj;
        return Objects.equals(state, other.state);
    }

    public void printSolutionPath(int totalNodesVisited) {
        List<Node> path = new ArrayList<>();

        Node current = this;
        while (current != null) {
            path.addFirst(current);
            current = current.getParent();
        }

        System.out.println("Solution found!");
        System.out.println("Total nodes visited: " + totalNodesVisited);
        System.out.println("Number of nodes in the path: " + path.size());

        for (Node node : path) {
            if (node.getDirection() != null) {
                System.out.println("Direction: " + node.getDirection());
            }
            System.out.println(node.getState());
        }
    }
}
