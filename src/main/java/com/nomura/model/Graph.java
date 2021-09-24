package com.nomura.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Graph {
    private HashSet<Node> nodes;
    private HashMap<Node, HashSet<Node>> connections;

    public Graph() {
        this.nodes = new HashSet<>();
        this.connections = new HashMap<>();
    }

    public Graph(HashSet<Node> nodes, HashMap<Node, HashSet<Node>> connections) {
        this.nodes = nodes;
        this.connections = connections;
    }

    public HashSet<Node> getNodes() {
        return nodes;
    }

    public HashMap<Node, HashSet<Node>> getConnections() {
        return connections;
    }

    public void addConnection(Node from, Node to) {
        addNodeToGraph(from);
        addNodeToGraph(to);
        addConnectionsOfExistingNodes(from, new HashSet<>(Arrays.asList(to)));
    }

    public void removeConnection(Node from, Node to) {
        if (nodes.contains(from) && connections.getOrDefault(from, new HashSet<Node>()).contains(to)) {
            HashSet<Node> connectionsForNode = connections.get(from);
            connectionsForNode.remove(to);
            if (connectionsForNode.isEmpty()) {
                connections.remove(from);
            } else {
                connections.put(to, connectionsForNode);
            }
        }
    }

    public void addNodeToGraph(Node node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    public void addNodeWithConnectionsToGraph(Node node, List<Node> connections) {
        addNodeToGraph(node);
        connections.forEach(this::addNodeToGraph);
        addConnectionsOfExistingNodes(node, new HashSet<>(connections));

    }

    private void addConnectionsOfExistingNodes(Node from, HashSet<Node> to){
        HashSet connectionsFromNode = connections.getOrDefault(from, new HashSet());
        connectionsFromNode.addAll(to);
        connections.put(from, connectionsFromNode);
    }

}
