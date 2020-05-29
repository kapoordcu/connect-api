package com.api.backend.graph;

import java.util.*;

public class Node {
    private String name;
    Map<Node, Integer> neighbors = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addNeighbor(Node neighbor, int distance) {
        neighbors.put(neighbor, distance);
    }

    public void removeNeighbor(Node neighbor) {
        neighbors.remove(neighbor);
    }

    public Node[] getNeighbors() {
        Node[] result = new Node[neighbors.size()];
        neighbors.keySet().toArray(result);
        return result;
    }

    public int getNeighborDistance(Node node) {
        return neighbors.get(node);
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object another) {
        return name.equals(((Node) another).name);
    }

    public String getName() {
        return name;
    }
}
