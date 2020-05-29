package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class ShortestPathStraregy implements CommandStrategy {
    @Override
    public String executeCommand(Graph graph, Command command) {
        String[] addCommandLiterals = command.getName().split("\\s");
        if(addCommandLiterals.length==4) {
            return String.valueOf(graph.shortestPath(addCommandLiterals[2], addCommandLiterals[3]));
        }
        return "SORRY, I DID NOT UNDERSTAND THAT";
    }
}
