package src;

import java.io.*;
import java.util.Scanner;

public class ReadGraph {
    public static void read(String filePath, Graph graph) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
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
    }
}
