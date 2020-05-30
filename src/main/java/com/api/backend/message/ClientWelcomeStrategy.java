package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class ClientWelcomeStrategy implements CommandStrategy {

    @Override
    public String executeCommand(Graph graph, Command text) {
        String[] keys = text.getName().split("\\s");
        for (int i = 0; i < keys.length -1; i++) {
            if(keys[i].equalsIgnoreCase("i") && keys[i+1].equalsIgnoreCase("am")) {
                return "Hi!! " + keys[i+2];
            }
        }
        return "SORRY, I DID NOT UNDERSTAND THAT";
    }
}