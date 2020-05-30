package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class ClientExitStrategy implements CommandStrategy {
    @Override
    public String executeCommand(Graph graph, Command command) {
        String[] keys = command.getName().split("\\s");
        if(keys.length>=1) {
            return "Bye!!";
        }
        return "SORRY, I DID NOT UNDERSTAND THAT";
    }
}