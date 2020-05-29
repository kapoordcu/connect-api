package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public interface CommandStrategy {
    String executeCommand(Graph graph, Command command);
}
