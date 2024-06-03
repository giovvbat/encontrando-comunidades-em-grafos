package org.example;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.scoring.EdgeBetweennessCentrality;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class GirvanNewman {
    public static List<Set<Integer>> split(Graph<Integer, DefaultEdge> graph) {
        ConnectivityInspector<Integer, DefaultEdge> inspector = new ConnectivityInspector<>(graph);
        List<Set<Integer>> communities = inspector.connectedSets();

        double oldModularity = -1;
        double newModularity = calculateModularity(graph, communities);
        DefaultEdge edgeToRemove = null;


        while (newModularity > oldModularity) {
            EdgeBetweennessCentrality<Integer, DefaultEdge> betweenness = new EdgeBetweennessCentrality<>(graph);

            double maxBetweenness = -1.0;

            for (DefaultEdge edge : graph.edgeSet()) {
                double score = betweenness.getEdgeScore(edge);
                if (score > maxBetweenness) {
                    maxBetweenness = score;
                    edgeToRemove = edge;
                }
            }

            if (edgeToRemove != null) {
                graph.removeEdge(edgeToRemove);
            }

            inspector = new ConnectivityInspector<>(graph);
            communities = inspector.connectedSets();

            oldModularity = newModularity;
            newModularity = calculateModularity(graph, communities);
        }

        if (edgeToRemove != null) {
            graph.addEdge(graph.getEdgeSource(edgeToRemove), graph.getEdgeTarget(edgeToRemove));
        }

        inspector = new ConnectivityInspector<>(graph);
        communities = inspector.connectedSets();
        return communities;
    }

    public static double calculateModularity(Graph<Integer, DefaultEdge> graph, List<Set<Integer>> communities) {
        double modularity = 0.0;
        int m = graph.edgeSet().size();
        double m2 = 2.0 * m;

        for (Integer i : graph.vertexSet()) {
            for (Integer j : graph.vertexSet()) {
                int Aij = graph.containsEdge(i, j) ? 1 : 0;
                int ki = graph.edgesOf(i).size();
                int kj = graph.edgesOf(j).size();

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