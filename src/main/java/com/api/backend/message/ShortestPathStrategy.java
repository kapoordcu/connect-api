package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class ShortestPathStrategy implements CommandStrategy {
    @Override
    public String executeCommand(Graph graph, Command command) {
        String[] keys = command.getName().split("\\s");
        if(keys.length==4) {
            int returnCost = graph.shortestPath(keys[2], keys[3]);
            if(returnCost==Integer.MAX_VALUE) {
                return "ERROR: NODE NOT FOUND";
            }
            return String.valueOf(returnCost);
        }
        return "SORRY, I DID NOT UNDERSTAND THAT";
    }
}
