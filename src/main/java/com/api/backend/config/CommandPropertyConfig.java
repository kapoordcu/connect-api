package com.api.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandPropertyConfig {

    @Value("${client.introduction}")
    private String clientIntroduction;

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
}
