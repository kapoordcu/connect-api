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
        commandMap.put(config.getIntroduceId(), new ClientWelcomeStrategy());
        commandMap.put(config.getExitId(), new ClientExitStrategy());
        commandMap.put(config.getAddNodeId(), new AddNodeStrategy());
        commandMap.put(config.getAddEdgeId(), new AddEdgeStrategy());
        commandMap.put(config.getRmNodeId(), new RemoveNodeStrategy());
        commandMap.put(config.getRmEdgeId(), new RemoveEdgeStrategy());
        commandMap.put(config.getShortestPath(), new ShortestPathStrategy());
        commandMap.put(config.getCloserThan(), new CloserThanXStrategy());
    }

    public String sendMessageToClient(Command command) {
        String commandText = command.getName().trim().toLowerCase().replaceAll("\\s+", " ");
        command.setName(commandText);
        CommandStrategy strategy = identifyValidCommand(commandText);
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
