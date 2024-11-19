package algorithm;

import controller.AlgorithmController;

public class SearchAlgorithmFactory {
    // Method to create and return the appropriate algorithm based on input
    public static SearchAlgorithm getAlgorithm(String algorithmType, AlgorithmController controller) {
        switch (algorithmType) {
            case "DFS":
                return new DFS(controller);
            case "BFS":
                return new BFS(controller);
            case "UC":
                return new UCAlgorithm(controller);
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithmType);
        }
    }
}

