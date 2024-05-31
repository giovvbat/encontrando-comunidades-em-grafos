package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadGraph {
    public static void read(String filePath, Graph<Integer, DefaultEdge> graph) {
        try {
            InputStream inputStream = Main.class.getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = reader.readLine();
            Scanner scanner = new Scanner(line);

            Integer vertexCount = scanner.nextInt();
            Integer edgeCount = scanner.nextInt();
            Integer vertex;
            Integer firstVertex, secondVertex;

            for(int i=0; i<vertexCount; i++) {
                line = reader.readLine();
                vertex = Integer.parseInt(line);
                graph.addVertex(vertex);
            }

            for(int i=0; i<edgeCount; i++) {
                line = reader.readLine();
                Scanner secondScanner = new Scanner(line);
                firstVertex = secondScanner.nextInt();
                secondVertex = secondScanner.nextInt();
                graph.addEdge(firstVertex, secondVertex);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
