package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("File path is required");
            return;
        }

        String filePath = args[0];

        Graph graph = new Graph();

        FileWriter writer;

        try {
            ReadGraph.read(filePath, graph);
        } catch(IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        try {
            new File("results").mkdirs();

            Path path = Paths.get(filePath);
            writer = new FileWriter("results/result-" + path.getFileName());
        } catch(IOException e) {
            System.err.println(e.getMessage());
            return;
        }



        long startTime = System.currentTimeMillis();

        List<Set<Integer>> communities = GirvanNewman.split(graph);

        long endTime = System.currentTimeMillis();

        try {
            System.out.println("Communities found:");
            writer.write("Communities found:\n");
            for (Set<Integer> community : communities) {
                System.out.println(community);
                writer.write(community.toString() + "\n");
            }

            writer.write("\nTime taken: " + (endTime - startTime) + "ms\n");
            writer.write("Number of communities: " + communities.size() + "\n");

            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Number of communities: " + communities.size());
    }
}
