package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class RemoveNodeStrategy implements CommandStrategy {
    @Override
    public String executeCommand(Graph graph, Command command) {
        String[] addCommandLiterals = command.getName().split("\\s");
        if(addCommandLiterals.length==3) {
            return graph.removeNode(addCommandLiterals[2]);
        }
        return "SORRY, I DID NOT UNDERSTAND THAT";
    }
}
