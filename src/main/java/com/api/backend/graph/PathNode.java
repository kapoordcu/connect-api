package com.api.backend.graph;

class PathNode implements Comparable<PathNode> {
    private String name;
    private String parent;
    private int distance2root;

    public PathNode(String name, String parent, int distance2root) {
        this.name = name;
        this.parent = parent;
        this.distance2root = distance2root;
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }

    public int getDistance2root() {
        return distance2root;
    }

    @Override
    public int compareTo(PathNode another) {
        return distance2root - another.distance2root;
    }

    @Override
    public String toString() {
        return "(" + this.name + "," + this.parent + "," + this.distance2root + ")";
    }
}