package com.api.backend.graph;

public class NodeExistsException extends RuntimeException {
    private String exMessage;

    public NodeExistsException(String exMessage) {
        this.exMessage = exMessage;
    }
}
