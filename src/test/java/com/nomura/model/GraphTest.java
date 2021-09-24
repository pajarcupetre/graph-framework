package com.nomura.model;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class GraphTest {

    @Test
    public void testEmptyGraphInit() {
        Graph graph = new Graph();

        assertEquals(graph.getNodes().size(), 0);
        assertEquals(graph.getConnections().size(), 0);
    }

    @Test
    public void testGraphInitWithValues() {
        Node first = new Node<User>(new User("3", "Tierney"));
        Node second = new Node<User>(new User("6", "Holding"));
        HashSet<Node> users =  new HashSet<>(Arrays.asList(first, second));
        HashMap<Node, HashSet<Node>> connections = new HashMap<>();
        connections.put(first, new HashSet<>(Arrays.asList(second)));
        Graph graph = new Graph(users, connections);
        assertEquals(graph.getNodes().size(), 2);
        assertEquals(graph.getConnections().size(), 1);
    }

    @Test
    public void testGraphAddUser() {
        Graph graph = new Graph();
        Node first = new Node<User>(new User("3", "Tierney"));
        graph.addNodeToGraph(first);
        assertEquals(graph.getNodes().size(), 1);
        assertEquals(graph.getConnections().size(), 0);
    }

    @Test
    public void testGraphAddConnection() {
        Graph graph = new Graph();
        Node first = new Node<User>(new User("3", "Tierney"));
        Node second = new Node<User>(new User("6", "Holding"));
        assertEquals(graph.getNodes().size(), 0);
        graph.addConnection(first, second);
        assertEquals(graph.getNodes().size(), 2);
        assertEquals(graph.getConnections().size(), 1);
    }

    @Test
    public void testGraphAddConnectionWithAlreadyExistingNode() {
        Graph graph = new Graph();
        Node first = new Node<User>(new User("3", "Tierney"));
        Node second = new Node<User>(new User("6", "Holding"));
        graph.addNodeToGraph(first);
        graph.addNodeToGraph(second);
        assertEquals(graph.getNodes().size(), 2);
        assertEquals(graph.getConnections().size(), 0);
        graph.addConnection(first, second);
        assertEquals(graph.getNodes().size(), 2);
        assertEquals(graph.getConnections().size(), 1);
    }

    @Test
    public void testGraphAddConnectionWithAlreadyExistingConnection() {
        Node first = new Node<User>(new User("3", "Tierney"));
        Node second = new Node<User>(new User("6", "Holding"));
        HashSet<Node> users =  new HashSet<>(Arrays.asList(first, second));
        HashMap<Node, HashSet<Node>> connections = new HashMap<>();
        connections.put(first, new HashSet<>(Arrays.asList(second)));
        Graph graph = new Graph(users, connections);
        assertEquals(graph.getConnections().size(), 1);
        //Adding same connection won't update the graph
        graph.addConnection(first, second);
        assertEquals(graph.getConnections().size(), 1);
    }

    @Test
    public void testGraphRemoveConnection() {
        Graph graph = new Graph();
        Node first = new Node<User>(new User("3", "Tierney"));
        Node second = new Node<User>(new User("6", "Holding"));
        assertEquals(graph.getNodes().size(), 0);
        graph.addConnection(first, second);
        assertEquals(graph.getNodes().size(), 2);
        assertEquals(graph.getConnections().size(), 1);
        // Remove link first second
        graph.removeConnection(first, second);
        assertEquals(graph.getConnections().size(), 0);
        // Add reverse link
        graph.addConnection(second, first);
        assertEquals(graph.getConnections().size(), 1);
        // Remove doesn't do anything because it is using reverse link
        graph.removeConnection(first, second);
        assertEquals(graph.getConnections().size(), 1);
    }

}
