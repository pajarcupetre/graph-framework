# graph-framework

Graph framework for computing the shortest path and is the directed graph has a cycle.
Node class is generic and can take any type for the value as shown with User class in testCases.

Graph class use case:
1. Create graph with nodes and edges using Graph(nodes, connections) passing Set of nodes and a map of nodes and set of neighbours
2. Create empty graph using Graph()
   1. Append new nodes with addNodeToGraph(node)
   2. Append node with own connections addNodeWithConnectionsToGraph(node, connections)
   3. Add an edge with addConnection(node1, node2): this will create the nodes too in case don't exist
   4. Remove edge using removeConnection(node1, node2): this will remove the linkage from node1 to node if exists

GraphOperations class have static methods defined that taking graph can determine if has a cycle or the shortest path for two nodes