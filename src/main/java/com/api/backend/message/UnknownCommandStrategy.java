package com.api.backend.message;

import com.api.backend.graph.Graph;
import com.api.backend.model.Command;

public class UnknownCommandStrategy  implements CommandStrategy {
    @Override
    public String executeCommand(Graph graph, Command command) {
        return "ERROR: You entered '" +  command.getName() + "' Rectify the command with correct parameters";
    }
}
