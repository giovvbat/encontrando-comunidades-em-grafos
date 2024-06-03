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

        long startTime = System.currentTimeMillis();

        List<Set<Integer>> communities = GirvanNewman.split(graph);

        long endTime = System.currentTimeMillis();

        System.out.println("Communities found:");
        for (Set<Integer> community : communities) {
            System.out.println(community);
        }

        System.out.println();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Number of communities: " + communities.size());
    }
}
