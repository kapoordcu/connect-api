package com.api.backend.message;

import com.api.backend.model.Command;

public class CommandContext {
    private CommandStrategy strategy;

    public CommandContext(CommandStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeCommand(Command command) {
        strategy.executeCommand(command);
    }

    public void setStrategy(CommandStrategy strategy) {
        this.strategy = strategy;
    }
}
