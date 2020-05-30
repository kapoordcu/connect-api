package com.api.backend.graph;

import com.api.backend.config.PropertyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

@Component
public class Graph {
    private Map<String, Node> nodes = new HashMap<String, Node>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Graph.class);
    private PropertyConfig config;

    @Autowired
    public Graph(PropertyConfig propertyConfig) {
        this.config = propertyConfig;
    }

    public String addNode(String name) {
        Node node = nodes.get(name);
        if (node != null) {
            return config.getErrorLabel() + config.getDelimiter() + config.getNodeExists();
        }
        LOGGER.info(String.format(config.getAddingNodeInfo(), name));
        nodes.put(name, new Node(name));
        return config.getNode() + config.getAddedLabel();
    }

    public String removeNode(String name) {
        Node node = nodes.get(name);
        if (node == null) {
            return config.getErrorLabel() + config.getDelimiter() + config.getNodeNotFound();
        }
        LOGGER.info(String.format(config.getRemovingNodeInfo(), name));
        nodes.remove(name);
        return config.getNode() + config.getRemovedLabel();
    }

    public String addEdge(String src, String dest, Integer weight) {
        String opMessageOnEdgeAddition = checkParametersForAddingEdge(config.getAddedLabel(), src, dest, weight);
        if(!opMessageOnEdgeAddition.contains(config.getErrorLabel())) {
            Node srcNode = nodes.get(src);
            Node destNode = nodes.get(dest);
            LOGGER.info(String.format(config.getAddingEdgeInfo(), src, dest, weight));
            srcNode.addNeighbor(destNode, weight);
            destNode.addNeighbor(srcNode, weight);

            nodes.put(src, srcNode);
            nodes.put(dest, destNode);
        }
        return opMessageOnEdgeAddition;
    }

    public String removeEdge(String src, String dest) {
        String opMessageOnEdgeRemoval = checkParametersForAddingEdge(config.getRemovedLabel(), src, dest, 10);
        if(!opMessageOnEdgeRemoval.contains(config.getErrorLabel())) {
            Node srcNode = nodes.get(src);
            Node destNode = nodes.get(dest);
            LOGGER.info(String.format(config.getRemovingEdgeInfo(), src, dest));
            srcNode.removeNeighbor(destNode);
            destNode.removeNeighbor(srcNode);
            nodes.remove(src);
            nodes.remove(dest);
        }
        return opMessageOnEdgeRemoval;
    }

    private String checkParametersForAddingEdge(String operation, String src, String dest, Integer weight) {
        String opMessageOnEdgeAddition = config.getEdge() + operation;
        Node srcNode = nodes.get(src);
        Node destNode = nodes.get(dest);
        if(srcNode == null) {
            opMessageOnEdgeAddition = config.getErrorLabel() + config.getDelimiter() + config.getNode() + src + config.getNotFound();
        } else if(destNode == null) {
            opMessageOnEdgeAddition =  config.getErrorLabel() + config.getDelimiter() + config.getNode()  + dest + config.getNotFound();
        } else if(weight<0) {
            opMessageOnEdgeAddition =  config.getErrorLabel() + config.getDelimiter() + config.getWeight() +  weight + config.getNegWeight();
        }
        return opMessageOnEdgeAddition;
    }

    public int shortestPath(String sourceNode, String destNode) {
        if(nodes.get(sourceNode)==null
                || nodes.get(destNode)==null ) {
            return Integer.MAX_VALUE;
        }
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
        return 0;
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