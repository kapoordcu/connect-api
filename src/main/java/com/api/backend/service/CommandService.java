package com.api.backend.service;

import com.api.backend.config.PropertyConfig;
import com.api.backend.graph.Graph;
import com.api.backend.message.*;
import com.api.backend.model.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CommandService {
    private Map<String, CommandStrategy> commandMap = new HashMap();
    private PropertyConfig config;
    private Pattern regexPattern;
    private Graph graph;
    private CommandContext commandContext = new CommandContext(new UnknownCommandStrategy(), graph);

    @Autowired
    public CommandService(PropertyConfig propertyConfig) {
        this.config = propertyConfig;
        graph = new Graph(config);
        fillCommandMap();
    }

    private void fillCommandMap() {
        commandMap.put(config.getClientIntroduction(), new ClientIntroductionStrategy());
        commandMap.put(config.getClientExit(), new ClientExitStrategy());
        commandMap.put(config.getAddNode(), new AddNodeStrategy());
        commandMap.put(config.getAddEdge(), new AddEdgeStrategy());
        commandMap.put(config.getRemoveNode(), new RemoveNodeStrategy());
        commandMap.put(config.getRemoveEdge(), new RemoveEdgeStrategy());
        commandMap.put(config.getShortestPath(), new ShortestPathStraregy());
        commandMap.put(config.getCloserThan(), new WeightCommandStrategy());
    }

    public String sendMessageToClient(Command command) {
        CommandStrategy strategy = identifyValidCommand(command.getName());
        commandContext.setStrategy(strategy);
        return strategy.executeCommand(graph, command);
    }

    private CommandStrategy identifyValidCommand(String command) {
        List<CommandStrategy> identifiedCommandList = commandMap.entrySet()
                .stream()
                .map(commandValue -> {
                    Pattern compile = Pattern.compile(commandValue.getKey().toLowerCase());
                    Matcher matcher = compile.matcher(command.toLowerCase());
                    if (matcher.matches()) {
                        return commandValue.getValue();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if(identifiedCommandList.size()==0) {
            return new UnknownCommandStrategy();
        }
        return identifiedCommandList.get(0);
    }

}
