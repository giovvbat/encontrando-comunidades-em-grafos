package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Adicionando vértices e arestas ao grafo de exemplo
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);

        graph.addEdge(0, 5);
        graph.addEdge(3, 5);
        graph.addEdge(2, 4);
        graph.addEdge(1, 2);
        graph.addEdge(4, 5);
        graph.addEdge(3, 4);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);
        graph.addEdge(6, 7);
        graph.addEdge(1, 7);
        graph.addEdge(3, 8);
        graph.addEdge(4, 8);


        int desiredCommunities = 3;
        List<Set<Integer>> communities = GirvanNewman.girvanNewman(graph, desiredCommunities);

        System.out.println("Communities found:");
        for (Set<Integer> community : communities) {
            System.out.println(community);
        }
    }
}
