package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class CommandContext {
    private CommandStrategy strategy;
    private Graph graph;

    public CommandContext(CommandStrategy strategy, Graph graph) {
        this.strategy = strategy;
        this.graph = graph;
    }

    public void executeCommand(Command command) {
        strategy.executeCommand(graph, command);
    }

    public void setStrategy(CommandStrategy strategy) {
        this.strategy = strategy;
    }
}
