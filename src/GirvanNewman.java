package src;

import java.util.*;

public class GirvanNewman {
    public static List<Set<Integer>> split(Graph graph) {
        List<Set<Integer>> communities = graph.getConnectedComponents();

        double oldModularity = -1;
        double newModularity = calculateModularity(graph, communities);
        Pair edgeToRemove = null;


        while (newModularity > oldModularity) {
            edgeToRemove = graph.edgeBetweennessCentrality();

            if (edgeToRemove != null) {
                graph.removeEdge(edgeToRemove.getFirstVertex(), edgeToRemove.getSecondVertex());
            }

            communities = graph.getConnectedComponents();

            oldModularity = newModularity;
            newModularity = calculateModularity(graph, communities);
        }

        if (edgeToRemove != null) {
            graph.addEdge(edgeToRemove.getFirstVertex(), edgeToRemove.getSecondVertex());
        }

        communities = graph.getConnectedComponents();
        return communities;
    }

    public static double calculateModularity(Graph graph, List<Set<Integer>> communities) {
        double modularity = 0.0;
        int m = graph.numEdges();
        double m2 = 2.0 * m;

        for (Integer i : graph.vertexSet()) {
            for (Integer j : graph.vertexSet()) {
                int Aij = graph.containsEdge(i, j) ? 1 : 0;
                int ki = graph.degree(i);
                int kj = graph.degree(j);

                for (Set<Integer> community : communities) {
                    if (community.contains(i) && community.contains(j)) {
                        modularity += (Aij - (ki * kj) / m2);
                        break;
                    }
                }
            }
        }

        return modularity / m2;
    }
}