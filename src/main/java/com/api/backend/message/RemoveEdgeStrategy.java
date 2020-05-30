package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class RemoveEdgeStrategy implements CommandStrategy {
    @Override
    public String executeCommand(Graph graph, Command command) {
        String[] keys = command.getName().split("\\s");
        if(keys.length==4) {
            return graph.removeEdge(keys[2], keys[3]);
        }
        return "SORRY, I DID NOT UNDERSTAND THAT";
    }
}
