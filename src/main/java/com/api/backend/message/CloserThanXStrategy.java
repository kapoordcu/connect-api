package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class CloserThanXStrategy implements CommandStrategy {
    @Override
    public String executeCommand(Graph graph, Command command) {
        System.out.println(graph);
        System.out.println(command.getName());
        return null;
    }
}
