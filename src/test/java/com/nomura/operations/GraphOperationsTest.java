package com.nomura.operations;

import com.nomura.model.Graph;
import com.nomura.model.Node;
import com.nomura.model.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GraphOperationsTest {

    @Test
    public void testGraphHasCycle()
    {
        User first = new User("123", "Name");
        User second = new User( "234", "PName");
        User third = new User("345", "Tbane");
        User four = new User("456", "Fbnae");
        Node<User> firstNode = new Node<>(first);
        Node<User> secondNode = new Node<>(second);
        Node<User> thirdNode = new Node<>(third);
        Node<User> fourthNode = new Node<>(four);
        Graph graph = new Graph();
        graph.addConnection(firstNode, secondNode);
        graph.addNodeToGraph(thirdNode);
        graph.addNodeWithConnectionsToGraph(fourthNode, Arrays.asList(thirdNode, firstNode));
        graph.addConnection(secondNode, fourthNode);
        assertTrue(GraphOperations.hasCycle(graph));
        // Remove link that creates cycle
        graph.removeConnection(secondNode, fourthNode);
        assertFalse(GraphOperations.hasCycle(graph));
        // Add another one with cycle
        graph.addConnection(secondNode, firstNode);
        assertTrue(GraphOperations.hasCycle(graph));
    }

    @Test
    public void testPath() {
        User first = new User("123", "Name");
        User second = new User( "234", "PName");
        User third = new User("345", "Tbane");
        User four = new User("456", "Fbnae");
        Node<User> firstNode = new Node<>(first);
        Node<User> secondNode = new Node<>(second);
        Node<User> thirdNode = new Node<>(third);
        Node<User> fourthNode = new Node<>(four);
        Graph graph = new Graph();
        graph.addConnection(firstNode, secondNode);
        graph.addNodeToGraph(thirdNode);
        graph.addNodeWithConnectionsToGraph(fourthNode, Arrays.asList(thirdNode, firstNode));
        List<Node> expected = Arrays.asList(fourthNode, firstNode, secondNode);
        assertEquals(expected,GraphOperations.getPath(graph, fourthNode, secondNode));
        // Removing link between 1 and 2 will remove the path
        graph.removeConnection(firstNode, secondNode);
        assertEquals(null, GraphOperations.getPath(graph, fourthNode, secondNode));
    }
}
