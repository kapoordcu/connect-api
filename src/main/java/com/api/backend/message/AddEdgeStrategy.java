package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class AddEdgeStrategy implements CommandStrategy {
    @Override
    public String executeCommand(Graph graph, Command command) {
        try {
            String[] addCommandLiterals = command.getName().split("\\s");
            Integer weight = Integer.valueOf(addCommandLiterals[4]);
            if(addCommandLiterals.length==5) {
                return graph.addEdge(addCommandLiterals[2], addCommandLiterals[3], weight);
            }
        } catch (Exception ex) {
            return "Weights should be an Integer";
        }
        return "SORRY, I DID NOT UNDERSTAND THAT";
    }
}
