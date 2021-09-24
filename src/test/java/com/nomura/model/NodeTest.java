package com.nomura.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeTest
{
    @Test
    public void testInitNode()
    {
        int value = 123;
        Node<Integer> node = new Node<>(value);
        assertEquals(node.getValue().longValue(), value);
    }
}
