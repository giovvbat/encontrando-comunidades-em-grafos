package org.example;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.scoring.EdgeBetweennessCentrality;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;

public class GirvanNewman {
    public static List<Set<Integer>> split(Graph<Integer, DefaultEdge> graph, int desiredCommunities) {
        List<Set<Integer>> communities = new ArrayList<>();
        ConnectivityInspector<Integer, DefaultEdge> inspector = new ConnectivityInspector<>(graph);

        // Alterado para parar quando o número de componentes conectados for igual ao desejado
        while (inspector.connectedSets().size() < desiredCommunities) {
            EdgeBetweennessCentrality<Integer, DefaultEdge> betweenness = new EdgeBetweennessCentrality<>(graph);
            DefaultEdge edgeToRemove = null;
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
        }

        communities.addAll(inspector.connectedSets());
        return communities;
    }
}