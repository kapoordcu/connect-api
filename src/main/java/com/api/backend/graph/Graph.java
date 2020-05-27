package com.api.backend.graph;

import com.api.backend.config.GraphConfigProperties;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Graph {
    private static final Logger LOGGER = LoggerFactory.getLogger(Graph.class);
    private Map<Node, List<Edge>> graph = new HashMap<>();

    @Autowired
    private GraphConfigProperties graphConfigProperties;

    public String addEdge(String src, String dest, Integer weight) {
        LOGGER.info("Adding an edge from " + src + " to " + dest + " with weight " + weight);
        String opMessageOnEdgeAddition = checkParametersForAddingEdge(graphConfigProperties.getAddedLabel(), src, dest, weight);
        if(!graphConfigProperties.getErrorLabel().contains(opMessageOnEdgeAddition)) {
            Edge newEdge = new Edge(src, dest, weight);
            graph.get(new Node(src)).add(newEdge);
        }
        return opMessageOnEdgeAddition;
    }

    public String removeEdge(String src, String dest) {
        LOGGER.info("Removing an edge from " + src + " to " + dest);
        String opMessageOnEdgeRemoval = checkParametersForAddingEdge(graphConfigProperties.getRemovedLabel(), src, dest, 10);
        if(!graphConfigProperties.getErrorLabel().contains(opMessageOnEdgeRemoval)) {
            Node srcNode = new Node(src);
            List<Edge> edgesFromSource = graph.get(srcNode);
            for (Edge edge : edgesFromSource) {
                if(edge.getDest().equals(dest)) {
                    edgesFromSource.remove(edge);
                }
            }
        }
        return opMessageOnEdgeRemoval;
    }

    private String checkParametersForAddingEdge(String operation, String src, String dest, Integer weight) {
        String opMessageOnEdgeAddition = "EDGE " + operation;
        Node srcNode = new Node(src);
        Node destNode = new Node(dest);
        if(!graph.containsKey(srcNode)) {
            opMessageOnEdgeAddition = graphConfigProperties.getErrorLabel() + ": NODE '" + src + "' NOT FOUND";
        } else if(!graph.containsKey(destNode)) {
            opMessageOnEdgeAddition =  graphConfigProperties.getErrorLabel() + ": NODE '" + dest + "' NOT FOUND";
        } else if(weight<0) {
            opMessageOnEdgeAddition =  graphConfigProperties.getErrorLabel() + ": Weight '" +  weight + "' is negative.";
        }
        return opMessageOnEdgeAddition;
    }

    public String addNode(String name) {
        LOGGER.info("Adding a node with name '" + name + "'");
        Node node = new Node(name);
        if(graph.containsKey(node)) {
           return graphConfigProperties.getErrorLabel() + ": NODE ALREADY EXISTS.";
        }
        graph.put(node, new LinkedList<>());
        return "NODE " + graphConfigProperties.getAddedLabel();
    }

    public String removeNode(String name) {
        LOGGER.info("Removing a node with name '" + name + "'");
        Node node = new Node(name);
        if(!graph.containsKey(node)) {
            return graphConfigProperties.getErrorLabel() + ": NODE NOT FOUND.";
        }
        graph.remove(node);
        return "NODE " + graphConfigProperties.getRemovedLabel();
    }
//
//    @Test
//    public void addNodesAndEdges() {
//        Graph start = new Graph();
//        System.out.println(start.addNode("X"));
//        System.out.println(start.addNode("Y"));
//        System.out.println(start.addNode("X"));
//        System.out.println(start.addNode("Z"));
//        System.out.println(start.removeNode("Z"));
//        System.out.println(start.removeNode("Z"));
//
//
//        System.out.println(start.addEdge("X", "Y", 10));
//        System.out.println(start.addEdge("X", "X", 8));
//        System.out.println(start.addEdge("X", "Q", 5));
//        System.out.println(start.addEdge("Y", "X", 5));
//        start.graph.entrySet()
//                .stream()
//                .map(entry-> entry.getKey())
//                .forEach(System.out::println);
//    }
}
