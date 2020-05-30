package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class UnknownCommandStrategy  implements CommandStrategy {
    @Override
    public String executeCommand(Graph graph, Command command) {
        return "SORRY, I DID NOT UNDERSTAND THAT";
    }
}
