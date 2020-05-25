package com.api.backend.service;

import com.api.backend.config.CommandPropertyConfig;
import com.api.backend.model.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommandService {
    private Set<String> commandSet = new HashSet<>();
    private CommandPropertyConfig propertyConfig;

    @Autowired
    public CommandService(CommandPropertyConfig propertyConfig) {
        this.propertyConfig = propertyConfig;
        fillCommandSet(propertyConfig, commandSet);
    }

    private void fillCommandSet(CommandPropertyConfig config, Set<String> commandSet) {
        commandSet.addAll(Arrays.asList(config.getClientIntroduction(), config.getClientExit(),
                config.getAddNode() , config.getAddEdge(), config.getRemoveEdge(),
                config.getRemoveNode(), config.getShortestPath() , config.getCloserThan()));
    }

    public String sendMessageToClient(Command command) {
        if(validCommand(command.getName())) {

        }
        return propertyConfig.getUnknownCommand();
    }

    private boolean validCommand(String name) {
        return false;
    }

}
