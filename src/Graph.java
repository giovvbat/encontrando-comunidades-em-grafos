package src;

import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> graph = new HashMap<>();

    public void addEdge(Integer vertex1, Integer vertex2) {
        graph.putIfAbsent(vertex1, new ArrayList<>());
        graph.putIfAbsent(vertex2, new ArrayList<>());
        graph.get(vertex1).add(vertex2);
        graph.get(vertex2).add(vertex1);
    }

    public void addVertex(Integer vertex) {
        graph.putIfAbsent(vertex, new ArrayList<>());
    }

    public void removeEdge(Integer vertex1, Integer vertex2) {
        graph.get(vertex1).remove(vertex2);
        graph.get(vertex2).remove(vertex1);
    }

    public Integer numEdges() {
        Integer numEdges = 0;
        for (Integer key : graph.keySet()) {
            numEdges += graph.get(key).size();
        }

        return numEdges / 2;
    }

    public Integer degree(Integer vertex) {
        return graph.get(vertex).size();
    }

    public boolean containsEdge(Integer vertex1, Integer vertex2) {
        return graph.get(vertex1).contains(vertex2);
    }

    public Set<Integer> vertexSet() {
        return graph.keySet();
    }

    public List<Set<Integer>> getConnectedComponents() {
        List<Set<Integer>> communities = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (Integer node : graph.keySet()) {
            if (!visited.contains(node)) {
                Set<Integer> community = new HashSet<>();
                Queue<Integer> queue = new LinkedList<>();
                queue.add(node);

                while (!queue.isEmpty()) {
                    Integer v = queue.poll();
                    if (!visited.contains(v)) {
                        visited.add(v);
                        community.add(v);
                        for (Integer neighbor : graph.get(v)) {
                            if (!visited.contains(neighbor)) {
                                queue.add(neighbor);
                            }
                        }
                    }
                }

                communities.add(community);
            }
        }

        return communities;
    }

    public Pair edgeBetweennessCentrality() {
        Map<Pair, Double> edgeBetweenness = new HashMap<>();
        for (int s : graph.keySet()) {
            Queue<Integer> queue = new LinkedList<>();
            Stack<Integer> stack = new Stack<>();
            Map<Integer, List<Integer>> predecessors = new HashMap<>();
            Map<Integer, Integer> shortestPaths = new HashMap<>();
            Map<Integer, Double> dependency = new HashMap<>();
            Map<Integer, Integer> distance = new HashMap<>();

            for (int v : graph.keySet()) {
                predecessors.put(v, new ArrayList<>());
                shortestPaths.put(v, 0);
                distance.put(v, -1);
                dependency.put(v, 0.0);
            }

            shortestPaths.put(s, 1);
            distance.put(s, 0);
            queue.add(s);

            while (!queue.isEmpty()) {
                int v = queue.poll();
                stack.push(v);
                for (int w : graph.get(v)) {
                    if (distance.get(w) < 0) {
                        queue.add(w);
                        distance.put(w, distance.get(v) + 1);
                    }
                    if (distance.get(w) == distance.get(v) + 1) {
                        shortestPaths.put(w, shortestPaths.get(w) + shortestPaths.get(v));
                        predecessors.get(w).add(v);
                    }
                }
            }

            while (!stack.isEmpty()) {
                int w = stack.pop();
                for (int v : predecessors.get(w)) {
                    double c = ((double) shortestPaths.get(v) / (double) shortestPaths.get(w)) * (1.0 + dependency.get(w));
                    Pair edge = new Pair(Math.min(v, w), Math.max(v, w));
                    edgeBetweenness.put(edge, edgeBetweenness.getOrDefault(edge, 0.0) + c);
                    dependency.put(v, dependency.get(v) + c);
                }
            }
        }

        if (edgeBetweenness.isEmpty()) {
            return null;
        }

        return Collections.max(edgeBetweenness.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
