package com.api.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {

    @Value("${client.introduction}")
    private String clientIntroduction;

    @Value("${client.welcome: Hi! }")
    private String welcomeMessage;

    @Value("${client.exit}")
    private String clientExit;

    @Value("${add.node}")
    private String addNode;

    @Value("${add.edge}")
    private String addEdge;

    @Value("${remove.node}")
    private String removeNode;

    @Value("${remove.edge}")
    private String removeEdge;

    @Value("${shortest.path}")
    private String shortestPath;

    @Value("${closer.than}")
    private String closerThan;

    @Value("${unknown.command}")
    private String unknownCommand;

    @Value("${graph.error:ERROR}")
    private String errorLabel;

    @Value("${graph.added:ADDED}")
    private String addedLabel;

    @Value("${graph.removed:REMOVED}")
    private String removedLabel;

    @Value("${graph.delimiter: : }")
    private String delimiter;

    @Value("${graph.node.exists:NODE ALREADY EXISTS}")
    private String nodeExists;

    @Value("${graph.not.found: NOT FOUND}")
    private String notFound;

    @Value("${graph.node.not.found:NODE NOT FOUND}")
    private String nodeNotFound;

    @Value("${graph.node:NODE}")
    private String node;

    @Value("${graph.edge:EDGE}")
    private String edge;

    @Value("${graph.weight:WEIGHT}")
    private String weight;

    @Value("${graph.negative.wt:IS NEGATIVE}")
    private String negWeight;

    @Value("${message.info.adding.node:Adding a node}")
    private String addingNodeInfo;

    @Value("${message.info.removing.node:Removing a node}")
    private String removingNodeInfo;

    @Value("${message.info.adding.edge:Adding an edge}")
    private String addingEdgeInfo;

    @Value("${message.info.removing.edge:Removing an edge}")
    private String removingEdgeInfo;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public String getClientIntroduction() {
        return clientIntroduction;
    }

    public String getClientExit() {
        return clientExit;
    }

    public String getAddNode() {
        return addNode;
    }

    public String getAddEdge() {
        return addEdge;
    }

    public String getRemoveNode() {
        return removeNode;
    }

    public String getRemoveEdge() {
        return removeEdge;
    }

    public String getShortestPath() {
        return shortestPath;
    }

    public String getCloserThan() {
        return closerThan;
    }

    public String getUnknownCommand() {
        return unknownCommand;
    }

    public String getErrorLabel() {
        return errorLabel;
    }

    public String getAddedLabel() {
        return addedLabel;
    }

    public String getRemovedLabel() {
        return removedLabel;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getNodeExists() {
        return nodeExists;
    }

    public String getNotFound() {
        return notFound;
    }

    public String getNodeNotFound() {
        return nodeNotFound;
    }

    public String getNode() {
        return node;
    }

    public String getEdge() {
        return edge;
    }

    public String getWeight() {
        return weight;
    }

    public String getNegWeight() {
        return negWeight;
    }

    public String getAddingNodeInfo() {
        return addingNodeInfo;
    }

    public String getRemovingNodeInfo() {
        return removingNodeInfo;
    }

    public String getAddingEdgeInfo() {
        return addingEdgeInfo;
    }

    public String getRemovingEdgeInfo() {
        return removingEdgeInfo;
    }
}
