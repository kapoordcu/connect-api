package com.api.backend.graph;

import com.api.backend.config.GraphConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph {
    private Map<String, Node> nodes = new HashMap<String, Node>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Graph.class);
    private GraphConfigProperties graphConfigProperties = new GraphConfigProperties();

    public Graph() {}

    private String addNode(String name) {
        Node node = nodes.get(name);
        if (node != null) {
            return graphConfigProperties.getErrorLabel() + ": NODE ALREADY EXISTS.";
        }
        LOGGER.info("Adding a node with name '" + name + "'");
        nodes.put(name, new Node(name));
        return "NODE " + graphConfigProperties.getAddedLabel();
    }

    public String removeNode(String name) {
        Node node = new Node(name);
        if (node != null) {
            return graphConfigProperties.getErrorLabel() + ": NODE NOT FOUND.";
        }
        LOGGER.info("Removing a node with name '" + name + "'");
        nodes.remove(name);
        return "NODE " + graphConfigProperties.getRemovedLabel();
    }

    public String addEdge(String src, String dest, Integer weight) {
        String opMessageOnEdgeAddition = checkParametersForAddingEdge("ADDED", src, dest, weight);
        if(!"ERROR".contains(opMessageOnEdgeAddition)) {
            Node srcNode = nodes.get(src);
            Node destNode = nodes.get(dest);
            LOGGER.info("Adding an edge from " + src + " to " + dest + " with weight " + weight);
            srcNode.addNeighbor(destNode, weight);
            destNode.addNeighbor(srcNode, weight);

            nodes.put(src, srcNode);
            nodes.put(dest, destNode);
        }
        return opMessageOnEdgeAddition;
    }

    public String removeEdge(String src, String dest) {
        String opMessageOnEdgeAddition = checkParametersForAddingEdge("REMOVED", src, dest, 10);
        if(!"ERROR".contains(opMessageOnEdgeAddition)) {
            Node srcNode = nodes.get(src);
            Node destNode = nodes.get(dest);
            LOGGER.info("Removing an edge from " + src + " to " + dest);
            srcNode.removeNeighbor(destNode);
            destNode.removeNeighbor(srcNode);
            nodes.remove(src);
            nodes.remove(dest);
        }
        return opMessageOnEdgeAddition;
    }
    
    private String checkParametersForAddingEdge(String operation, String src, String dest, Integer weight) {
        String opMessageOnEdgeAddition = "EDGE " + operation;
        Node srcNode = nodes.get(src);
        Node destNode = nodes.get(dest);
        if(srcNode == null) {
            opMessageOnEdgeAddition = graphConfigProperties.getErrorLabel() + ": NODE '" + src + "' NOT FOUND";
        } else if(destNode == null) {
            opMessageOnEdgeAddition =  graphConfigProperties.getErrorLabel() + ": NODE '" + dest + "' NOT FOUND";
        } else if(weight<0) {
            opMessageOnEdgeAddition =  graphConfigProperties.getErrorLabel() + ": Weight '" +  weight + "' is negative.";
        }
        return opMessageOnEdgeAddition;
    }

    public int shortestPath(String sourceNode, String destNode) {
        Map<String, String> parents = new HashMap<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<PathNode> priorityQueue = new PriorityQueue<PathNode>();

        PathNode start = new PathNode(sourceNode, null, 0);
        priorityQueue.add(start);

        while (priorityQueue.size() > 0) {
            PathNode currentPathNode = priorityQueue.remove();

            if (!visited.contains(currentPathNode.getName())) {
                Node currentNode = nodes.get(currentPathNode.getName());
                parents.put(currentPathNode.getName(), currentPathNode.getParent());
                visited.add(currentPathNode.getName());

                if (currentPathNode.getName().equals(destNode)) {
                    return calculatePathCost(parents, destNode);
                }

                Node[] neighbors = nodes.get(currentPathNode.getName()).getNeighbors();
                for (int i = 0; i < neighbors.length; i++) {
                    Node neighbor = neighbors[i];
                    int distance2root = currentPathNode.getDistance2root() + currentNode.getNeighborDistance(neighbor);
                    priorityQueue.add(new PathNode(neighbor.getName(), currentPathNode.getName(), distance2root));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private int calculatePathCost(Map<String, String> parents, String endNodeName) {
        List<String> path = new ArrayList<>();
        String node = endNodeName;
        while (node != null) {
            path.add(0, node);
            String parent = parents.get(node);
            node = parent;
        }
        return calculatePathCost(path);
    }

    private int calculatePathCost(List<String> path) {
        int pathCost = 0;
        for (int i = 0; i < path.size()-1; i++) {
            pathCost += nodes.get(path.get(i)).getNeighborDistance(nodes.get(path.get(i+1)));
        }
        return pathCost;
    }
}