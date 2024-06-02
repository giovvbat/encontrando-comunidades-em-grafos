package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.List;
import java.util.Set;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        ReadGraph.read(filePath, graph);

        int desiredCommunities = 8;
        List<Set<Integer>> communities = GirvanNewman.split(graph, desiredCommunities);

        System.out.println("Communities found:");
        for (Set<Integer> community : communities) {
            System.out.println(community);
        }
    }
}
