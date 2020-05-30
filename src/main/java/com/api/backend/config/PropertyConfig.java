package com.api.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {

    @Value("${client.introduction:i am}")
    private String introduceId;

    @Value("${client.welcome: Hi! }")
    private String welcomeId;

    @Value("${client.exit:bye}")
    private String exitId;

    @Value("${add.node.id:add node (.*)}")
    private String addNodeId;

    @Value("${remove.node.id:add node (.*)}")
    private String rmNodeId;

    @Value("${add.edge.id:add node (.*)}")
    private String addEdgeId;

    @Value("${remove.edge.id:add node (.*)}")
    private String rmEdgeId;

    @Value("${shortest.path}")
    private String shortestPath;

    @Value("${closer.than}")
    private String closerThan;

    @Value("${sorry.message: SORRY, I DID NOT UNDERSTAND THAT}")
    private String sorryInfo;

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

    public String getWelcomeId() {
        return welcomeId;
    }

    public String getIntroduceId() {
        return introduceId;
    }

    public String getExitId() {
        return exitId;
    }

    public String getAddNodeId() {
        return addNodeId;
    }

    public String getAddEdgeId() {
        return addEdgeId;
    }

    public String getRmNodeId() {
        return rmNodeId;
    }

    public String getRmEdgeId() {
        return rmEdgeId;
    }

    public String getShortestPath() {
        return shortestPath;
    }

    public String getCloserThan() {
        return closerThan;
    }

    public String getSorryInfo() {
        return sorryInfo;
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
