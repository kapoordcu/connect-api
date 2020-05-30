package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class RemoveNodeStrategy implements CommandStrategy {
    @Override
    public String executeCommand(Graph graph, Command command) {
        String[] keys = command.getName().split("\\s");
        if(keys.length==3) {
            return graph.removeNode(keys[2]);
        }
        return "SORRY, I DID NOT UNDERSTAND THAT";
    }
}
