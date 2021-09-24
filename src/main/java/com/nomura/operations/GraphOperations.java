package com.nomura.operations;

import com.nomura.model.Graph;
import com.nomura.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphOperations {

    public static boolean hasCycle(Graph graph) {

        for (Node node: graph.getNodes()) {
            if (isCycle(graph, node, new HashSet<>(), new HashSet<>())) {
                return true;
            }
        }
        return false;
    }

    /*
     * Check if a cycle using DFS and considering if already have a node on Path a new connection 
     * will create a cycle
     */
    private static boolean isCycle(Graph graph, Node node, Set<Node> visited, Set<Node> path) {
        visited.add(node);
        Set<Node> connections = graph.getConnections().getOrDefault(node, new HashSet<Node>());
        path.add(node);
        for (Node nodeInConnection : connections) {
            if (path.contains(nodeInConnection)) {
                return true;
            }
            if (!visited.contains(nodeInConnection)) {
                if (isCycle(graph, nodeInConnection, visited, path)) {
                    return true;
                }
            }
        }
        // Remove node from path when returning on upper level
        path.remove(node);
        return false;
    }

    /*
     * Shortest path between two nodes using BFS algorithm
     */
    public static List<Node> getPath(Graph graph, Node start, Node end) {
        Set<Node> visited = new HashSet<>();
        visited.add(start);
        ArrayList<Node> inLine = new ArrayList<>();
        inLine.add(start);
        HashMap<Node, Node> parents = new HashMap<>();
        parents.put(start, null);
        int index = 0;
        while (index < inLine.size()) {
            Node node = inLine.get(index);
            for (Node connection: graph.getConnections().getOrDefault(node, new HashSet<>())){
                if (!visited.contains(connection)) {
                    visited.add(connection);
                    inLine.add(connection);
                    parents.put(connection, node);
                    if (connection.equals(end)) {
                        return createPath(parents, end);
                    }
                }
            }
            index += 1;
        }
        // no path was found
        return null;
    }

    private static List<Node> createPath(HashMap<Node, Node> parents, Node end) {
        List<Node> path = new ArrayList<>();
        path.add(end);
        Node next = parents.get(end);
        while (next != null) {
            path.add(next);
            next = parents.get(next);
        }
        Collections.reverse(path);
        return path;
    }
}
