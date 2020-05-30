package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class ShortestPathStraregy implements CommandStrategy {
    @Override
    public String executeCommand(Graph graph, Command command) {
        String[] addCommandLiterals = command.getName().split("\\s");
        if(addCommandLiterals.length==4) {
            int returnCost = graph.shortestPath(addCommandLiterals[2], addCommandLiterals[3]);
            if(returnCost==Integer.MAX_VALUE) {
                return "ERROR: NODE NOT FOUND";
            }
            return String.valueOf(returnCost);
        }
        return "SORRY, I DID NOT UNDERSTAND THAT";
    }
}
