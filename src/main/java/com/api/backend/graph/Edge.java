package com.api.backend.graph;

public class Edge {
    private String src;
    private String dest;
    private double weight;

    public Edge(String src, String dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public String getSrc() {
        return src;
    }

    public String getDest() {
        return dest;
    }

    public double getWeight() {
        return weight;
    }
}
